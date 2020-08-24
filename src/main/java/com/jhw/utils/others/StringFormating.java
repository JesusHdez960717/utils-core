package com.jhw.utils.others;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Formatter;
import java.util.StringTokenizer;

/**
 * Clase auxiliar para darle formato predefinido a un String.<\br>
 * Por ejemplo, se quiere convertir el String "12345679.123456" a dinero, la
 * transformacion seria el String "$123 456 789.12"
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class StringFormating {

    public static String formatToMoney(BigDecimal number, String moneda) {
        return "$ " + formatToMoney(number) + " " + moneda;
    }

    public static String formatToMoney(String number) {
        if (number.trim().isEmpty()) {
            number = "0";
        }
        return formatToMoney(new BigDecimal(number));
    }

    public static String formatToMoney(BigDecimal number) {
        BigDecimal rounded = number.setScale(2, RoundingMode.HALF_EVEN);//round to 2places

        StringTokenizer str = new StringTokenizer(rounded.toString(), ".");
        String integerPart = str.nextToken();

        String reverse = new StringBuilder(integerPart).reverse().toString();
        String split = reverse.replaceAll("...", "$0 ").trim();
        String integerFix = new StringBuilder(split).reverse().toString();

        String answ = integerFix + "." + str.nextToken();
        return answ;
    }

}
