package com.example.carbonfootprint.airports;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Airport {
@PrimaryKey
public int id;

    public String name;

    public String city;

    public String country;

    public String iata;


    public Double latitude;

    public Double longitude;

}
