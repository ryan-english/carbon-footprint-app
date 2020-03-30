package com.example.carbonfootprint.airports;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AirportDao {
    @Query("SELECT * FROM airports ORDER BY iata")
   public LiveData<List<Airport>> getAirports();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Airport airport);

}
