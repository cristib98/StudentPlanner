package com.example.proiectdam.Database.dml;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proiectdam.Database.model.Nota;

import java.util.List;

@Dao
public interface notaDao {

    @Query("select * from note")
    List<Nota> getAll();

    @Query("select * from note where nota<5")
    List<Nota> getAllRestante();


    @Insert
    long insert(Nota nota);

    @Update
    int update(Nota nota);

    @Delete
    int delete(Nota nota);

}
