package com.example.michel_desktop.mobiledev.vieuwModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.michel_desktop.mobiledev.GridCell;
import com.example.michel_desktop.mobiledev.models.BalanceModel;
import com.example.michel_desktop.mobiledev.repository.MainAcitivityRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainVieuwModel extends ViewModel {
	private MainAcitivityRepository mainActivityRepository;
    private List<GridCell> mGridCell = new ArrayList<>();
    private String apiKey;
    private String secretKey;
    private LiveData<List<BalanceModel>> balanceList;

	/**
	 * Constructor
	 * @param context
	 */
	public MainVieuwModel(Context context){
		mainActivityRepository = new MainAcitivityRepository(context);
		balanceList = mainActivityRepository.getALlBalance();
	}

    /**
     * Grid cell
     * @return list
     */
    public List<GridCell> getmGridCell() {
        return mGridCell;
    }

	/**
	 * Add gridcell in de list
	 * @param gridcell gridceel object
	 */
	public void addGridCell(GridCell gridcell){
        mGridCell.add(gridcell);
    }

	/**
	 * Comperator list
	 */
	public void comperateList(){
        Collections.sort(mGridCell, new Comparator<GridCell>() {
            @Override
            public int compare(GridCell g1, GridCell g2) {
                if(g1.getBalance() > g2.getBalance()){
                    return -1;
                } else if(g1.getBalance() < g2.getBalance()){
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

	/**
	 * Live data wat het balance model van 1 specifieke coin op vraagd
	 * @param cointag cointag
	 * @return live data object
	 */
	public List<BalanceModel> getAllBalance(String cointag){
		return mainActivityRepository.getAllBalance(cointag);
	}

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

	public LiveData<List<BalanceModel>> getBalance() {
		return balanceList;
	}

	public void insert(BalanceModel balanceModel) {
		mainActivityRepository.insert(balanceModel);
	}

	public void update(BalanceModel balanceModel) {
		mainActivityRepository.update(balanceModel);
	}

	public void delete(BalanceModel balanceModel) {
		mainActivityRepository.delete(balanceModel);
	}
}
