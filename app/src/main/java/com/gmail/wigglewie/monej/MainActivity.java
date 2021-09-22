package com.gmail.wigglewie.monej;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gmail.wigglewie.monej.fragments.ConverterFragment;

import java.util.ArrayList;
import java.util.Date;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CurrencyRate> currencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.plant(new Timber.DebugTree());

        getParsedData();
    }

    private void getParsedData() {
        currencyList = new ArrayList<>();
        currencyList.add(new CurrencyRate(123, new Date(), "RUS", 23, "Russian rubles", 2.2));
        //TODO implement service

        showFragmentWithLoadedData();
    }

    private void showFragmentWithLoadedData() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, ConverterFragment.newInstance(currencyList))
                .commit();
    }

}