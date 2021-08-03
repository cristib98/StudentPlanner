package com.example.proiectdam.Database.dml;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proiectdam.Database.model.Materie;

import java.util.List;
@Dao
public interface materieDao {
    @Query("select * from materii")
    List<Materie> getAll();

    @Insert
    long insert(Materie materie);

    @Update
    int update(Materie materie);

    @Delete
    int delete(Materie materie);
}
