package com.gmail.wigglewie.monej;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public enum Currency {

    BELARUS("BYN", R.drawable.ic_flag_by),
    RUSSIA("RUB", R.drawable.ic_flag_ru),
    USA("USD", R.drawable.ic_flag_usa),
    EUROPE("EUR", R.drawable.ic_flag_eu);

    public String abbreviation;

    public int icon;

    public int cur_id;

    public Date date;

    public int scale;

    public String name_rus;

    public double rate;

    Currency(String abbreviation, int icon) {
        this.abbreviation = abbreviation;
        this.icon = icon;
    }

    private static final Map<Integer, Currency> mapByOrdinal;

    static {
        mapByOrdinal = new HashMap<>();
        for (Currency item : Currency.values()) {
            mapByOrdinal.put(item.ordinal(), item);
        }
    }

    private static final Map<String, Currency> mapByAbbreviation;

    static {
        mapByAbbreviation = new HashMap<>();
        for (Currency item : Currency.values()) {
            mapByAbbreviation.put(item.abbreviation, item);
        }
    }

    public static Currency findByIndex(int index) {
        return mapByOrdinal.get(index);
    }

    public static Currency findByAbbreviation(String abbreviation) {
        return mapByAbbreviation.get(abbreviation);
    }

    @Override
    public String toString() {
        return "CurrencyItem{" +
                "abbreviation='" + abbreviation + '\'' +
                ", icon=" + icon +
                ", ordinal=" + ordinal() +
                '}';
    }

    public static String[] getAbbreviationsArray() {
        String[] result = new String[Currency.values().length];
        int i = 0;
        for (Currency item : Currency.values()) {
            result[i++] = item.abbreviation;
        }
        return result;
    }
}
