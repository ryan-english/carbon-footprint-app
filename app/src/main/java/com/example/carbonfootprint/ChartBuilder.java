package com.example.carbonfootprint;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;


/*
Class for making charts with data from hashtables
 */
public class ChartBuilder {

    public static int[] colorset(){
        return new int[]{R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent};
    }

    public static PieChart buildPieChart(Hashtable<String,Float> ht, Context context){
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
        return(chart);
    }

    public static BarChart buildBarChart(Hashtable<String,Float> ht, Context context){
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
        return(chart);
    }

    public static TableLayout buildTable(Hashtable<String,Float> ht, Context context){
        TableLayout table = new TableLayout(context);
        Iterator<String> iterator = ht.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Float value = ht.get(key);
            TableRow row = new TableRow(context);
            TextView label = new TextView(context);
            TextView number = new TextView(context);
            label.setText(getStringResource(key, context));

            number.setText(value.toString());
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
