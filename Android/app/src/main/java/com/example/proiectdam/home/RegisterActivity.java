package com.example.proiectdam.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proiectdam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText inputEmail, inputParola, inputParola2, inputData, inputAn, inputNumeComplet;
    private Button btnRegister;
    private FirebaseAuth auth;
    private ProgressDialog loading;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private static final String USER = "users";
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputEmail = findViewById(R.id.etEmail);
        inputParola = findViewById(R.id.etParola);
        inputParola2 = findViewById(R.id.etParola2);
        inputData = findViewById(R.id.etData);
        inputNumeComplet = findViewById(R.id.etNumeComplet);
        inputAn = findViewById(R.id.etAn);
        auth = FirebaseAuth.getInstance();
        loading = new ProgressDialog(RegisterActivity.this);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference("USER");


        btnRegister = findViewById(R.id.btnInregistrare);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificare();
            }
        });


    }

    private void verificare() {
        String email = inputEmail.getText().toString();
        String parola = inputParola.getText().toString();
        String parola2 = inputParola2.getText().toString();
        String data = inputData.getText().toString();
        String an = inputAn.getText().toString();
        String numeComplet = inputNumeComplet.getText().toString();


        if (email.isEmpty() || !email.contains("@")) {
            showError(inputEmail, "Mail invalid!");
        }if (parola.isEmpty() || parola.length() < 7) {
            showError(inputParola, getString(R.string.cel_putin_7_caractere));
        }
       if (parola2.isEmpty() || !parola2.equals(parola)) {
            showError(inputParola2, getString(R.string.parolele_nu_corespund));
        }
      if (numeComplet.isEmpty()){
            showError(inputNumeComplet, getString(R.string.int_nume));

        }
      if (data.isEmpty() || !data.contains("-")) {
            showError(inputData, getString(R.string.data_invalida));
        }
        if (an.isEmpty()) {
            showError(inputAn, getString(R.string.an_invalid));
        }
        else {
            user = new User(email, numeComplet, data, an, parola);


            loading.setTitle(getString(R.string.inregistrare));
            loading.setMessage(getString(R.string.se_verifica));
            loading.setCanceledOnTouchOutside(false);
            loading.show();

            auth.createUserWithEmailAndPassword(email, parola).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        loading.dismiss();

                        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                        Toast.makeText(RegisterActivity.this, R.string.inregistrare_succes, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else {
                        loading.dismiss();
                        Toast.makeText(RegisterActivity.this, R.string.inregistrare_esuata, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

}
