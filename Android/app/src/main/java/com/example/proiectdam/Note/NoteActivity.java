package com.example.proiectdam.Note;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectdam.Database.model.Nota;
import com.example.proiectdam.Database.service.NotaService;
import com.example.proiectdam.R;
import com.example.proiectdam.informatii.asyncTask.Callback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class NoteActivity extends AppCompatActivity {

    private static final int ADD_NOTA_REQUEST_CODE = 201;
    private static final int UPDATE_NOTA_REQUEST_CODE = 202;
    private ListView lvNote;
    private Button fab;
    private Button graph;


    private List<Nota> noteLista = new ArrayList<>();
    private NotaService notaService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_layout);

        Toast.makeText(this, R.string.room, Toast.LENGTH_SHORT).show();

        fab = findViewById(R.id.btnAdd);
        lvNote = findViewById(R.id.lv_note);
        addNoteAdapter();
        fab.setOnClickListener(addNewNoteEventListener());

        lvNote.setOnItemClickListener(updateEventListener());
        lvNote.setOnItemLongClickListener(deleteEventListener());

        graph = findViewById(R.id.btnGrafic);
        graph.setOnClickListener(veziGrafic());




        notaService = new NotaService(getApplicationContext());
        notaService.getAll(getAllFromDbCallback());

    }

    private AdapterView.OnItemClickListener updateEventListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AdaugareNota.class);
                intent.putExtra(AdaugareNota.NOTA_KEY, noteLista.get(position));
                startActivityForResult(intent, UPDATE_NOTA_REQUEST_CODE);
            }
        };
    }

    private AdapterView.OnItemLongClickListener deleteEventListener() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                notaService.delete(deleteToDbCallback(position), noteLista.get(position));
                return true;
            }
        };
    }


    private Callback<Integer> deleteToDbCallback(final int position) {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUiThread(Integer result) {
                if (result != -1) {
                    noteLista.remove(position);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Nota> updateToDbCallback() {
        return new Callback<Nota>() {
            @Override
            public void runResultOnUiThread(Nota result) {
                if (result != null) {
                    for (Nota nota : noteLista) {
                        if (nota.getId() == result.getId()) {
                            nota.setNota(result.getNota());
                            nota.setDisciplina(result.getDisciplina());
                            nota.setNrCredite(result.getNrCredite());
                            nota.setData(result.getData());
                            break;
                        }
                    }
                    notifyAdapter();
                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Nota n = (Nota) data
                    .getSerializableExtra(AdaugareNota.NOTA_KEY);
            if (requestCode == ADD_NOTA_REQUEST_CODE) {
                notaService.insert(insertIntoDbCallBack(), n);
            }
            else if (requestCode == UPDATE_NOTA_REQUEST_CODE) {
                notaService.update(updateToDbCallback(), n);
            }
        }
    }

    private Callback<List<Nota>> getAllFromDbCallback() {
        return new Callback<List<Nota>>() {
            @Override
            public void runResultOnUiThread(List<Nota> result) {
                if (result != null) {
                    noteLista.clear();
                    noteLista.addAll(result);
                    notifyAdapter();
                }

            }
        };
    }

    private Callback<Nota> insertIntoDbCallBack() {
        return new Callback<Nota>() {
            @Override
            public void runResultOnUiThread(Nota result) {
                if (result != null) {
                    noteLista.add(result);
                    notifyAdapter();
                }
            }
        };
    }


    private View.OnClickListener addNewNoteEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AdaugareNota.class);
                startActivityForResult(intent, ADD_NOTA_REQUEST_CODE);
            }
        };
    }

    private View.OnClickListener veziGrafic() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Nota> list = new ArrayList<>();
                for(Nota n: noteLista)
                    list.add(n);
                Intent intent = new Intent(NoteActivity.this, BarChartActivity.class);
                intent.putExtra("list", list);
                startActivity(intent);
            }
        };

    }

    private void addNoteAdapter() {

        NoteAdapter adapter = new NoteAdapter(getApplicationContext(), R.layout.lv_nota, noteLista, getLayoutInflater());
        lvNote.setAdapter(adapter);
    }
    private void notifyAdapter() {

        NoteAdapter adapter = (NoteAdapter) lvNote.getAdapter();
        adapter.notifyDataSetChanged();
    }



}
