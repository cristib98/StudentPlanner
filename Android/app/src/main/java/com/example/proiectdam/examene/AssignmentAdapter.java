package com.example.proiectdam.examene;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proiectdam.Database.model.Assignment;
import com.example.proiectdam.Note.DateConverter;
import com.example.proiectdam.R;

import java.util.Date;
import java.util.List;


public class AssignmentAdapter extends ArrayAdapter<Assignment> {
    private Context context;
    private List<Assignment> assignments;
    private LayoutInflater inflater;
    private int resource;

    public AssignmentAdapter(@NonNull Context context, int resource,
                       @NonNull List<Assignment> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.assignments = objects;
        this.inflater = inflater;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Assignment as = assignments.get(position);
        if (as != null) {
            addDescriere(view, as.getDescriere());
            addData(view, as.getData());
        }
        return view;
    }

    private void addDescriere(View view, String descriere) {
        TextView textView = view.findViewById(R.id.descriere);
        populateTextViewContent(textView,  descriere );
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
