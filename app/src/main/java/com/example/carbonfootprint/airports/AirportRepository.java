package com.example.carbonfootprint.airports;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AirportRepository {

    private AirportDao mAirportDao;
    private LiveData<List<Airport>> mAllAirports;;

    AirportRepository(Application application){
        AirportDatabase db = AirportDatabase.getDatabase(application);
        mAirportDao = db.airportDAO();
        mAllAirports = mAirportDao.getAirports();
    }

    public LiveData<List<Airport>> getAllAirports(){
      mAllAirports = mAirportDao.getAirports();
      Log.d("fug","benis");
        return mAllAirports;
    }

    public void insert(Airport airport){
        mAirportDao.insert(airport);
    }


}
