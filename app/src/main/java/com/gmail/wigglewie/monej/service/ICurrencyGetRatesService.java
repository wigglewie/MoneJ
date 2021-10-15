package com.gmail.wigglewie.monej.service;

import com.gmail.wigglewie.monej.data.CurrencyRateXML;

import java.util.List;

public interface ICurrencyGetRatesService {

    List<CurrencyRateXML> loadData();

}
