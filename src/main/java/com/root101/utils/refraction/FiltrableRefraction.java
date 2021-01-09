/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
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
package com.root101.utils.refraction;

import com.root101.utils.interfaces.Filtrable;
import com.root101.utils.interfaces.Formateable;
import java.lang.reflect.Field;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
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
                }//TODO: add un else con una recursividad siendo T obj = value
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
