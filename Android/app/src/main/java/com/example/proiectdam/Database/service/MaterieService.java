package com.example.proiectdam.Database.service;

import android.content.Context;

import com.example.proiectdam.Database.DatabaseManager;
import com.example.proiectdam.Database.dml.materieDao;
import com.example.proiectdam.Database.model.Materie;
import com.example.proiectdam.informatii.asyncTask.Callback;
import com.example.proiectdam.informatii.asyncTask.asyncTaskRunner;

import java.util.List;
import java.util.concurrent.Callable;

public class MaterieService {

    private final materieDao mDao;
    private final asyncTaskRunner taskRunner;

    public MaterieService(Context context) {
        mDao = DatabaseManager.getInstance(context).getMaterieDao();
        taskRunner = new asyncTaskRunner();
    }


    public void getAll(Callback<List<Materie>> callback) {
        Callable<List<Materie>> callable = new Callable<List<Materie>>() {
            @Override
            public List<Materie> call() {
                return mDao.getAll();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void insert(Callback<Materie> callback, final Materie materie) {
        Callable<Materie> callable = new Callable<Materie>() {
            @Override
            public Materie call() {
                if (materie == null) {
                    return null;
                }
                long id = mDao.insert(materie);
                if (id == -1) {
                    return null;
                }
                materie.setId(id);
                return materie;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void update(Callback<Materie> callback, final Materie materie) {
        Callable<Materie> callable = new Callable<Materie>() {
            @Override
            public Materie call() {
                if (materie == null) {
                    return null;
                }
                int count = mDao.update(materie);
                if (count < 1) {
                    return null;
                }
                return materie;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void delete(Callback<Integer> callback, final Materie materie) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                if (materie == null) {
                    return -1;
                }
                return mDao.delete(materie);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
}
