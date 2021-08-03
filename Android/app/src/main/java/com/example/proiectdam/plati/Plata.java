package com.example.proiectdam.plati;

import java.io.Serializable;
import java.util.Date;

public class Plata implements Serializable {


    private String descriere;
    private Date data;
    private float suma;

    public Plata(String descriere, Date data, float suma) {
        this.descriere = descriere;
        this.data = data;
        this.suma = suma;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getSuma() {
        return suma;
    }

    public void setSuma(float suma) {
        this.suma = suma;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Plata{");
        sb.append("descriere='").append(descriere).append('\'');
        sb.append(", data=").append(data);
        sb.append(", suma=").append(suma);
        sb.append('}');
        return sb.toString();
    }
}
