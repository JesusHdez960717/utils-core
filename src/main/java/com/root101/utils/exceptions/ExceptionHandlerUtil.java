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
package com.root101.utils.exceptions;

import com.root101.json.jackson.JACKSON;
import java.io.File;
import java.util.ArrayList;

/**
 * 
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class ExceptionHandlerUtil {

    /**
     * Add the exception to a new File
     *
     * @param f file to save the exception
     * @param e exception to save
     * @return true if the exception was saved ok in the file
     */
    public static boolean saveException(File f, Throwable e) {
        try {
            File folder = new File(f.getPath() + File.separator);
            folder.mkdirs();
            File file = new File(folder, System.currentTimeMillis() + ".json");
            JACKSON.write(file, e);
        } catch (Exception errorWriting) {
            return false;
        }
        return true;
    }

    /**
     * Add the exception to the file as an exception's arrays
     *
     * @param f file to save the exception
     * @param e exception to save
     * @return true if the exception was saved ok in the file
     */
    public static boolean saveExceptionSingleFile(File f, Throwable e) {
        try {
            ArrayList<Throwable> exc;
            try {
                exc = JACKSON.read(f, JACKSON.getTypeFactory().constructCollectionType(ArrayList.class, Throwable.class));
            } catch (Exception exception) {
                exc = new ArrayList<>();
            }
            exc.add(e);
            JACKSON.write(f, exc);
        } catch (Exception errorWriting) {
            return false;
        }
        return true;
    }
}
