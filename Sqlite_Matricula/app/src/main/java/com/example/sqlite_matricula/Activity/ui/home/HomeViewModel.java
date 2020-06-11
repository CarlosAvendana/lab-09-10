package com.example.sqlite_matricula.Activity.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Laboratorio SQLite // Carlos Obando // Felipe Piedra");
    }

    public LiveData<String> getText() {
        return mText;
    }
}