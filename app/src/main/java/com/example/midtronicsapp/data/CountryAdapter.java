package com.example.midtronicsapp.data;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midtronicsapp.R;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{

    public interface CountryClickListner{
        void onListItemClick(int position);
    }

    private List<Country> countries;
    final private CountryClickListner mOnClickListener;

    public CountryAdapter(List<Country> countries, CountryClickListner countryClickListner) {
        this.countries = countries;
        this.mOnClickListener = countryClickListner;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public Button countryButton;


        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            countryButton = itemView.findViewById(R.id.country_button);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("Sathvik","Came to onclick in adapter");
            int position = getAdapterPosition();
            mOnClickListener.onListItemClick(position);
        }
    }





    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.country_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Country country = countries.get(position);

        // Set item views based on your views and data model

        Button button = holder.countryButton;
        button.setText(country.getName());
    }


    @Override
    public int getItemCount() {
        return countries.size();
    }
}
