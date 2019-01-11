package com.example.michel_desktop.mobiledev.vieuwModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.michel_desktop.mobiledev.repository.KeyRepository;
import com.example.michel_desktop.mobiledev.models.KeyModel;

import java.util.List;

public class KeyViewModel extends ViewModel {
    private KeyRepository keyRepository;
    private LiveData<List<KeyModel>> keysList;

    public KeyViewModel(Context context) {
        keyRepository = new KeyRepository(context);
        keysList = keyRepository.getAllKeys();
    }

    public LiveData<List<KeyModel>> getAllKey() {
        return keysList;
    }

    public void insert(KeyModel keyModel) {
        keyRepository.insert(keyModel);
    }

    public void update(KeyModel keyModel) {
        keyRepository.update(keyModel);
    }

    public void delete(KeyModel keyModel) {
        keyRepository.delete(keyModel);
    }
}
