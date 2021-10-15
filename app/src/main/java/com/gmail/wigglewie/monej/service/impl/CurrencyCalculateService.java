package com.gmail.wigglewie.monej.service.impl;

import com.gmail.wigglewie.monej.data.Currency;
import com.gmail.wigglewie.monej.service.ICurrencyCalculateService;

import org.apache.commons.math3.util.Precision;

public class CurrencyCalculateService implements ICurrencyCalculateService {

    public CurrencyCalculateService() {
    }

    @Override
    public double calculateRate(Currency currency1, Currency currency2) {
        if (currency1.equals(Currency.BELARUS)) {
            return Precision.round(1 / currency2.rate * currency2.scale, 4);
        }
        if (currency2.equals(Currency.BELARUS)) {
            return currency1.rate;
        }
        if (currency2.equals(Currency.RUSSIA)) {
            return Precision.round((currency1.rate / currency2.rate) * Currency.RUSSIA.scale, 4);
        }
        return Precision.round(currency1.rate / currency2.rate, 4);

    }

}
