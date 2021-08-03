package com.example.proiectdam.informatii.asyncTask;

public interface Callback<R> {
    void runResultOnUiThread(R result);
}
