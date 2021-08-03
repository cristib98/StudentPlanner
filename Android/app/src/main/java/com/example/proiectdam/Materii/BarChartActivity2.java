package com.example.proiectdam.Materii;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectdam.Database.model.Materie;
import com.example.proiectdam.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarChartActivity2 extends AppCompatActivity {

    ArrayList<Materie> list;
    LinearLayout layout;
    Map<String, Integer> source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafic_tip_examene);

        Intent intent = getIntent();

        list = (ArrayList<Materie>) intent.getSerializableExtra("list");

        source = getSource(list);

        layout = findViewById(R.id.layoutBar);
        layout.addView(new BarChartview2(getApplicationContext(), source));

    }

    private Map<String, Integer> getSource(List<Materie> materii)
    {
        if(materii==null || materii.isEmpty())
            return new HashMap<>();
        else
        {
            Map<String, Integer> results = new HashMap<>();
            for(Materie materie: materii)
                if(results.containsKey(materie.getTipExaminare()))
                    results.put(materie.getTipExaminare(), results.get(materie.getTipExaminare())+1);
                else
                    results.put(materie.getTipExaminare(), 1);
            return results;
        }
    }

}
