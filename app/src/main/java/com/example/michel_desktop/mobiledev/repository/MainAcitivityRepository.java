package com.example.michel_desktop.mobiledev.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.michel_desktop.mobiledev.db.AppDatabase;
import com.example.michel_desktop.mobiledev.db.BalanceDB;
import com.example.michel_desktop.mobiledev.models.BalanceModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainAcitivityRepository {
	private AppDatabase mAppDatabase;
	private BalanceDB balance;
	private LiveData<List<BalanceModel>> balanceList;
	private Executor mExecutor = Executors.newSingleThreadExecutor();
	public MainAcitivityRepository (Context context) {
		mAppDatabase = AppDatabase.getInstance(context);
		balance = mAppDatabase.balanceDB();
		balanceList = balance.getbalanceDB();
	}

	/**
	 * Vraag alle balance data op
	 * @return live data object
	 */
	public LiveData<List<BalanceModel>> getALlBalance() {
		return balanceList;
	}

	/**
	 * Live data wat het balance model van 1 specifieke coin op vraagd
	 * @param cointag cointag
	 * @return live data object
	 */
	public List<BalanceModel> getAllBalance(String cointag){
		return balance.getBalanceModel(cointag);
	}

	/**
	 * Insert balance model
	 * @param balanceModel balance model
	 */
	public void insert(final BalanceModel balanceModel) {
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				balance.insertReminders(balanceModel);
			}
		});
	}

	/**
	 * Update balance model
	 * @param balanceModel balance model
	 */
	public void update(final BalanceModel balanceModel) {
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				balance.updateReminders(balanceModel);
			}
		});
	}

	public void delete(final BalanceModel balanceModel) {
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				balance.deleteReminders(balanceModel);
			}
		});
	}
}
