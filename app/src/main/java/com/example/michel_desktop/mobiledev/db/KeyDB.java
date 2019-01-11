package com.example.michel_desktop.mobiledev.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.michel_desktop.mobiledev.models.KeyModel;
import java.util.List;

@Dao
public interface KeyDB {
    @Query("SELECT * FROM keystorge")
    public LiveData<List<KeyModel>> getALlKey();

    @Insert
    public void insertReminders(KeyModel keyModel);

    @Delete
    public void deleteReminders(KeyModel keyModel);

    @Update
    public void updateReminders(KeyModel keyModel);
}
