package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Input3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input3);
    }

    public void results(View view)
    {
        Intent result = new Intent(this, ResultsPage.class);
        startActivity(result);
    }

    public void backElectric(View view)
    {
        Intent back = new Intent(this, userInput2.class);
        startActivity(back);
    }
}
