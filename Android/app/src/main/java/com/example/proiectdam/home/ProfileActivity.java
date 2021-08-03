package com.example.proiectdam.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectdam.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity  extends AppCompatActivity {

    private Button btnLogOut;

    private FirebaseUser user;
    private DatabaseReference referinta;

    private String userId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toast.makeText(this, R.string.firebase, Toast.LENGTH_SHORT).show();

        btnLogOut = (Button) findViewById(R.id.btnLogOut);

        btnLogOut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        referinta = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        final TextView greetingTv = (TextView) findViewById(R.id.greeting);
        final TextView emailTv = (TextView) findViewById(R.id.email);
        final TextView numeTv = (TextView) findViewById(R.id.nume);
        final TextView dataNastereTv = (TextView) findViewById(R.id.dataNastere);
        final TextView anTv = (TextView) findViewById(R.id.an);


        referinta.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User profilUser = snapshot.getValue(User.class);

                if (profilUser != null) {

                    String nume = profilUser.nume;
                    String mail = profilUser.email;
                    String data = profilUser.dataNastere;
                    String an = profilUser.an;

                    greetingTv.setText(getString(R.string.welcome) + nume +"!");
                    emailTv.setText(mail);
                    numeTv.setText(nume);
                    dataNastereTv.setText(data);
                    anTv.setText(an);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, R.string.eroare, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
