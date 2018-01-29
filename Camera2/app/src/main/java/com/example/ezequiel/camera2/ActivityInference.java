package com.example.ezequiel.camera2;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.tensorflow.Operation;
import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by kimsanghwan on 2017-08-03.
 */

public class ActivityInference {
    static {
        System.loadLibrary("tensorflow_inference");
    }

    private static ActivityInference activityInferenceInstance;
    private TensorFlowInferenceInterface inferenceInterface;
    private static final String MODEL_FILE = "file:///android_asset/frozen_savedmodel.pb";
    private static final String INPUT_NODE = "images_input";
    private static final String OUTPUT_NODE = "output/output";
//    private static final String OUTPUT_NODE_TWO = "ArgMax";

    private static final int[] INPUT_SIZE = {1,3};
    private static AssetManager assetManager;
    private Context mContext;
    private int[] intValues;
    private float[] floatValues;
    private float[][]outputValues;


    private int inputSize = 512;
    private int imageMean = 117;
    private float imageStd = 1;
    float[] max_list, point_x, point_y = new float[14];
    int[] index_list = new int[14];

    public static ActivityInference getInstance(final Context context)
    {
        if (activityInferenceInstance == null)
        {
            activityInferenceInstance = new ActivityInference(context);
        }
        return activityInferenceInstance;
    }

    public ActivityInference(final Context context) {
        mContext = context;
        this.assetManager = context.getAssets();
        inferenceInterface = new TensorFlowInferenceInterface(assetManager, MODEL_FILE);
    }

    public static Bitmap resizeSquare(Bitmap src, int max) {
        if(src == null)
            return null;

        return Bitmap.createScaledBitmap(src, max, max, true);
    }

    public int getAge(Bitmap bitmap) {

//        float[] resu = new float[(int) inferenceInterface.graphOperation(OUTPUT_NODE).output(0).shape().size(1)];//        inferenceInterface
//        float[] resu_two = new float[(int) inferenceInterface.graphOperation(OUTPUT_NODE_TWO).output(0).shape().size(1)];//        inferenceInterface

        InputStream istr;
//        Bitmap bitmap = null;
        final Operation operation = inferenceInterface.graphOperation(OUTPUT_NODE);

        try {
            istr = assetManager.open("profile_two.jpg");
//            bitmap = resizeSquare(bitmap, 240);

//            bitmap = resizeSquare(BitmapFactory.decodeStream(istr), 240);

            intValues = new int[bitmap.getWidth() * bitmap.getHeight()];
            floatValues = new float[bitmap.getWidth() * bitmap.getHeight() * 3];
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
            int inputSecondSize = intValues.length - inputSize;
            for (int i = 0; i < intValues.length; ++i) {
                final int val = intValues[i];
                floatValues[i * 3 + 0] = (((val >> 16) & 0xFF));
                floatValues[i * 3 + 1] = (((val >> 8) & 0xFF));
                floatValues[i * 3 + 2] = ((val & 0xFF));
            }
            boolean[] input = new boolean[1];
            input[0] = false;
//            inferenceInterface.feed(INPU, input);

            inferenceInterface.feed(INPUT_NODE, floatValues, 1, height, width, 3);

            inferenceInterface.run(new String[] {
                    OUTPUT_NODE
            });
            outputValues = new float[1][8];

            float[] resu = new float[8];
            long[] resu_two = new long[1];
            inferenceInterface.fetch(OUTPUT_NODE, resu);
            float max = 0;
            int index = 0;
            for (int i = 0; i < resu.length; i++) {
                if (resu[i] > max) {
                    max = resu[i];
                    index = i;
                }
            }
            return index;
//            inferenceInterface.fetch(OUTPUT_NODE_TWO, resu_two);
//            int size = 64 * 36;
//
//            int index = 0;
//            float max = resu[0];
//            for (int i = 0; i < resu.length; i++) {

//                if ((i % 14 == 0)) {
////                    float max = resu[i];
//                    if (resu[i] > max) {
//                        max = resu[i];
//                        index = i / 14;
//                    }


//                    for (int j = 0; j < size; j++) {
//
//                        if(resu[j + (size*i)] > max) {
//                            max = resu[j + (size*i)];
//                            index = j;
//                        }
//
////                    data[j] = resu.get(j + (size*i));
//                    }

//                int index = findMaxIndex(data);

//                }


//                Log.d("e", "test");
//            }
//
//            int first = index / 36;
//            int second = index % 36;
//            StringBuilder builder = new StringBuilder();
//
//            String h = inferenceInterface.getStatString();
//
//            String result = builder.toString();
//            return result;
        } catch (IOException e) {
            Log.d("e", e.toString());
            Log.d("e", e.toString());
            return 0;
            // handle exception
        }

//        return ;
    }

}