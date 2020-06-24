package com.jhw.utils.exceptions;

import com.jhw.utils.jackson.JACKSON;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ExceptionHandlerUtil {

    /**
     * Add the exception to the file as an exception's arrays
     * @param f file to save the exception
     * @param e exception to save
     * @return true if the exception was saved ok in the file
     */
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
