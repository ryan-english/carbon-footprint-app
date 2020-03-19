package com.example.carbonfootprint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class UserInput extends AppCompatActivity {
    private int stage;
    private int backlines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
        LinearLayout layout = findViewById(R.id.scrollLayout);
        TextView title = findViewById(R.id.title);

        Intent intent = getIntent();
        stage = intent.getIntExtra("NEXT_STAGE",0);
        backlines = intent.getIntExtra("BACK_LINES",0);


        switch (stage){
            case 0:
                title.setText(R.string.travelTitle);
                layout.addView(getLayoutInflater().inflate(R.layout.question_public_transport,null));
                layout.addView(getLayoutInflater().inflate(R.layout.question_car,null));
                layout.addView(getLayoutInflater().inflate(R.layout.question_plane,null));
                break;
            case 1:
                title.setText(R.string.electricTitle);
                title.setTextColor(getResources().getColor(R.color.orange));
                layout.addView(getLayoutInflater().inflate(R.layout.question_pc,null));
                layout.addView(getLayoutInflater().inflate(R.layout.question_laptop,null));
                layout.addView(getLayoutInflater().inflate(R.layout.question_printer,null));
                break;
            case 2:
                title.setText(R.string.facilitiesTitle);
                title.setTextColor(getResources().getColor(R.color.colorPrimary));
                layout.addView(getLayoutInflater().inflate(R.layout.question_gym,null));
                layout.addView(getLayoutInflater().inflate(R.layout.question_library,null));
                layout.addView(getLayoutInflater().inflate(R.layout.question_lectures,null));
                break;
        }
    }


    public void toNext(View view) {

        LinearLayout layout = findViewById(R.id.scrollLayout);
        List<View> children = getAllChildrenBFS(layout);
        ArrayList<RadioGroup> questions = new ArrayList<>();

        for(View v : children) {
            if (v instanceof RadioGroup) {
                questions.add((RadioGroup) v);
            }
        }

        for(RadioGroup r : questions){
            if(r.getCheckedRadioButtonId() != -1) {
                String category = (String) r.getTag();
                RadioButton b = findViewById(r.getCheckedRadioButtonId());
                String n = (String) b.getTag();
                Float answer = Float.parseFloat(n);
                EmissionCalculatorTable.calculateAndRecord(category, answer);
                backlines++;
            }}

        stage++;
        Intent next;
        if(stage == 3){next = new Intent(this,ResultsPage.class);}
        else {next = new Intent(this, UserInput.class);
        next.putExtra("NEXT_STAGE", stage);
        }
        next.putExtra("BACK_LINES",backlines);
        startActivity(next);

    }

    private List<View> getAllChildrenBFS(View v) {
        List<View> visited = new ArrayList<>();
        List<View> unvisited = new ArrayList<>();
        unvisited.add(v);

        while (!unvisited.isEmpty()) {
            View child = unvisited.remove(0);
            visited.add(child);
            if (!(child instanceof ViewGroup)) continue;
            ViewGroup group = (ViewGroup) child;
            final int childCount = group.getChildCount();
            for (int i=0; i<childCount; i++) unvisited.add(group.getChildAt(i));
        }

        return visited;
    }

    public void back(View view)
    {
        if(stage==0) {
            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);
        }
        else{
            Intent back = new Intent(this, UserInput.class);
            stage--;
            back.putExtra("NEXT_STAGE",stage);
            for(int i=0 ; i<backlines ; i++){EmissionCalculatorTable.removeLast();}
            startActivity(back);

        }
    }
}
