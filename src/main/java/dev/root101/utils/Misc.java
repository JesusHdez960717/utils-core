/*
 * Copyright 2022 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.root101.utils;

import java.lang.reflect.Array;
import java.util.Formatter;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class Misc {

    private Misc() {
    }

    public static boolean exact(List neww, List old) {
        return neww.equals(old);
    }

    /**
     * Siempre return false, porque: <\br>
     * Esto solo compara si los elementos coninciden, pero chequea contra ID, o
     * sea que si se chequea para elementos que se han agregado o quitado
     * funciona ok, pero para elementos editados, no notaria la diferencia xq el
     * campo que cambió no forma parte del equals. Para que funcione todo hay
     * que hacer la comparación por todos los campos, con un refraction o algo
     * parecido, por lo que habria los tiempos, xq el refraction va a consumir
     * procesamiento</br>
     * TODO: prueba de estres
     *
     * @param neww
     * @param old
     * @return
     */
    public static boolean equalsIgnoreOrder(List neww, List old) {
        return false;//simpre falso hasta que se haga la prueba de estres
        //return neww.containsAll(old);
        //return new HashSet<>(neww).equals(new HashSet<>(old));
    }

    public static String toString64(byte[] arr) {
        return String.format("%1$064x", new java.math.BigInteger(1, arr));
    }

    public static float round2f(double numero) {
        return Float.parseFloat(new Formatter().format("%.2f", numero).toString().replace(',', '.'));
    }

    public static long daysBetween(LocalDate from, LocalDate to) {
        return ChronoUnit.DAYS.between(from, to);
    }

    public static float evaluate(float a, String op, float b) {
        switch (op) {
            case "+" -> {
                return a + b;
            }
            case "-" -> {
                return a - b;
            }
            case "*" -> {
                return a * b;
            }
            case "/" -> {
                return b == 0 ? 0 : a / b;
            }
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
