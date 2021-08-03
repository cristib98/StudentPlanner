package com.example.proiectdam.Materii;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proiectdam.Database.model.Materie;
import com.example.proiectdam.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class AdaugareMaterie extends AppCompatActivity {

    public static String MATERIE_KEY = "materie_key";
    private TextInputEditText tietNumeMaterie;
    private TextInputEditText tietCredite;
    private TextInputEditText tietNumeProf;
    private Button btn;
    private Spinner spnCategory;
    private Intent intent;

    private Materie materie = null;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adaugare_materie);

        tietNumeMaterie=findViewById(R.id.tiet_materie);
        tietCredite=findViewById(R.id.tiet_credite);
        tietNumeProf=findViewById(R.id.tiet_profesor);
        spnCategory = findViewById(R.id.spinner);

        btn = findViewById(R.id.btnAdd);
        addTipExaminareAdapter();
        btn.setOnClickListener(saveMaterieEventListner());


        intent = getIntent();
        if (intent.hasExtra(MATERIE_KEY)) {
            materie = (Materie) intent.getSerializableExtra(MATERIE_KEY);
            buildViewsFromMaterie(materie);
        }

    }

    private void buildViewsFromMaterie(Materie materie) {
        if (materie == null) {
            return;
        }
        tietCredite.setText(String.valueOf(materie.getNrCredite()));

        tietNumeProf.setText(materie.getNumeProf());
        tietNumeMaterie.setText(materie.getNumeMaterie());
        selectTipExamen(materie);
    }

    private void selectTipExamen(Materie materie) {
        ArrayAdapter adapter = (ArrayAdapter) spnCategory.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            String item = (String) adapter.getItem(i);
            if (item != null && item.equals(materie.getTipExaminare())) {
                spnCategory.setSelection(i);
                break;
            }
        }
    }
    private View.OnClickListener saveMaterieEventListner() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    createFromViews();
                    intent.putExtra(MATERIE_KEY, materie);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private void createFromViews() {
        String tipExamen = spnCategory.getSelectedItem().toString();
        Integer credite = Integer.parseInt(tietCredite.getText().toString());
        String numeProf = tietNumeProf.getText().toString();
        String numeMaterie = tietNumeMaterie.getText().toString();
        if (materie == null) {
            materie = new Materie(numeMaterie, credite, numeProf, tipExamen);
        } else {
            materie.setNrCredite(credite);
            materie.setNumeMaterie(numeMaterie);
            materie.setNumeProf(numeProf);
            materie.setTipExaminare(tipExamen);
        }
    }

    private void addTipExaminareAdapter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.spinnerValues,
                android.R.layout.simple_spinner_dropdown_item);
        spnCategory.setAdapter(adapter);
    }

    private boolean validate() {
        if (tietNumeMaterie.getText() == null) {
            Toast.makeText(getApplicationContext(),
                    R.string.nume_materie,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (tietNumeProf.getText() == null) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_nume_prof,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (tietCredite.getText() == null) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_nr_credite,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }


        return true;
    }


}
