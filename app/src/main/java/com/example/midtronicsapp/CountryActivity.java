package com.example.midtronicsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.midtronicsapp.data.Country;
import com.example.midtronicsapp.data.CountryAdapter;
import com.example.midtronicsapp.data.CountryInfo;

import java.util.ArrayList;

public class CountryActivity extends AppCompatActivity implements CountryAdapter.CountryClickListner{
    private static final String TAG = "Sathvik";
    ArrayList<Country> countries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        // Lookup the recyclerview in activity layout
        RecyclerView rvCountries =  findViewById(R.id.rvCountries);

        // Initialize contacts
         countries = load_countries();
        // Create adapter passing in the sample user data
        CountryAdapter adapter = new CountryAdapter(countries, this);
        // Attach the adapter to the recyclerview to populate items
        rvCountries.setAdapter(adapter);
        // Set layout manager to position the items
        rvCountries.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

    }

    private ArrayList<Country> load_countries() {
        ArrayList<Country> countryList = new ArrayList<>();
        final String[] countries = getResources().getStringArray(R.array.countries_array);
        for(String country_name : countries){
            countryList.add(new Country(country_name));
        }
        return countryList;
    }

    @Override
    public void onListItemClick(int position) {

        Intent intent = new Intent(CountryActivity.this, CountryInfo.class);
        intent.putExtra("country_name", countries.get(position).getName());

        // Try to invoke the intent.
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
          Log.d(TAG,"Activity not found");
        }
    }


}