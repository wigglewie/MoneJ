package com.gmail.wigglewie.monej.service.impl;

import android.icu.text.SimpleDateFormat;

import com.gmail.wigglewie.monej.data.CurrencyRateXML;
import com.gmail.wigglewie.monej.service.ICurrencyGetRatesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import timber.log.Timber;

public class CurrencyGetRatesService implements ICurrencyGetRatesService {

    @Override
    public List<CurrencyRateXML> loadData() {
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
