package com.example.ezequiel.camera2;

import android.app.Activity;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AnalysisActivity extends Activity {

    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userId = Sharedpreference.getUserId(getApplicationContext());
        List<Customer> customers = new ArrayList<>();

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
