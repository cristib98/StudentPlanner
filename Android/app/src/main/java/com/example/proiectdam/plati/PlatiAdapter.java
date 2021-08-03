package com.example.proiectdam.plati;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proiectdam.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PlatiAdapter extends ArrayAdapter<Plata> {
    private Context context;
    private List<Plata> plati;
    private LayoutInflater inflater;
    private int resource;

    public PlatiAdapter(@NonNull Context context, int resource,
                       @NonNull List<Plata> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.plati = objects;
        this.inflater = inflater;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Plata p = plati.get(position);
        if (p != null) {
            addDescriere(view, p.getDescriere());
            addData(view, p.getData());
            addSuma(view, p.getSuma());
        }
        return view;
    }
    private void addDescriere(View view, String descriere) {
        TextView textView = view.findViewById(R.id.Descriere);
        populateTextViewContent(textView, descriere);
    }

    private void addData(View view, Date data) {
        TextView textView = view.findViewById(R.id.dataLimita);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.applyPattern("dd/MM/yyyy");
        String newDate = df.format(data);

        populateTextViewContent(textView, "Data limita: " + newDate);
    }

    private void addSuma(View view, float suma) {
        TextView textView = view.findViewById(R.id.suma);
        String sumaStr = String.valueOf(suma);
        populateTextViewContent(textView, sumaStr + " lei.");
    }

    private void populateTextViewContent(TextView textView, String value) {
        if (value != null && !value.trim().isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText("");
        }
    }
}
