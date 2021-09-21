package com.gmail.wigglewie.monej;

import java.io.Serializable;
import java.util.Date;

public class CurrencyRate implements Serializable {

    public int cur_id;

    public Date date;

    public String abbreviation;

    public int scale;

    public String name_rus;

    public double rate;

    public CurrencyRate() {
    }

    public CurrencyRate(int cur_id, Date date, String abbreviation, int scale,
            String name_rus, double rate) {
        this.cur_id = cur_id;
        this.date = date;
        this.abbreviation = abbreviation;
        this.scale = scale;
        this.name_rus = name_rus;
        this.rate = rate;
    }

    //            "Cur_ID": 456,
    //            "Date": "2021-09-20T00:00:00",
    //            "Cur_Abbreviation": "RUB",
    //            "Cur_Scale": 100,
    //            "Cur_Name": "Российских рублей",
    //            "Cur_OfficialRate": 3.4226

}
