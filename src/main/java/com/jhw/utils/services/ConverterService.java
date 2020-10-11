/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.utils.services;

import com.jhw.utils.jackson.JACKSON;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ConverterService {

    public static <T> T convert(Object objectToConvert, Class<? extends T> convertToClass) throws Exception {
        return JACKSON.convert(objectToConvert, convertToClass);
    }

    public static <T> List<T> convert(List list, Class<? extends T> convertToClass) throws Exception {
        return JACKSON.convert(list, convertToClass);
    }

    public static <T> List<Object[]> convert(List<T> values, Function<T, Object[]> converterFunction) {
        List<Object[]> answ = new ArrayList<>(values.size());
        for (T value : values) {
            answ.add(converterFunction.apply(value));
        }
        return answ;
    }

    public static <T> List<Map<String, Object>> convert(String[] names, List<T> values, Function<T, Object[]> converterFunction) {
        List<Map<String, Object>> m = new ArrayList<>();
        for (T object : values) {
            Object objs[] = converterFunction.apply(object);

            Map<String, Object> mAux = new HashMap<>();
            for (int i = 0; i < objs.length; i++) {
                mAux.put(names[i], objs[i]);
            }
            m.add(mAux);
        }
        return m;
    }
}
