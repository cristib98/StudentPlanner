package com.example.proiectdam.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.proiectdam.Database.dml.assignmentDao;
import com.example.proiectdam.Database.dml.materieDao;
import com.example.proiectdam.Database.dml.notaDao;
import com.example.proiectdam.Database.model.Assignment;
import com.example.proiectdam.Database.model.Materie;
import com.example.proiectdam.Database.model.Nota;
import com.example.proiectdam.Note.DateConverter;


@Database(entities = {Nota.class, Assignment.class, Materie.class}, exportSchema = false, version = 4)
@TypeConverters({DateConverter.class})
public abstract class DatabaseManager extends RoomDatabase {
    private static DatabaseManager databaseManager;

    private static final String PROIECT_DAM = "proiect_dam";

    public static DatabaseManager getInstance(Context context) {
        if(databaseManager == null) {
            synchronized (DatabaseManager.class) {
                if (databaseManager == null) {
                    databaseManager = Room.databaseBuilder(context, DatabaseManager.class, PROIECT_DAM)
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return databaseManager;
    }

    public abstract notaDao getNotaDao();
    public abstract assignmentDao getAssignmentDao();
    public abstract materieDao getMaterieDao();

}
