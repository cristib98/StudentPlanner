package com.example.proiectdam.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proiectdam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    RelativeLayout r1, r2;
    private EditText inputEmail, inputParola;
    private FirebaseAuth auth2;
    private ProgressDialog loading;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            r1.setVisibility(View.VISIBLE);
            r2.setVisibility(View.VISIBLE);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        r1 = (RelativeLayout) findViewById(R.id.relative1);
        r2 = (RelativeLayout) findViewById(R.id.relative2);
        inputEmail= (EditText) findViewById(R.id.etName);
        inputParola= (EditText) findViewById(R.id.etPass);
        auth2 = FirebaseAuth.getInstance();
        loading = new ProgressDialog(LoginActivity.this);

        handler.postDelayed(runnable,2000);


        Button btn = (Button)findViewById(R.id.btnLogare);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificare();
            }
        });

        Button btnReg = (Button) findViewById(R.id.btnRegister);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

    private void verificare() {
        String email = inputEmail.getText().toString();
        String parola = inputParola.getText().toString();



        if (email.isEmpty() || !email.contains("@")) {
            showError(inputEmail, getString(R.string.mail_invalid));
        } else if (parola.isEmpty() || parola.length() < 7) {
            showError(inputParola, getString(R.string.cel_putin_7));
        }
        else {
            loading.setTitle(getString(R.string.logare));
            loading.setMessage(getString(R.string.verificare));
            loading.setCanceledOnTouchOutside(false);
            loading.show();

            auth2.signInWithEmailAndPassword(email,parola).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        loading.dismiss();
                        startActivity(new Intent(LoginActivity.this, MeniuActivity.class));

                    }
                    else {
                        loading.dismiss();
                        Toast.makeText(LoginActivity.this, R.string.nume_parola_gresita, Toast.LENGTH_SHORT).show();
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
