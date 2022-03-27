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
package dev.root101.json;

import dev.root101.clean.core.app.services.ConverterHandler;
import dev.root101.clean.core.app.services.ConverterService;
import dev.root101.json.jackson.JACKSON;
import java.util.List;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class ConverterServiceJSONImpl implements ConverterService {

    private static final ConverterServiceJSONImpl INSTANCE;

    static {
        INSTANCE = new ConverterServiceJSONImpl();
        ConverterHandler.registerConverterService(INSTANCE);
    }

    public static final ConverterServiceJSONImpl INSTANCE() {
        return INSTANCE;
    }

    private ConverterServiceJSONImpl() {
    }

    @Override
    public <T> T convert(Object objectToConvert, Class<? extends T> convertToClass) throws RuntimeException {
        try {
            return JACKSON.convert(objectToConvert, convertToClass);
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public <T> List<T> convert(List list, Class<? extends T> convertToClass) throws RuntimeException {
        try {
            return JACKSON.convert(list, convertToClass);
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
    }

}
