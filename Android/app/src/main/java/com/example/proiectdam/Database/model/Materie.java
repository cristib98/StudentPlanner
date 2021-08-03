package com.example.proiectdam.Database.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "materii")
public class Materie implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name="numeMaterie")
    private String numeMaterie;
    @ColumnInfo(name="credite")
    private int nrCredite;
    @ColumnInfo(name="numeProf")
    private String numeProf;
    @ColumnInfo(name="tiPExaminare")
    private String tipExaminare;

    public Materie(long id, String numeMaterie, int nrCredite, String numeProf, String tipExaminare) {
        this.id = id;
        this.numeMaterie = numeMaterie;
        this.nrCredite = nrCredite;
        this.numeProf = numeProf;
        this.tipExaminare = tipExaminare;
    }

    @Ignore
    public Materie(String numeMaterie, int nrCredite, String numeProf, String tipExaminare) {
        this.numeMaterie = numeMaterie;
        this.nrCredite = nrCredite;
        this.numeProf = numeProf;
        this.tipExaminare = tipExaminare;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumeMaterie() {
        return numeMaterie;
    }

    public void setNumeMaterie(String numeMaterie) {
        this.numeMaterie = numeMaterie;
    }

    public int getNrCredite() {
        return nrCredite;
    }

    public void setNrCredite(int nrCredite) {
        this.nrCredite = nrCredite;
    }

    public String getNumeProf() {
        return numeProf;
    }

    public void setNumeProf(String numeProf) {
        this.numeProf = numeProf;
    }

    public String getTipExaminare() {
        return tipExaminare;
    }

    public void setTipExaminare(String tipExaminare) {
        this.tipExaminare = tipExaminare;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Materie{");
        sb.append("numeMaterie='").append(numeMaterie).append('\'');
        sb.append(", nrCredite=").append(nrCredite);
        sb.append(", numeProf='").append(numeProf).append('\'');
        sb.append(", tipExaminare='").append(tipExaminare).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

