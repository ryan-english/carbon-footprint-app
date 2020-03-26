package com.example.carbonfootprint.airports;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Airport.class}, version =1)
public abstract class AirportDatabase extends RoomDatabase {
    public abstract AirportDAO airportDAO();
}
