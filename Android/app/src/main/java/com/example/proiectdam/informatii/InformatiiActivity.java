package com.example.proiectdam.informatii;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectdam.R;
import com.example.proiectdam.informatii.asyncTask.Callback;

import com.example.proiectdam.informatii.asyncTask.asyncTaskRunner;
import com.example.proiectdam.informatii.network.HttpManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class InformatiiActivity extends AppCompatActivity {

    private ListView lvInfo;
    private List<Facultate> infoLista = new ArrayList<>();
    public static final String INFO_URL = "https://jsonkeeper.com/b/9O37";
    private asyncTaskRunner atr = new asyncTaskRunner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informatii_layout);

        lvInfo = findViewById(R.id.lv_info);
        addInfoAdapter();

        getInfoFromHttp();

    }

    private void addInfoAdapter() {

        InformatiiAdapter adapter = new InformatiiAdapter(getApplicationContext(),
                R.layout.lv_info, infoLista, getLayoutInflater());
        lvInfo.setAdapter(adapter);
    }

    private void notifyAdapter() {
        ArrayAdapter adapter = (ArrayAdapter) lvInfo.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void getInfoFromHttp() {
        Callable<String> asyncOperation = new HttpManager(INFO_URL);
        Callback<String> mainThreadOperation = receiveInfoFromHttp();
        atr.executeAsync(asyncOperation, mainThreadOperation);
    }

    private Callback<String> receiveInfoFromHttp() {
        return new Callback<String>() {
            @Override
            public void runResultOnUiThread(String result) {
                Toast.makeText(InformatiiActivity.this, "Date preluate din JSON!", Toast.LENGTH_SHORT).show();

                infoLista.addAll(InformatiiJsonParser.fromJson(result));

                notifyAdapter();
            }
        };
    }

}
