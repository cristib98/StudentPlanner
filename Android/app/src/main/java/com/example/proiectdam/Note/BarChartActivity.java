package com.example.proiectdam.Note;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proiectdam.Database.model.Nota;
import com.example.proiectdam.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarChartActivity extends AppCompatActivity {

    ArrayList<Nota> list;
    LinearLayout layout;
    Map<Integer, Integer> source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafic_bare);


        Intent intent = getIntent();


        list = (ArrayList<Nota>) intent.getSerializableExtra("list");

        source = getSource(list);

        layout = findViewById(R.id.layoutBar);
        layout.addView(new BarChartview(getApplicationContext(), source));
    }

    private Map<Integer, Integer> getSource(List<Nota> note)
    {
        if(note==null || note.isEmpty())
            return new HashMap<>();
        else
        {
            Map<Integer, Integer> results = new HashMap<>();
            for(Nota n: note)
                if(results.containsKey(n.getNota()))
                    results.put(n.getNota(), results.get(n.getNota())+1);
                else
                    results.put(n.getNota(), 1);
            return results;
        }
    }
}
