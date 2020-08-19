/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.utils.others;

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
                if (f.get(obj).toString().toLowerCase().contains(text.toLowerCase())) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }
}
