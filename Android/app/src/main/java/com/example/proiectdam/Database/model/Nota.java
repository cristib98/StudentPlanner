package com.example.proiectdam.Database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;


@Entity (tableName = "note")
public class Nota implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name="disciplina")
    private String disciplina;
    @ColumnInfo(name="credite")
    private int nrCredite;
    @ColumnInfo(name="nota")
    private int nota;
    @ColumnInfo(name="data")
    private Date data;


    public Nota(long id, String disciplina, int nrCredite, int nota, Date data) {
        this.id = id;
        this.disciplina = disciplina;
        this.nrCredite = nrCredite;
        this.nota = nota;
        this.data = data;
    }


    @Ignore
    public Nota(String disciplina, int nrCredite, int nota, Date data) {
        this.disciplina = disciplina;
        this.nrCredite = nrCredite;
        this.nota = nota;
        this.data = data;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public int getNrCredite() {
        return nrCredite;
    }

    public void setNrCredite(int nrCredite) {
        this.nrCredite = nrCredite;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Nota{");
        sb.append("disciplina='").append(disciplina).append('\'');
        sb.append(", nrCredite=").append(nrCredite);
        sb.append(", nota=").append(nota);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
