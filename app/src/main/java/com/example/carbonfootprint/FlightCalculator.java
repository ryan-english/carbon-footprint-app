package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import com.example.carbonfootprint.airports.AirportDatabase;


public class FlightCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_calculator);

        Room.databaseBuilder(this, AirportDatabase.class, "air.db")
                .createFromAsset("database/airports.db")
                .build();

    }

}
