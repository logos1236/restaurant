package ru.armishev.helper;

import java.text.NumberFormat;

public class Helper {
    /*
    Цена в рублях
     */
    public static String getPriceFormated(long cost) {
        //NumberFormat format = NumberFormat.getNumberInstance();
        //String currency = format.format(cost/100);

        String currency = String.format("%.2f",(float)cost/100);

        return currency;
    }
}
