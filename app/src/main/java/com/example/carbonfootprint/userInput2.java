package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class userInput2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input2);
    }

    public void facilityPage(View view)
    {
        Intent facility = new Intent(this, Input3.class);
        startActivity(facility);
    }

    public void backTravel(View view)
    {
        Intent back = new Intent(this, UserInput.class);
        startActivity(back);
    }
}
