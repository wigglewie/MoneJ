package com.gmail.wigglewie.monej;

public enum GrammarLocaleRu {

    BYN(R.string.byn_currency_naming_single, R.string.byn_currency_naming_up_to_four,
            R.string.byn_currency_naming_multiply),
    RUB(R.string.rub_currency_naming_single, R.string.rub_currency_naming_up_to_four,
            R.string.rub_currency_naming_multiply),
    USD(R.string.usd_currency_naming_single, R.string.usd_currency_naming_up_to_four,
            R.string.usd_currency_naming_multiply),
    EUR(R.string.eur_currency_naming_single, R.string.eur_currency_naming_up_to_four,
            R.string.eur_currency_naming_multiply);

    private final int nameCurrencySingle;
    private final int nameCurrencyUpToFour;
    private final int nameCurrencyMultiply;

    GrammarLocaleRu(int nameCurrencySingle, int nameCurrencyUpToFour, int nameCurrencyMultiply) {
        this.nameCurrencySingle = nameCurrencySingle;
        this.nameCurrencyUpToFour = nameCurrencyUpToFour;
        this.nameCurrencyMultiply = nameCurrencyMultiply;
    }

    public int getNaming(int number) {
        char[] numeralAsChars = String.valueOf(number).toCharArray();
        if (number > 9) {
            int lastTwoDigits =
                    Integer.parseInt(numeralAsChars[numeralAsChars.length - 2] + String.valueOf(numeralAsChars[numeralAsChars.length - 1]));
            if (lastTwoDigits > 9 && lastTwoDigits < 21) {
                return nameCurrencyMultiply;
            }
        }
        int lastDigit = Character.getNumericValue(numeralAsChars[numeralAsChars.length - 1]);
        if (lastDigit == 1) {
            return nameCurrencySingle;
        }
        if (lastDigit > 1 && 5 > lastDigit) {
            return nameCurrencyUpToFour;
        }
        return nameCurrencyMultiply;
    }

}
