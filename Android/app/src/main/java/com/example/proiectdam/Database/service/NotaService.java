package com.example.proiectdam.Database.service;

import android.content.Context;

import com.example.proiectdam.Database.DatabaseManager;
import com.example.proiectdam.Database.dml.notaDao;
import com.example.proiectdam.Database.model.Nota;
import com.example.proiectdam.informatii.asyncTask.Callback;
import com.example.proiectdam.informatii.asyncTask.asyncTaskRunner;

import java.util.List;
import java.util.concurrent.Callable;

public class NotaService {

    private final notaDao nDao;
    private final asyncTaskRunner taskRunner;

    public NotaService(Context context) {
        nDao = DatabaseManager.getInstance(context).getNotaDao();
        taskRunner = new asyncTaskRunner();
    }



    public void getAll(Callback<List<Nota>> callback) {
        Callable<List<Nota>> callable = new Callable<List<Nota>>() {
            @Override
            public List<Nota> call() {
                return nDao.getAll();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void getAllRestante(Callback<List<Nota>> callback) {
        Callable<List<Nota>> callable = new Callable<List<Nota>>() {
            @Override
            public List<Nota> call() {
                return nDao.getAllRestante();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void insert(Callback<Nota> callback, final Nota nota) {
        Callable<Nota> callable = new Callable<Nota>() {
            @Override
            public Nota call() {
                if (nota == null) {
                    return null;
                }
                long id = nDao.insert(nota);
                if (id == -1) {
                    return null;
                }
                nota.setId(id);
                return nota;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void update(Callback<Nota> callback, final Nota nota) {
        Callable<Nota> callable = new Callable<Nota>() {
            @Override
            public Nota call() {
                if (nota == null) {
                    return null;
                }
                int count = nDao.update(nota);
                if (count < 1) {
                    return null;
                }
                return nota;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void delete(Callback<Integer> callback, final Nota nota) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                if (nota == null) {
                    return -1;
                }
                return nDao.delete(nota);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

}
