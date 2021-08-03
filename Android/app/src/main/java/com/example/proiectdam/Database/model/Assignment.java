package com.example.proiectdam.Database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "assignments")
public class Assignment implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name="descriere")
    private String descriere;

    @ColumnInfo(name="data")
    private Date data;

    public Assignment(long id, String descriere, Date data) {
        this.id = id;
        this.descriere = descriere;
        this.data = data;
    }

    @Ignore
    public Assignment(String descriere, Date data) {
        this.descriere = descriere;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Assignment{");
        sb.append("descriere='").append(descriere).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
