package com.example.proiectdam.Database.service;

import android.content.Context;

import com.example.proiectdam.Database.DatabaseManager;
import com.example.proiectdam.Database.dml.assignmentDao;
import com.example.proiectdam.Database.model.Assignment;
import com.example.proiectdam.informatii.asyncTask.Callback;
import com.example.proiectdam.informatii.asyncTask.asyncTaskRunner;

import java.util.List;
import java.util.concurrent.Callable;

public class AssignmentService {

    private final assignmentDao aDao;
    private final asyncTaskRunner taskRunner;

    public AssignmentService(Context context) {
        aDao = DatabaseManager.getInstance(context).getAssignmentDao();
        taskRunner = new asyncTaskRunner();
    }



    public void getAll(Callback<List<Assignment>> callback) {
        Callable<List<Assignment>> callable = new Callable<List<Assignment>>() {
            @Override
            public List<Assignment> call() {
                return aDao.getAll();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }


    public void insert(Callback<Assignment> callback, final Assignment as) {
        Callable<Assignment> callable = new Callable<Assignment>() {
            @Override
            public Assignment call() {
                if (as == null) {
                    return null;
                }
                long id = aDao.insert(as);
                if (id == -1) {
                    return null;
                }
                as.setId(id);
                return as;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void update(Callback<Assignment> callback, final Assignment as) {
        Callable<Assignment> callable = new Callable<Assignment>() {
            @Override
            public Assignment call() {
                if (as == null) {
                    return null;
                }
                int count = aDao.update(as);
                if (count < 1) {
                    return null;
                }
                return as;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void delete(Callback<Integer> callback, final Assignment as) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                if (as == null) {
                    return -1;
                }
                return aDao.delete(as);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
}
