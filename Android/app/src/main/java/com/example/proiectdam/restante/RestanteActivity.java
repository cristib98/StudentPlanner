package com.example.proiectdam.restante;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectdam.Database.model.Nota;
import com.example.proiectdam.Database.service.NotaService;
import com.example.proiectdam.Note.NoteAdapter;
import com.example.proiectdam.R;
import com.example.proiectdam.informatii.asyncTask.Callback;

import java.util.ArrayList;
import java.util.List;

public class RestanteActivity  extends AppCompatActivity {

    private ListView lvNote;

    private List<Nota> noteLista = new ArrayList<>();
    private NotaService notaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restante_layout);

        lvNote = findViewById(R.id.lv_note);
        addNoteAdapter();

        notaService = new NotaService(getApplicationContext());
        notaService.getAllRestante(getAllFromDbCallback());

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

    private void addNoteAdapter() {

        NoteAdapter adapter = new NoteAdapter(getApplicationContext(), R.layout.lv_nota, noteLista, getLayoutInflater());
        lvNote.setAdapter(adapter);
    }
    private void notifyAdapter() {

        NoteAdapter adapter = (NoteAdapter) lvNote.getAdapter();
        adapter.notifyDataSetChanged();
    }


}
