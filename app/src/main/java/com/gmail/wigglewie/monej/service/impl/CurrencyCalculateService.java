package com.gmail.wigglewie.monej.service.impl;

import com.gmail.wigglewie.monej.data.Currency;
import com.gmail.wigglewie.monej.service.ICurrencyCalculateService;

import org.apache.commons.math3.util.Precision;

public class CurrencyCalculateService implements ICurrencyCalculateService {

    public CurrencyCalculateService() {
    }

    @Override
    public double calculateRate(Currency currency1, Currency currency2) {
        if (currency1.name().equals(Currency.BELARUS.name())) {
            return Precision.round(1 / currency2.rate, 4);
        }
        if (currency2.name().equals(Currency.BELARUS.name())) {
            return currency1.rate;
        } else {
            return Precision.round(currency1.rate / currency2.rate, 4);
        }
    }

}
