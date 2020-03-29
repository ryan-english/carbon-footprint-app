package com.example.carbonfootprint.airports;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carbonfootprint.R;

import java.util.List;


public class MyAirportRecyclerViewAdapter extends RecyclerView.Adapter<MyAirportRecyclerViewAdapter.AirportViewHolder> {

    private List<Airport> mValues;
    private LayoutInflater mInflater;


    public MyAirportRecyclerViewAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }



    @Override
    public AirportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater
                .inflate(R.layout.fragment_airport, parent, false);
        return new AirportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AirportViewHolder holder, int position) {
        if(mValues != null) {
        //    holder.airportName.setText(mValues.get(position).name);
            holder.airportCode.setText(mValues.get(position).iata);
        }
    }

    @Override
    public int getItemCount() {
        if(mValues != null){return mValues.size();}
        else return 0;
    }


    class AirportViewHolder extends RecyclerView.ViewHolder {
        private final TextView airportName;
        private final TextView airportCode;

        private AirportViewHolder(View view) {
            super(view);
            airportName = (TextView) view.findViewById(R.id.airport_name);
            airportCode = (TextView) view.findViewById(R.id.airport_code);

        }
    }

        public void setValues(List<Airport> airports){
            mValues = airports;
            notifyDataSetChanged();
            Log.d("fug",String.valueOf(getItemCount()));
            Log.d("fug", String.valueOf(mValues.get(1).getIata()));
        }
    }
