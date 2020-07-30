package com.jhw.utils.file;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
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
            throw new IOException(fFrom.getAbsolutePath() + " don't found.");
        }
    }

    public static void delete(String path) throws IOException {
        File f = new File(path);
        if (f.isDirectory()) {
            FileUtils.deleteDirectory(f);
        } else if (f.isFile()) {
            f.delete();
        } else {
            throw new IOException("The path is not a directori neither a file.");
        }
    }
}
