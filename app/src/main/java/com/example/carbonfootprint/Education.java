package com.example.carbonfootprint;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Education extends AppCompatActivity {

    public static Button resultButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.HiddenTitleTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        resultButton = findViewById(R.id.resultButton);
    }

    public void backToStart(View view)
    {
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    public void toResults(View view)
    {
        Intent results = new Intent(this, ResultsPage.class);
        startActivity(results);
    }
}
