package com.example.ezequiel.camera2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ezequiel.camera2.utils.Sharedpreference;
import com.example.ezequiel.camera2.utils.Utils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static android.R.attr.data;

public class AnalysisActivity extends Activity {

    private String userId;
    private List<Customer> customers;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        PieChart chart = (PieChart) findViewById(R.id.chart);

        List<Entry> entries = new ArrayList<Entry>();

//        for (YourData data : dataObjects) {
//
//            // turn your data into Entry objects
//            entries.add(new Entry(data.getValueX(), data.getValueY()));
//        }

        entries.add(new Entry(3, 5));
        entries.add(new Entry(1, 5));
        PieDataSet dataSet = new PieDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(Color.YELLOW);
        dataSet.setValueTextColor(Color.BLACK); // styling, ...

        PieData pieData = new PieData();
        pieData.setDataSet(dataSet);
        chart.setData(pieData);
        chart.invalidate(); // refresh


        userId = Sharedpreference.getUserId(getApplicationContext());
        customers = new ArrayList<>();

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Utils.baseURL +  "data/1";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            HashSet<Integer> ids = new HashSet<>();
                            List<String> imageURLs = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject json = jsonArray.getJSONObject(i);

                                if (ids.contains(json.getInt("id"))) {
                                    imageURLs.add(json.getString("image_address"));
                                    customers.get(customers.size()-1).setImages(imageURLs);
                                } else {
                                    imageURLs = new ArrayList<>();
                                    imageURLs.add(json.getString("image_address"));
                                    customers.add(
                                            new Customer(json.getInt("id")
                                                , json.getInt("age")
                                                , json.getInt("gender")
                                                , imageURLs
                                                , json.getString("date_entered")
                                            )
                                    );
                                    ids.add(json.getInt("id"));
                                }


                            }
                            customers.get(0);

                        } catch (Exception e) {

                        }


                        Log.d("get", "get");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
                Log.d("error", error.getMessage() +"");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
