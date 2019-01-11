package com.example.michel_desktop.mobiledev.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.michel_desktop.mobiledev.models.BalanceModel;
import java.util.List;

@Dao
public interface BalanceDB {
	@Query("SELECT * FROM balanceStorge")
	public LiveData<List<BalanceModel>> getbalanceDB();

	@Query("SELECT * FROM balanceStorge WHERE asset = :asset")
	public List<BalanceModel> getBalanceModel(String asset);

	@Insert
	public void insertReminders(BalanceModel balanceModel);

	@Delete
	public void deleteReminders(BalanceModel balanceModel);

	@Update
	public void updateReminders(BalanceModel balanceModel);
}
