package com.example.carbonfootprint;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class AlternateEducation extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.HiddenTitleTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternate_education);
    }

    public void backToStart(View view)
    {
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

}
