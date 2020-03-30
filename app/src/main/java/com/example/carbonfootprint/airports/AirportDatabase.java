package com.example.carbonfootprint.airports;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Airport.class}, version =1)
public abstract class AirportDatabase extends RoomDatabase {
    public abstract AirportDao airportDAO();

    private static volatile AirportDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AirportDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized(AirportDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AirportDatabase.class, "db_airports")
                            .createFromAsset("database/airports.db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                    Log.d("fug","made database");
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                AirportDao dao = INSTANCE.airportDAO();


                Airport word = new Airport();
                dao.insert(word);
                Log.d("fug",word.getIata());
            });
        }
    };

}
