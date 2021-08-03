package com.example.proiectdam.Materii;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proiectdam.Database.model.Materie;
import com.example.proiectdam.R;

import java.util.List;

public class MateriiAdapter extends ArrayAdapter<Materie> {

    private Context context;
    private int resource;
    private List<Materie> materii;
    private LayoutInflater inflater;

    public MateriiAdapter(@NonNull Context context, int resource, @NonNull List<Materie> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.materii = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Materie materie = materii.get(position);
        if (materie != null) {
            addNumeMaterie(view, materie.getNumeMaterie());
            addNrCredite(view, materie.getNrCredite());
            addTipExaminare(view, materie.getTipExaminare());
            addNumeProf(view, materie.getNumeProf());
        }
        return view;
    }
    private void addNumeMaterie(View view, String numeMaterie){
        TextView tv = view.findViewById(R.id.nume);
        addTextViewContent(tv, numeMaterie);
    }

    private void addNrCredite(View view, int nrCredite){
        TextView tv = view.findViewById(R.id.credite);
        String crediteStr = String.valueOf(nrCredite);
        addTextViewContent(tv, crediteStr+" credite");
    }

    private void addTipExaminare(View view, String tipExaminare){
        TextView tv = view.findViewById(R.id.examinare);
        addTextViewContent(tv, tipExaminare);
    }

    private void addNumeProf(View view, String numeProf){
        TextView tv = view.findViewById(R.id.prof);
        addTextViewContent(tv, "Profesor: " + numeProf);
    }

    private void addTextViewContent(TextView textView, String value) {
        if (value != null && !value.isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText("");
        }
    }




}
