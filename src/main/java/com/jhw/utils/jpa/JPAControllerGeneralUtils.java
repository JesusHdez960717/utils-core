/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.utils.jpa;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class JPAControllerGeneralUtils {

    public static Object getId(Object obj) throws Exception {
        Object id = null;
        for (Field declaredField : obj.getClass().getDeclaredFields()) {
            for (Annotation declaredAnnotation : declaredField.getDeclaredAnnotations()) {
                if (declaredAnnotation instanceof javax.persistence.Id) {
                    boolean oldFieldAccessibility = declaredField.isAccessible();
                    try {
                        declaredField.setAccessible(true);//in case it's private, force the readable
                        id = declaredField.get(obj);
                    } catch (Exception e) {
                        throw e;
                    } finally {
                        declaredField.setAccessible(oldFieldAccessibility);
                    }
                }
            }
        }
        return id;
    }

    public static boolean isEntity(Class classType) {
        for (Annotation declaredAnnotation : classType.getDeclaredAnnotations()) {
            if (declaredAnnotation instanceof javax.persistence.Entity) {
                return true;
            }
        }
        return false;
    }
}
