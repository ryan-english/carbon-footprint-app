package com.example.carbonfootprint.airports;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AirportViewModel extends AndroidViewModel {

    private AirportRepository mRepository;

    private LiveData<List<Airport>> mAllAirports;

    public AirportViewModel(Application application) {
        super(application);
        mRepository = new AirportRepository(application);
        mAllAirports = mRepository.getAllAirports();
    }

    public LiveData<List<Airport>> getAllAirports() {
        Log.d("fug", "ebin");
        return mAllAirports;
    }

    public void insert(Airport airport){mRepository.insert(airport);}
}
