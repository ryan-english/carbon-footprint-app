package com.example.carbonfootprint.airports;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AirportDAO {
    @Query("SELECT * FROM airport")
    List<Airport> getAirports();

    @Query("SELECT id, name, IATA FROM airport")
    List<Airport> getIdentifiers();
}
