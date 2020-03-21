/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Yo
 */
public class FILE {

    public static void copy(String desde, String hasta) throws IOException {
        File fFrom = new File(desde);
        File fTo = new File(hasta);
        if (fFrom.isDirectory()) {//voy a copiar un directorio
            if (fTo.isFile()) {
                throw new IOException("Can't copy a directory inside a file");
            } else {//directory inside directory
                fTo.mkdirs();
                FileUtils.copyDirectoryToDirectory(fFrom, fTo);//ok
            }
        } else if (fFrom.isFile()) {
            if (fTo.isDirectory()) {//file into directory
                FileUtils.copyFileToDirectory(fFrom, fTo);
            } else {//file to file
                FileUtils.copyFile(fFrom, fTo);//ok
            }
        } else {
            throw new IOException("File don't found.");
        }
    }
}
