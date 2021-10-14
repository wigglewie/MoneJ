package com.gmail.wigglewie.monej.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class CurrencyViewModel extends ViewModel {

    private static CurrencyViewModel instance;

    private MutableLiveData<ArrayList<Currency>> currencyValue;

    public static CurrencyViewModel getInstance() {
        if (instance == null) {
            instance = new CurrencyViewModel();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<Currency>> getCurrencyValue() {
        if (currencyValue == null) {
            currencyValue = new MutableLiveData<>();
        }
        return currencyValue;
    }

}
