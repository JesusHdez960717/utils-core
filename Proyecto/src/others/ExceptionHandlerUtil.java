/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import jackson.JACKSON;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Yo
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
