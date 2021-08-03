package com.example.proiectdam.examene;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proiectdam.Database.model.Assignment;
import com.example.proiectdam.Note.DateConverter;
import com.example.proiectdam.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class AdaugareAssignment extends AppCompatActivity {

    public static String ASSIGNMENT_KEY = "assignment_key";
    private TextInputEditText tietData;
    private TextInputEditText tietDescriere;
    private Button btn;
    private Intent intent;

    private Assignment assignment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adaugare_assignment);

        tietDescriere = findViewById(R.id.tiet_descriere);
        tietData = findViewById(R.id.tiet_data);

        intent = getIntent();
        if (intent.hasExtra(ASSIGNMENT_KEY)) {
            assignment = (Assignment) intent.getSerializableExtra(ASSIGNMENT_KEY);
            buildViewsFromAssignment(assignment);
        }
        btn = (Button) findViewById(R.id.btnAdd);
        btn.setOnClickListener(saveAssignmentEventListener());
    }


    private void buildViewsFromAssignment(Assignment a) {
        if (a == null) {
            return;
        }
        if (a.getData() != null) {
            tietData.setText(DateConverter.fromDate(a.getData()));
        }
        tietDescriere.setText(String.valueOf(a.getDescriere()));

    }



    private View.OnClickListener saveAssignmentEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    createFromViews();
                    intent.putExtra(ASSIGNMENT_KEY, assignment);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private boolean validate() {
        if (tietDescriere.getText() == null) {
            Toast.makeText(getApplicationContext(),
                    R.string.addDesc,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (tietData.getText() == null) {
            Toast.makeText(getApplicationContext(),
                    R.string.addData,
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }

        return true;
    }

    private void createFromViews() {
        Date date = DateConverter.fromString(tietData.getText().toString());
        String descriere = tietDescriere.getText().toString();

        if (assignment == null) {
            assignment = new Assignment(descriere, date);
            Toast.makeText(this, R.string.addGood, Toast.LENGTH_SHORT).show();
        }
        else {
            assignment.setData(date);
            assignment.setDescriere(descriere);
        }

    }


}
