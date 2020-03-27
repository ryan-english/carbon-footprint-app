package com.example.carbonfootprint;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;


/*
Class for making charts with data from hashtables
 */
public class ChartBuilder {

    public static int[] colorset(){
        return new int[]{R.color.pastelPink, R.color.pastelPurple, R.color.pastelBlue,
                        R.color.pastelYellow, R.color.pastelOrange, R.color.pastelPeach,
                        R.color.pastelGreen, R.color.pastelTurquoise, R.color.pastelTeal};
    }

    public static PieChart buildPieChart(LinkedHashMap<String, Float> ht, Context context){
        PieData piedata = new PieData();
        ArrayList<PieEntry> stats = new ArrayList<>();
        Iterator<String> iterator = ht.keySet().iterator();
        while(iterator.hasNext()){ String key = iterator.next();
            Float value = ht.get(key);
            stats.add(new PieEntry(value,getStringResource(key, context)));
        }
        PieDataSet dset = new PieDataSet(stats,"");
        dset.setColors(colorset(),context);
        piedata.setDataSet(dset);

        PieChart chart = new PieChart(context);
        chart.setData(piedata);
        chart.setHoleColor(212121);
        chart.getLegend().setTextColor(ContextCompat.getColor(context,R.color.white));
        return(chart);
    }

    public static BarChart buildBarChart(LinkedHashMap<String, Float> ht, Context context){
        BarData bdata = new BarData();
        Iterator<String> iterator = ht.keySet().iterator();
        int i;
        i=0;
        while(iterator.hasNext()){ String key = iterator.next();
            Float value = ht.get(key);
            ArrayList<BarEntry> stats = new ArrayList<>();
            stats.add(new BarEntry(i, value));
            BarDataSet dset = new BarDataSet(stats, getStringResource(key, context));
            int n = i % colorset().length;
            int[] color = new int[]{colorset()[n]};
            dset.setColors(color,context);
            bdata.addDataSet(dset);
            i++;}

        BarChart chart = new BarChart(context);
        chart.setData(bdata);
        chart.getXAxis().setDrawLabels(false);
        chart.getAxisLeft().setTextColor(ContextCompat.getColor(context,R.color.white));
        chart.getAxisRight().setDrawLabels(false);
        chart.getLegend().setTextColor((ContextCompat.getColor(context,R.color.white)));
        return(chart);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static TableLayout buildTable(LinkedHashMap<String, Float> ht, Context context){
        TableLayout table = new TableLayout(context);

        Iterator<String> iterator = ht.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Float value = ht.get(key);

            TableRow row = new TableRow(context);
            TextView label = new TextView(context);
            TextView number = new TextView(context);
            TextView title = new TextView(context);

            label.setText(getStringResource(key, context));
            label.setTextColor((ContextCompat.getColor(context,R.color.white)));
            label.setBackground(ContextCompat.getDrawable(context,R.drawable.table_border));
            label.setGravity(Gravity.CENTER);

            number.setText(value.toString());
            number.setTextColor(ContextCompat.getColor(context,R.color.white));
            number.setBackground(ContextCompat.getDrawable(context,R.drawable.table_border));
            number.setGravity(Gravity.CENTER);

            title.setText("Activity");
            title.setTextColor(ContextCompat.getColor(context,R.color.white));
            title.setBackground(ContextCompat.getDrawable(context,R.drawable.table_border));

            row.addView(label);
            row.addView(number);
            table.addView(row);
        }
        return table;
    }

    private static String getStringResource(String key,Context context){
        int resId = context.getResources().getIdentifier(key, "string", context.getPackageName());
        String label = context.getString(resId);
        return label;
    }
}
