package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.carbonfootprint.airports.Airport;
import com.example.carbonfootprint.airports.AirportViewModel;
import com.example.carbonfootprint.airports.JWinnHaversine;
import com.example.carbonfootprint.airports.MyAirportRecyclerViewAdapter;

import java.util.List;


public class FlightCalculator extends AppCompatActivity {

    private RecyclerView departure;
    RecyclerView arrival;
    private AirportViewModel mAirportViewModel;
    private MyAirportRecyclerViewAdapter depAdapter;
    private MyAirportRecyclerViewAdapter arrAdapter;
    private Double departureLatitude = 0.0;
    private Double departureLongitude = 0.0;
    private Double arrivalLatitude = 0.0;
    private Double arrivalLongitude = 0.0;
    private EditText hotel;
    private TextView depIata;
    private TextView arrIata;


    private final View.OnClickListener depOnClickListener = new View.OnClickListener(){
     @Override
     public void onClick(View v){int pos = departure.getChildAdapterPosition(v);
     Log.d("fug",mAirportViewModel.getAllAirports().getValue().get(pos).getIata());
     departureLatitude = mAirportViewModel.getAllAirports().getValue().get(pos).latitude;
     departureLongitude = mAirportViewModel.getAllAirports().getValue().get(pos).latitude;
     depIata.setText(mAirportViewModel.getAllAirports().getValue().get(pos).iata);
     }
    };

    private final View.OnClickListener arrOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){int pos = arrival.getChildAdapterPosition(v);
            Log.d("fug",mAirportViewModel.getAllAirports().getValue().get(pos).getIata());
            arrivalLatitude = mAirportViewModel.getAllAirports().getValue().get(pos).latitude;
            arrivalLongitude = mAirportViewModel.getAllAirports().getValue().get(pos).latitude;
            arrIata.setText(mAirportViewModel.getAllAirports().getValue().get(pos).iata);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_calculator);
        EmissionCalculatorTable.initialise();
        depIata = findViewById(R.id.fc_departure);
        arrIata = findViewById(R.id.fc_arrival);
        hotel = findViewById(R.id.fc_hotel);
        departure = findViewById(R.id.recycler_air_departure);
        arrival = findViewById(R.id.recycler_air_arrival);
        depAdapter = new MyAirportRecyclerViewAdapter(this, depOnClickListener);
        arrAdapter = new MyAirportRecyclerViewAdapter(this, arrOnClickListener);
        departure.setAdapter(depAdapter);
        arrival.setAdapter(arrAdapter);
        arrival.setLayoutManager(new LinearLayoutManager(this));
        departure.setLayoutManager(new LinearLayoutManager(this));

        mAirportViewModel = new ViewModelProvider(this).get(AirportViewModel.class);
       mAirportViewModel.getAllAirports().observe(this, new Observer<List<Airport>>(){
               public void onChanged( final List<Airport> airports){
                   depAdapter.setValues(airports);
                   arrAdapter.setValues(airports);
               }
       });
       depAdapter.notifyDataSetChanged();
    }



    public void onClickCalculateDistance(View view) {
        //using Jason Winn's Haversine class from https://github.com/jasonwinn/haversine.git
        double flightDistance = JWinnHaversine.distance(departureLatitude,departureLongitude,arrivalLatitude,arrivalLongitude);
        Float fd = new Float(flightDistance);
        Float hotelNights = Float.valueOf(hotel.getText().toString());
        EmissionCalculatorTable.calculateAndRecord("type_plane",fd);
        EmissionCalculatorTable.calculateAndRecord("type_hotel",hotelNights);
       Intent next;
       next = new Intent(this,ResultsPage.class);
       startActivity(next);




    }
}
