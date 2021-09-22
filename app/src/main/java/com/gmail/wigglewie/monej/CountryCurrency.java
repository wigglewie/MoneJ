package com.gmail.wigglewie.monej;

import java.util.HashMap;
import java.util.Map;

public enum CountryCurrency {

    BELARUS("BYN", R.drawable.ic_flag_by, 0),
    RUSSIA("RUB", R.drawable.ic_flag_ru, 1),
    UAS("USD", R.drawable.ic_flag_usa, 2),
    EUROPE("EUR", R.drawable.ic_flag_eu, 3);

    public String abbreviation;

    public int icon;

    public final int index;

    CountryCurrency(String abbreviation, int icon, int index) {
        this.abbreviation = abbreviation;
        this.icon = icon;
        this.index = index;
    }

    private static final Map<Integer, CountryCurrency> map;

    static {
        map = new HashMap<>();
        for (CountryCurrency item : CountryCurrency.values()) {
            map.put(item.index, item);
        }
    }

    public static CountryCurrency findByIndex(int index) {
        return map.get(index);
    }

    @Override
    public String toString() {
        return "CurrencyItem{" +
                "abbreviation='" + abbreviation + '\'' +
                ", icon=" + icon +
                ", index=" + index +
                '}';
    }

    public static String[] getAbbreviationsArray() {
        String[] result = new String[CountryCurrency.values().length];
        int i = 0;
        for (CountryCurrency item : CountryCurrency.values()) {
            result[i++] = item.abbreviation;
        }
        return result;
    }
}
