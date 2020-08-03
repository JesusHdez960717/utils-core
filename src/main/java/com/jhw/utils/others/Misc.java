package com.jhw.utils.others;

import java.util.Date;
import java.util.Formatter;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Field;
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

    public static <T> List<T> sortByAnnotation(List<T> list, Class<T> clazz, String[] fieldsNames) {
        for (String actualField : fieldsNames) {
            try {
                Field field = clazz.getDeclaredField(actualField);//cojo el Field

                boolean oldAccess = field.isAccessible();

                Collections.sort(list, (first, second) -> {
                    try {
                        return ((Comparable) field.get(first)).compareTo(field.get(second));
                    } catch (Exception ex) {
                        return 0;
                    }
                });

                field.setAccessible(oldAccess);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;//return the same list
    }
}
