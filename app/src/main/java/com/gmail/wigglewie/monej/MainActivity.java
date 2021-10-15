package com.gmail.wigglewie.monej;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.wigglewie.monej.data.Currency;
import com.gmail.wigglewie.monej.data.CurrencyRateXML;
import com.gmail.wigglewie.monej.data.CurrencyViewModel;
import com.gmail.wigglewie.monej.fragments.ConverterFragment;
import com.gmail.wigglewie.monej.service.impl.CurrencyGetRatesService;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private CurrencyViewModel model;

    private CurrencyGetRatesService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = CurrencyViewModel.getInstance();
        Timber.plant(new Timber.DebugTree());
        service = new CurrencyGetRatesService();
        getParsedData();
    }

    private void getParsedData() {
        //TODO implement service
        List<CurrencyRateXML> loadedCurrencyRateList = service.loadData();

        ArrayList<Currency> currencyList = new ArrayList<>();
        loadedCurrencyRateList.forEach(currencyRate -> {
            Currency currency = Currency.findByAbbreviation(currencyRate.getAbbreviation());
            currency.curId = currencyRate.getCurId();
            currency.date = currencyRate.getDate();
            currency.scale = currencyRate.getScale();
            currency.nameRus = currencyRate.getNameRus();
            currency.rate = currencyRate.getRate();
            currencyList.add(currency);
        });
        model.getCurrencyValue().setValue(currencyList);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, ConverterFragment.newInstance())
                .commit();
    }

}
