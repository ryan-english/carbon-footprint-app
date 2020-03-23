package com.example.carbonfootprint;

//This is a message to test Github desktop

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EmissionCalculatorTable.initialise();

        TextView appInfo = findViewById(R.id.appInfo);
        appInfo.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);

        TextView tipsInfo = findViewById(R.id.tipsInfo);
        tipsInfo.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
    }

    public void openSurvey(View view)
    {
        Intent survey = new Intent(this, UserInput.class);
        startActivity(survey);
    }

    public void openTips (View view)
    {
        Intent tips = new Intent(this, Education.class);
        startActivity(tips);
    }
}
