package com.example.midtronicsapp.data;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.midtronicsapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CountryInfo extends AppCompatActivity {

    TextView countryTV;
    TextView capitalTV;
    TextView populationTV;
    TextView areaTV;
    TextView regionTV;
    TextView subregionTV;

    private static final String URL = "https://restcountries.com/v3.1/name/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_info);

        Bundle bundle = getIntent().getExtras();
        String country_name = bundle.getString("country_name");
        RequestQueue queue = Volley.newRequestQueue(this);
        getCountryInfo(queue, country_name);

        countryTV  = findViewById(R.id.country_name);
        countryTV.setText(country_name);

        capitalTV    = findViewById(R.id.capital);
        populationTV = findViewById(R.id.population);
        areaTV       = findViewById(R.id.area);
        regionTV     = findViewById(R.id.region);
        subregionTV  = findViewById(R.id.sub_region);


    }

    private void getCountryInfo(RequestQueue queue, String countryName) {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL+countryName,
                new Response.Listener<String>() {
                    @Override
                    public String toString() {
                        return "$classname{}";
                    }

                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("Sathvik", response);
                        JSONArray array = null;
                        try {
                            array = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            JSONObject object = array.getJSONObject(0);


                            capitalTV.setText(object.getJSONArray("capital").getString(0));
                            areaTV.setText(object.getString("area"));
                            populationTV.setText(object.getString("population"));
                            regionTV.setText(object.getString("region"));
                            subregionTV.setText(object.getString("subregion"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                countryTV.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


}
