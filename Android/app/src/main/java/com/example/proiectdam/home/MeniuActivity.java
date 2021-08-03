package com.example.proiectdam.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.proiectdam.Materii.MateriiActivity;
import com.example.proiectdam.Note.NoteActivity;
import com.example.proiectdam.informatii.InformatiiActivity;

import com.example.proiectdam.R;
import com.example.proiectdam.restante.RestanteActivity;
import com.example.proiectdam.examene.ExameneActivity;
import com.example.proiectdam.plati.PlatiActivity;

public class MeniuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_meniu);

        ImageView img = (ImageView)findViewById(R.id.imgInfo);

        img.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        CardView cv1 = (CardView)findViewById(R.id.cvPlati);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MeniuActivity.this, PlatiActivity.class));
            }
        });

        CardView cv2 = (CardView)findViewById(R.id.cvNote);
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MeniuActivity.this, NoteActivity.class));
            }
        });

        CardView cv3 = (CardView)findViewById(R.id.cvMaterii);
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MeniuActivity.this, MateriiActivity.class));
            }
        });

        CardView cv4 = (CardView)findViewById(R.id.cvRestante);
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MeniuActivity.this, RestanteActivity.class));
            }
        });

        CardView cv5 = (CardView)findViewById(R.id.cvExamene);
        cv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MeniuActivity.this, ExameneActivity.class));
            }
        });

        CardView cv6 = (CardView)findViewById(R.id.cvInfo);
        cv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MeniuActivity.this, InformatiiActivity.class));
            }
        });
        CardView cv7 = (CardView)findViewById(R.id.cvProfil);
        cv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MeniuActivity.this, ProfileActivity.class));
            }
        });

        CardView cv8 = (CardView)findViewById(R.id.cvAse);
        cv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(getString(R.string.ase_ro))));
            }
        });





    }

    public void openDialog(){
        InfoDialog info = new InfoDialog();
        info.show(getSupportFragmentManager(), getString(R.string.info));

    }
}
