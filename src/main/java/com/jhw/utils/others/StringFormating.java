package com.jhw.utils.others;

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

    public static String formatToMoney(double number, String moneda) {
        return "$ " + formatToMoney(number) + " " + moneda;
    }

    public static String formatToMoney(String number) {
        if (number.trim().isEmpty()) {
            number = "0";
        }
        return formatToMoney(Double.parseDouble(number));
    }

    public static String formatToMoney(double number) {
        //redondear a dos lugares
        float round = Float.parseFloat(new Formatter().format("%.2f", number).toString().replace(',', '.'));
        String text = round + "";

        StringTokenizer l = new StringTokenizer(text, ".");

        String real = l.nextToken();
        String decimal = l.nextToken();

        String resp = formatToMoney(Long.parseLong(real)) + "." + decimal;
        return resp.trim();
    }

    public static String formatToMoney(long number) {
        String resp = "";
        String real = number + "";
        String realBack = reverse(real);
        int spaces = realBack.length() / 3;
        for (int i = 0; i < spaces; i++) {
            for (int j = 0; j < 3; j++) {
                resp += realBack.charAt(i * 3 + j);
            }
            resp += " ";
        }
        for (int i = 3 * spaces; i < realBack.length(); i++) {
            resp += realBack.charAt(i);
        }

        return reverse(resp);
    }

    private static String reverse(String real) {
        String realBack = "";
        for (int i = 0; i < real.length(); i++) {
            realBack += real.charAt(real.length() - 1 - i);
        }
        return realBack;
    }
}
