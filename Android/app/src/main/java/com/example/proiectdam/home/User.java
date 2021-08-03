package com.example.proiectdam.home;

public class User {

    public String email, nume, dataNastere, an, parola;

    public User(){

    }

    public User(String email, String nume, String dataNastere, String an, String parola) {
        this.email = email;
        this.nume = nume;
        this.dataNastere = dataNastere;
        this.an = an;
        this.parola = parola;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("email='").append(email).append('\'');
        sb.append(", nume='").append(nume).append('\'');
        sb.append(", dataNastere='").append(dataNastere).append('\'');
        sb.append(", an='").append(an).append('\'');
        sb.append(", parola='").append(parola).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
