package com.example.carbonfootprint;

//This is a message to test Github desktop

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EmissionCalculatorTable.initialise();
    }

    public void openSurvey(View view)
    {
        Intent survey = new Intent(this, UserInput.class);
        startActivity(survey);
    }
}
