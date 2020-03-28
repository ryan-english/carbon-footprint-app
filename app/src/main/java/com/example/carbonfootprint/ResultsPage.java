package com.example.carbonfootprint;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;

import java.util.Iterator;
import java.util.LinkedHashMap;


public class ResultsPage extends AppCompatActivity {
    private TextView label;
    private TextView top;
    private double n;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.HiddenTitleTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);
        label = findViewById(R.id.textTotal);
        top = findViewById(R.id.textResult);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenheight = displayMetrics.heightPixels;
        int screenwidth = displayMetrics.widthPixels;
/*
        EmissionCalculatorTable.initialise();
        EmissionCalculatorTable.calculateAndRecord("type_car",100.0f);
        EmissionCalculatorTable.calculateAndRecord("type_plane", 40.0f);
        EmissionCalculatorTable.calculateAndRecord("type_computer",400.0f);
        EmissionCalculatorTable.calculateAndRecord("type_train",300.0f);
*/
        LinkedHashMap emissionStats = EmissionCalculatorTable.getEmissionTable();

        addPie(emissionStats, screenwidth, screenheight);
        addBar(emissionStats, screenwidth, screenheight);
        addTable(emissionStats, screenwidth, screenheight);
        addTotal(emissionStats);
    }

    public void backToStart(View view)
    {
        Intent back = new Intent(this, MainActivity.class);
        startActivity(back);
    }

    public void backTips (View view)
    {
        Intent tips = new Intent(this, Education.class);
        startActivity(tips);
    }

    private void addPie(LinkedHashMap ht, int screenwidth, int screenheight) {
        PieChart pie = ChartBuilder.buildPieChart(ht,this);
        pie.setMinimumWidth(screenwidth);
        pie.setMinimumHeight(screenheight / 2);

        LinearLayout container = findViewById(R.id.layout);
        container.addView(pie);
    }

    private void addBar(LinkedHashMap ht, int screenwidth, int screenheight) {
        BarChart bar = ChartBuilder.buildBarChart(ht,this);
        bar.setMinimumWidth(screenwidth);
        bar.setMinimumHeight(screenheight / 2);
        LinearLayout container = findViewById(R.id.layout);
        container.addView(bar);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void addTable(LinkedHashMap<String, Float> ht, int screenwidth, int screenheight) {
        TableLayout container = findViewById(R.id.emissionsTable);
        container.addView(ChartBuilder.buildTable(ht,this));
    }

    private void addTotal(LinkedHashMap<String, Float> ht) {
        Iterator<String> iterator = ht.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Float value = ht.get(key);
            n = n + value;
        }
        n = Math.round(n);
        top.setText("Your Weekly C02 Emissions:");
        label.setText(n + "kg");
    }

    public void sendResults(View view){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "My total weekly CO2 emissions is " + n + "kg. Try the CARBN app to find out yours!");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }
    public void weeklyResults (View v){
        top.setText("Your Weekly C02 Emissions:");
        label.setText(n + " kg");
    }

    public void monthlyResults (View v){
        double m = n * 4;
        top.setText("Your Monthly C02 Emissions:");
        label.setText(m + " kg");
    }
    public void annualResults (View v){
        double o = n * 45;
        top.setText("Your Annual C02 Emissions:");
        label.setText(o + " kg");
    }
}