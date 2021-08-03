package com.example.proiectdam.Database.dml;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proiectdam.Database.model.Assignment;
import com.example.proiectdam.Database.model.Nota;

import java.util.List;

@Dao
public interface assignmentDao {

    @Query("select * from assignments")
    List<Assignment> getAll();


    @Insert
    long insert(Assignment as);

    @Update
    int update(Assignment as);

    @Delete
    int delete(Assignment as);
}
