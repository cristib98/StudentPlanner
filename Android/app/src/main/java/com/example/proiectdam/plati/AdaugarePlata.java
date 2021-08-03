package com.example.proiectdam.plati;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proiectdam.R;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Calendar;
import java.util.Date;

public class AdaugarePlata extends AppCompatActivity {


    public static String PLATA_KEY = "plata_key";
    private TextInputEditText tietDescriere;
    private TextInputEditText tietSuma;
    private DatePicker datePicker;
    private Button btn;
    private Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adaugare_plata);
        tietDescriere = findViewById(R.id.tiet_descriere);
        tietSuma = findViewById(R.id.tiet_suma);
        datePicker = findViewById(R.id.datePicker1);
        intent = getIntent();
        btn = (Button) findViewById(R.id.btnAdd);
        btn.setOnClickListener(addSaveEventListener());

    }
    private View.OnClickListener addSaveEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    Plata p = crearePlata();
                    intent.putExtra(PLATA_KEY, p);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };

    }

    private boolean validate() {

        if (tietDescriere.getText() == null) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_descriere,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }

        if (tietSuma.getText() == null) {
            Toast.makeText(getApplicationContext(),
                    R.string.add_suma,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }


        return true;
    }
    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    private Plata crearePlata() {

        String desc = tietDescriere.getText().toString();
        Date date = getDateFromDatePicker(datePicker);
        float suma = Float.parseFloat(tietSuma.getText().toString());
        Plata plt = new Plata(desc,date,suma);
        Toast.makeText(this, R.string.addPlata, Toast.LENGTH_SHORT).show();
        return plt;
    }



}
