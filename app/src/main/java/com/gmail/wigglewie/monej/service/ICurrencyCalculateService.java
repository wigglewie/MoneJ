package com.gmail.wigglewie.monej.service;

import com.gmail.wigglewie.monej.data.Currency;

public interface ICurrencyCalculateService {

    double calculateRate(Currency currency1, Currency currency2);
}
