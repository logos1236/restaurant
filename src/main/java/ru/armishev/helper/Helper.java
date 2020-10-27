package ru.armishev.helper;

import java.text.NumberFormat;

public class Helper {
    /*
    Цена в рублях
     */
    public static String getPriceFormated(long cost) {
        String currency = String.format("%.2f",(float)cost/100);

        return currency;
    }
}
