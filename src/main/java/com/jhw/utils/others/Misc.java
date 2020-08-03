package com.jhw.utils.others;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.Formatter;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class Misc {

    public static String toString64(byte[] arr) {
        return String.format("%1$064x", new java.math.BigInteger(1, arr));
    }

    public static float round2f(double numero) {
        return Float.parseFloat(new Formatter().format("%.2f", numero).toString().replace(',', '.'));
    }

    public static long daysBetween(Date from, Date to) {
        return TimeUnit.DAYS.convert(Math.abs(to.getTime() - from.getTime()), TimeUnit.MILLISECONDS);
    }

    public static float evaluate(float a, String op, float b) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return b == 0 ? 0 : a / b;
        }
        return 0;
    }

    public static <T> List<T> sortByAnnotation(List<T> list, Class<T> clazz, String[] fieldsNames, int order) {
        try {
            for (String actualField : fieldsNames) {
                Field field = clazz.getDeclaredField(actualField);//cojo el Field 

                field.setAccessible(true);

                Collections.sort(list, (first, second) -> {
                    try {
                        return order * ((Comparable) field.get(first)).compareTo(field.get(second));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        return 0;
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;//return the same list 
    }

    public static <T> T[] reverse(T arr[]) {
        T[] answ = (T[]) Array.newInstance(arr.getClass().getComponentType(), arr.length);
        for (int i = 0; i < arr.length; i++) {
            answ[i] = arr[arr.length - 1 - i];
        }
        return answ;
    }
}
