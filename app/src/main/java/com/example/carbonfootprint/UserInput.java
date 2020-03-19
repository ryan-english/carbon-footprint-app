package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
    }

    public void electric(View view)
    {
        Intent next = new Intent(this, userInput2.class);
        startActivity(next);
    }

    public void backHome(View view)
    {
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }
}
