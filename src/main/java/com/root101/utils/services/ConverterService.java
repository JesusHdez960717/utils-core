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
package com.root101.utils.services;

import com.root101.json.jackson.JACKSON;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
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
