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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

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

        PieChart pieChart = (PieChart) findViewById(R.id.chart);
//        LineChart chart = (LineChart) findViewById(R.id.chart_line);
//        // programmatically create a LineChart
        List<Entry> entrie = new ArrayList<Entry>();
////        entries.add(new Entry(0f, 10f));
////        entries.add(new Entry(1f, 20f));
////        entries.add(new Entry(2f, 30f));
//        entries.add(new Entry(0, 1));
//        entries.add(new Entry(1, 2));
//        entries.add(new Entry(3, 3));
//        entries.add(new Entry(4, 0));
//
        LineDataSet set = new LineDataSet(entrie, "Election Results");
////
////        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
////        dataSets.add(set);
//
//        LineData data = new LineData();
//        data.addDataSet(set);
//        chart.setData(data);
//
////        chart.setDescription("");
//        chart.setNoDataText("No Chart Data"); // this is the top line
//        chart.invalidate();
////        chart.invalidate();
//        chart.in.


//        List<PieEntry> entries = new ArrayList<>();
//
//        entries.add(new PieEntry(18.5f, "Green"));
//        entries.add(new PieEntry(26.7f, "Yellow"));
//        entries.add(new PieEntry(24.0f, "Red"));
//        entries.add(new PieEntry(30.8f, "Blue"));
//
//        PieDataSet set = new PieDataSet(entries, "Election Results");
//        PieData data = new PieData(set);
//        pieChart.setData(data);
//        pieChart.invalidate(); // refresh

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(18.5f, "Green"));
        entries.add(new PieEntry(26.7f, "Yellow"));
        entries.add(new PieEntry(24.0f, "Red"));
        entries.add(new PieEntry(30.8f, "Blue"));


        PieDataSet dataSet = new PieDataSet(entries, "test");
        dataSet.addEntry(new PieEntry(30.8f, "Blue"));
        dataSet.addEntry(new PieEntry(30.8f, "Blue"));
        dataSet.setColor(Color.YELLOW);
        dataSet.setValueTextColor(Color.BLACK); // styling, ...

        PieData pieData = new PieData();
        pieData.setDataSet(dataSet);
        pieChart.setData(pieData);
        pieChart.invalidate(); // refresh






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
