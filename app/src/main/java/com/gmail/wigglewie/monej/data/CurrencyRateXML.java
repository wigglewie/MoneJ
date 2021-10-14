package com.gmail.wigglewie.monej.data;

import java.io.Serializable;
import java.util.Date;

//xml parsed
public class CurrencyRateXML implements Serializable {

    private int curId;

    private Date date;

    private String abbreviation;

    private int scale;

    private String nameRus;

    private double rate;

    public CurrencyRateXML() {
    }

    public CurrencyRateXML(int curId, Date date, String abbreviation, int scale,
                           String nameRus, double rate) {
        this.curId = curId;
        this.date = date;
        this.abbreviation = abbreviation;
        this.scale = scale;
        this.nameRus = nameRus;
        this.rate = rate;
    }

    public int getCurId() {
        return curId;
    }

    public void setCurId(int curId) {
        this.curId = curId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getNameRus() {
        return nameRus;
    }

    public void setNameRus(String nameRus) {
        this.nameRus = nameRus;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    //            "Cur_ID": 456,
    //            "Date": "2021-09-20T00:00:00",
    //            "Cur_Abbreviation": "RUB",
    //            "Cur_Scale": 100,
    //            "Cur_Name": "Российских рублей",
    //            "Cur_OfficialRate": 3.4226

}
