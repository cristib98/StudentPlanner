package com.example.proiectdam.Note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ViewUtils;

import com.example.proiectdam.Database.model.Nota;
import com.example.proiectdam.R;

import java.util.Date;
import java.util.List;

public class NoteAdapter extends ArrayAdapter<Nota> {
    private Context context;
    private List<Nota> note;
    private LayoutInflater inflater;
    private int resource;

    public NoteAdapter(@NonNull Context context, int resource,
                              @NonNull List<Nota> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.note = objects;
        this.inflater = inflater;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Nota n = note.get(position);
        if (n != null) {
            addDisciplina(view, n.getDisciplina());
            addCredit(view, n.getNrCredite());
            addNota(view, n.getNota());
            addData(view, n.getData());
        }
        return view;
    }
    private void addDisciplina(View view, String disciplina) {
        TextView textView = view.findViewById(R.id.disciplina);
        populateTextViewContent(textView, "Disciplina: " + disciplina );
    }

    private void addCredit(View view, int credite) {
        TextView textView = view.findViewById(R.id.credite);
        String nrCredite = String.valueOf(credite);
        populateTextViewContent(textView, "Nr. Credite: " + nrCredite);
    }

    private void addNota(View view, int nota) {
        TextView textView = view.findViewById(R.id.nota);
        String notaStr = String.valueOf(nota);
        populateTextViewContent(textView, notaStr);
    }

    private void addData(View view, Date date) {
        TextView tv = view.findViewById(R.id.data);
        populateTextViewContent(tv, DateConverter.fromDate(date));
    }


    private void populateTextViewContent(TextView textView, String value) {
        if (value != null && !value.trim().isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText("");
        }
    }


}
