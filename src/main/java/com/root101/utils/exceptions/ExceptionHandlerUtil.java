package com.root101.utils.exceptions;

import com.root101.json.jackson.JACKSON;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
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
