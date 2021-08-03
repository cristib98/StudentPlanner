package com.example.proiectdam.informatii;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proiectdam.R;

import java.util.List;

public class InformatiiAdapter extends ArrayAdapter<Facultate> {

    private Context context;
    private List<Facultate> facultati;
    private LayoutInflater inflater;
    private int resource;

    public InformatiiAdapter(@NonNull Context context, int resource,
                       @NonNull List<Facultate> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.facultati = objects;
        this.inflater = inflater;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Facultate f = facultati.get(position);
        if (f != null) {
            addNrTel(view, f.getNrTel());
            addNume(view, f.getNume());
            addEmail(view, f.getEmail());
        }
        return view;
    }
    private void addNrTel(View view, String nrTel) {
        TextView textView = view.findViewById(R.id.nrTel);
        populateTextViewContent(textView, nrTel  );
    }

    private void addEmail(View view, String email) {
        TextView textView = view.findViewById(R.id.email);
        populateTextViewContent(textView, email  );
    }

    private void addNume(View view, String nume) {
        TextView textView = view.findViewById(R.id.numeFac);
        populateTextViewContent(textView, nume);
    }


    private void populateTextViewContent(TextView textView, String value) {
        if (value != null && !value.trim().isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText("");
        }
    }




}
