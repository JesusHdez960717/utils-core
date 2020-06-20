package com.jhw.utils.exceptions;

import com.jhw.utils.jackson.JACKSON;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class ExceptionHandlerUtil {

    public static boolean saveException(File f, Exception e) {
        try {
            ArrayList<Exception> exc;
            try {
                exc = JACKSON.read(f, JACKSON.getTypeFactory().constructCollectionType(ArrayList.class, Exception.class));
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
