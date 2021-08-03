package com.example.proiectdam.Materii;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proiectdam.Database.model.Materie;
import com.example.proiectdam.Database.service.MaterieService;
import com.example.proiectdam.R;
import com.example.proiectdam.informatii.asyncTask.Callback;
import java.util.ArrayList;
import java.util.List;

public class MateriiActivity extends AppCompatActivity {

    private static final int ADD_MATERIE_REQUEST_CODE = 201;
    private static final int UPDATE_MATERIE_REQUEST_CODE = 202;

    private ListView lvMaterii;
    private Button btn;
    private Button graph;

    private List<Materie> materii = new ArrayList<>();
    private MaterieService materieService;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materii);

        Toast.makeText(this, R.string.room, Toast.LENGTH_SHORT).show();

        lvMaterii = findViewById(R.id.lv_materii);
        btn = findViewById(R.id.btnAdd);
        graph = findViewById(R.id.btnGrafic);
        graph.setOnClickListener(veziGrafic());
        addAdapter();
        btn.setOnClickListener(addMaterieEventListner());
        lvMaterii.setOnItemClickListener(updateEventListener());
        lvMaterii.setOnItemLongClickListener(deleteEventListener());

        materieService = new MaterieService(getApplicationContext());
        materieService.getAll(getAllFromDbCallback());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Materie materie = (Materie) data.getSerializableExtra(AdaugareMaterie.MATERIE_KEY);
            if (requestCode == ADD_MATERIE_REQUEST_CODE) {
                materieService.insert(insertIntoDbCallback(), materie);
            } else if (requestCode == UPDATE_MATERIE_REQUEST_CODE) {
                materieService.update(updateToDbCallback(), materie);
            }
        }
    }

    private Callback<List<Materie>> getAllFromDbCallback() {
        return new Callback<List<Materie>>() {
            @Override
            public void runResultOnUiThread(List<Materie> result) {
                if (result != null) {
                    materii.clear();
                    materii.addAll(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Materie> insertIntoDbCallback() {
        return new Callback<Materie>() {
            @Override
            public void runResultOnUiThread(Materie result) {
                if (result != null) {
                    materii.add(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Materie> updateToDbCallback() {
        return new Callback<Materie>() {
            @Override
            public void runResultOnUiThread(Materie result) {
                if (result != null) {
                    for (Materie materie : materii) {
                        if (materie.getId() == result.getId()) {
                            materie.setTipExaminare(result.getTipExaminare());
                            materie.setNumeProf(result.getNumeProf());
                            materie.setNumeMaterie(result.getNumeMaterie());
                            materie.setNrCredite(result.getNrCredite());
                            break;
                        }
                    }
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Integer> deleteToDbCallback(final int position) {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUiThread(Integer result) {
                if (result != -1) {
                    materii.remove(position);
                    notifyAdapter();
                }
            }
        };
    }


    private AdapterView.OnItemClickListener updateEventListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AdaugareMaterie.class);
                intent.putExtra(AdaugareMaterie.MATERIE_KEY, materii.get(position));
                startActivityForResult(intent, UPDATE_MATERIE_REQUEST_CODE);
            }
        };
    }

    private AdapterView.OnItemLongClickListener deleteEventListener() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                materieService.delete(deleteToDbCallback(position), materii.get(position));
                return true;
            }
        };
    }

    private View.OnClickListener addMaterieEventListner() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdaugareMaterie.class);
                startActivityForResult(intent, ADD_MATERIE_REQUEST_CODE);
            }
        };
    }

    private void addAdapter() {

        MateriiAdapter adapter = new MateriiAdapter(getApplicationContext(), R.layout.lv_materie, materii, getLayoutInflater());
        lvMaterii.setAdapter(adapter);
    }

    private void notifyAdapter() {
        MateriiAdapter adapter = (MateriiAdapter) lvMaterii.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private View.OnClickListener veziGrafic() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Materie> list = new ArrayList<>();
                for(Materie m: materii)
                    list.add(m);
                Intent intent = new Intent(MateriiActivity.this, BarChartActivity2.class);
                intent.putExtra("list", list);
                startActivity(intent);
            }
        };

    }

}
