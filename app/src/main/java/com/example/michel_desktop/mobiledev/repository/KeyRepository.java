package com.example.michel_desktop.mobiledev.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.michel_desktop.mobiledev.db.AppDatabase;
import com.example.michel_desktop.mobiledev.db.KeyDB;
import com.example.michel_desktop.mobiledev.models.KeyModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class KeyRepository {
    private AppDatabase mAppDatabase;

    private KeyDB keyDao;
    private LiveData<List<KeyModel>> keyList;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public KeyRepository (Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        keyDao = mAppDatabase.keysDBAccess();
        keyList = keyDao.getALlKey();
    }

    public LiveData<List<KeyModel>> getAllKeys() {
        return keyList;
    }

    public void insert(final KeyModel keyModel) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                keyDao.insertReminders(keyModel);
            }
        });
    }

    public void update(final KeyModel keyModel) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                keyDao.updateReminders(keyModel);
            }
        });
    }

    public void delete(final KeyModel keyModel) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                keyDao.deleteReminders(keyModel);
            }
        });
    }
}
