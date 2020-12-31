/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.utils.refraction;

import com.root101.utils.interfaces.Filtrable;
import com.root101.utils.interfaces.Formateable;
import java.lang.reflect.Field;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class FiltrableRefraction {

    /**
     * Test a contain of the String text over T obj
     *
     * @param <T>
     * @param obj
     * @param text
     * @return
     */
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
        return full.toLowerCase();
    }
}
