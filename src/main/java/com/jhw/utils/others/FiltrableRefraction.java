/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.utils.others;

import com.jhw.utils.interfaces.Filtrable;
import com.jhw.utils.interfaces.Formateable;
import java.lang.reflect.Field;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class FiltrableRefraction {

    public static <T> boolean test(T obj, String text) {
        try {
            Class c = obj.getClass();
            for (Field f : c.getDeclaredFields()) {
                f.setAccessible(true);
                Object value = f.get(obj);
                if (value == null) {
                    continue;
                }
                if (value instanceof Filtrable) {
                    if (((Filtrable) value).test(text)) {
                        return true;
                    }
                } else if (value instanceof Formateable) {
                    if (((Formateable) value).format().toLowerCase().contains(text.toLowerCase())) {
                        return true;
                    }
                } else if (value.toString().toLowerCase().contains(text.toLowerCase())) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static String toFullString(Object obj) {
        String full = "";
        try {
            Class c = obj.getClass();
            for (Field f : c.getDeclaredFields()) {
                f.setAccessible(true);
                Object value = f.get(obj);
                if (value == null) {
                    continue;
                }
                if (value instanceof Formateable) {
                    full += ((Formateable) value).format();
                }
                full += value.toString();
            }
        } catch (Exception e) {
        }
        full.toLowerCase();
        return full;
    }
}
