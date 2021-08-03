package com.example.proiectdam.Note;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proiectdam.Database.model.Nota;
import com.example.proiectdam.R;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Date;


public class AdaugareNota extends AppCompatActivity {

    public static String NOTA_KEY = "nota_key";
    private TextInputEditText tietDisciplina;
    private TextInputEditText tietCredite;
    private TextInputEditText tietData;
    private NumberPicker np;
    private Button btn;
    private Intent intent;
    int notaPicker;

    private Nota nota = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adaugare_nota);

        np = findViewById(R.id.np);
        np.setMinValue(0);
        np.setMaxValue(10);
        np.setWrapSelectorWheel(true);

        tietDisciplina = findViewById(R.id.tiet_materie);
        tietCredite = findViewById(R.id.tiet_credite);
        tietData = findViewById(R.id.tiet_data);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                notaPicker = np.getValue();
            }
        });

        intent = getIntent();
        if (intent.hasExtra(NOTA_KEY)) {
            nota = (Nota) intent.getSerializableExtra(NOTA_KEY);
             buildViewsFromNota(nota);
        }
        btn = (Button) findViewById(R.id.btnAdd);
        btn.setOnClickListener(saveNotaEventListener());

    }

    private void buildViewsFromNota(Nota n) {
        if (n == null) {
            return;
        }
        if (n.getData() != null) {
            tietData.setText(DateConverter.fromDate(n.getData()));
        }
        tietCredite.setText(String.valueOf(n.getNrCredite()));

        tietDisciplina.setText(n.getDisciplina());
        np.setValue(n.getNota());
    }



    private View.OnClickListener saveNotaEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    createFromViews();
                    intent.putExtra(NOTA_KEY, nota);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };

    }
    private boolean validate() {
        if (tietDisciplina.getText() == null) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_nota,
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


    private void createFromViews() {
        Date date = DateConverter.fromString(tietData.getText().toString());
        Integer credite = Integer.parseInt(tietCredite.getText().toString());
        String materie = tietDisciplina.getText().toString();
        int nt = np.getValue();

        if (nota == null) {
            nota = new Nota(materie, credite, nt, date);
            Toast.makeText(this, R.string.addNota, Toast.LENGTH_SHORT).show();
        }
        else {
            nota.setData(date);
            nota.setNrCredite(credite);
            nota.setDisciplina(materie);
            nota.setNota(nt);
        }

    }
}
