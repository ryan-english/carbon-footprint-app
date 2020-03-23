package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;

import java.util.Iterator;
import java.util.LinkedHashMap;


public class ResultsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);

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

    public void backFacility(View view)
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

    private void addTable(LinkedHashMap<String, Float> ht, int screenwidth, int screenheight) {
        LinearLayout container = findViewById(R.id.layout);
        container.addView(ChartBuilder.buildTable(ht,this));
    }

    private void addTotal(LinkedHashMap<String, Float> ht) {
        double n = 0;
        Iterator<String> iterator = ht.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Float value = ht.get(key);
            n = n + value;
        }
        n = Math.round(n);
        TextView label = findViewById(R.id.textTotal);
        label.setText("Total weekly CO2 Emissions: " +n+ "kg");
    }
}