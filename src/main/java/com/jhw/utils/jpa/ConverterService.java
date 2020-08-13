/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.utils.jpa;

import com.jhw.utils.jackson.JACKSON;
import java.util.List;

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

}
