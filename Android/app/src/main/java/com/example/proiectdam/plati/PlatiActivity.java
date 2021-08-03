package com.example.proiectdam.plati;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proiectdam.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PlatiActivity extends AppCompatActivity {


    private static final int ADD_PLATI__REQUEST_CODE = 210;
    private ListView lvPlati;
    private Button fab;

    private List<Plata> platiLista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plati_layout);

        fab = findViewById(R.id.addPlatafab);
        lvPlati = findViewById(R.id.lv_plati);
        addPlatiAdapter();
        fab.setOnClickListener(addNewPlatiEventListener());

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2020);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DATE, 28);
        Date date = calendar.getTime();
        calendar.set(Calendar.YEAR, 2021);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 12);
        Date date2 = calendar.getTime();
        platiLista.add(new Plata("Restanta poo", date, 100));
        platiLista.add(new Plata("Taxa scolarizare",date2,3500));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PLATI__REQUEST_CODE
                && resultCode == RESULT_OK && data != null) {
            Plata p = (Plata) data
                    .getSerializableExtra(AdaugarePlata.PLATA_KEY);
            if (p != null) {
                Toast.makeText(getApplicationContext(),
                        R.string.plata_succes,
                        Toast.LENGTH_SHORT)
                        .show();
                platiLista.add(p);
                notifyAdapter();
            }
        }


    }
    private View.OnClickListener addNewPlatiEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdaugarePlata.class);
                startActivityForResult(intent, ADD_PLATI__REQUEST_CODE);
            }
        };
    }

    private void addPlatiAdapter() {
        PlatiAdapter adapter = new PlatiAdapter(getApplicationContext(),
                R.layout.lv_plata, platiLista, getLayoutInflater());
        lvPlati.setAdapter(adapter);
    }
    private void notifyAdapter() {
        ArrayAdapter adapter = (ArrayAdapter) lvPlati.getAdapter();
        adapter.notifyDataSetChanged();
    }
}
