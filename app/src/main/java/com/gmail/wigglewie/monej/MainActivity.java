package com.gmail.wigglewie.monej;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.wigglewie.monej.fragments.ConverterFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
        //TODO implement service
        currencyList = new ArrayList<>();
        try {
            currencyList.add(new CurrencyRate(
                    456,
                    new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                            .parse("2021-09-20T00:00:00"),
                    "RUB",
                    100,
                    "Российских рублей",
                    3.4226));
            currencyList.add(new CurrencyRate(
                    431,
                    new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                            .parse("2021-09-20T00:00:00"),
                    "USD",
                    1,
                    "Доллар США",
                    2.4826));
            currencyList.add(new CurrencyRate(
                    451,
                    new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                            .parse("2021-09-20T00:00:00"),
                    "EUR",
                    1,
                    "Евро",
                    2.9240));
            currencyList.add(new CurrencyRate(
                    123,
                    new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                            .parse("2021-09-20T00:00:00"),
                    "BYN",
                    1,
                    "BEL_TEST",
                    2.2));

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        currencyList.forEach(currencyRate -> {
            Currency currency = Currency.findByAbbreviation(currencyRate.abbreviation);
            currency.cur_id = currencyRate.cur_id;
            currency.date = currencyRate.date;
            currency.scale = currencyRate.scale;
            currency.name_rus = currencyRate.name_rus;
            currency.rate = currencyRate.rate;
        });
        showFragmentWithLoadedData();
    }

    private void showFragmentWithLoadedData() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, ConverterFragment.newInstance(currencyList))
                .commit();
    }

}