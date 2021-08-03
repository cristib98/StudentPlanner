package com.example.proiectdam.informatii;

import java.io.Serializable;

public class Facultate implements Serializable {

    private String nume;
    private String nrTel;
    private String email;

    public Facultate(String nume, String nrTel, String email) {
        this.nume = nume;
        this.nrTel = nrTel;
        this.email = email;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNrTel() {
        return nrTel;
    }

    public void setNrTel(String nrTel) {
        this.nrTel = nrTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Facultate{");
        sb.append("nume='").append(nume).append('\'');
        sb.append(", nrTel='").append(nrTel).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
