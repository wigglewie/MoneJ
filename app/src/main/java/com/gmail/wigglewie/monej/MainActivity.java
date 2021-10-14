package com.gmail.wigglewie.monej;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.wigglewie.monej.data.Currency;
import com.gmail.wigglewie.monej.data.CurrencyRateXML;
import com.gmail.wigglewie.monej.data.CurrencyViewModel;
import com.gmail.wigglewie.monej.fragments.ConverterFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private CurrencyViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = CurrencyViewModel.getInstance();
        Timber.plant(new Timber.DebugTree());

        getParsedData();
    }

    private void getParsedData() {
        //TODO implement service
        List<CurrencyRateXML> loadedCurrencyRateList = loadRatioData();

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

    private List<CurrencyRateXML> loadRatioData() {
        List<CurrencyRateXML> result = new ArrayList<>();
        try {
            result.add(new CurrencyRateXML(
                    123,
                    new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                            .parse("2021-09-20T00:00:00"),
                    "BYN",
                    1,
                    "Белорусский рубль",
                    1));
            result.add(new CurrencyRateXML(
                    456,
                    new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                            .parse("2021-09-20T00:00:00"),
                    "RUB",
                    100,
                    "Российских рублей",
                    3.4226));
            result.add(new CurrencyRateXML(
                    431,
                    new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                            .parse("2021-09-20T00:00:00"),
                    "USD",
                    1,
                    "Доллар США",
                    2.4826));
            result.add(new CurrencyRateXML(
                    451,
                    new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                            .parse("2021-09-20T00:00:00"),
                    "EUR",
                    1,
                    "Евро",
                    2.9240));
        } catch (Exception e) {
            Timber.d("===== FAKE LOADED ===== : %s", result.toString());
        }
        return result;
    }

}
