package com.example.carbonfootprint.airports;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "airports")
public class Airport {



@PrimaryKey
    @ColumnInfo(name = "id")
   public int id;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "country")
    public String country;
@Ignore
    @ColumnInfo(name = "airport")
    public String airport;

    @ColumnInfo(name = "iata")
    public String iata;

    @ColumnInfo(name = "latitude")
    public Double latitude;

    @ColumnInfo(name = "longitude")
    public Double longitude;

    public String getIata(){return this.iata;}

    public Airport(){this.id = 1; this. iata = "nnn";}
}
