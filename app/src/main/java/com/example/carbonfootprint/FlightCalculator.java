package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;;

import android.os.Bundle;
import android.view.View;

import com.example.carbonfootprint.airports.Airport;
import com.example.carbonfootprint.airports.AirportViewModel;
import com.example.carbonfootprint.airports.MyAirportRecyclerViewAdapter;

import java.util.List;


public class FlightCalculator extends AppCompatActivity {

    private RecyclerView departure;
    RecyclerView arrival;
    private AirportViewModel mAirportViewModel;
    private MyAirportRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_calculator);
        departure = findViewById(R.id.recycler_air_departure);
        arrival = findViewById(R.id.recycler_air_arrival);
        adapter = new MyAirportRecyclerViewAdapter(this);
        departure.setAdapter(adapter);
        arrival.setAdapter(adapter);
        arrival.setLayoutManager(new LinearLayoutManager(this));
        departure.setLayoutManager(new LinearLayoutManager(this));

        mAirportViewModel = new ViewModelProvider(this).get(AirportViewModel.class);
       mAirportViewModel.getAllAirports().observe(this, new Observer<List<Airport>>(){
               public void onChanged( final List<Airport> airports){
                   adapter.setValues(airports);
               }
       });
       adapter.notifyDataSetChanged();
    }

    public void doThing(View view) {
        LiveData<List<Airport>> l = mAirportViewModel.getAllAirports();
        adapter.setValues(l.getValue());
    }
}
