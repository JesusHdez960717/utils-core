/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.utils.others;

import java.awt.Desktop;
import java.io.File;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class Opener {

    private final File file;

    public Opener(File file) {
        this.file = file;
    }

    public void open() throws Exception {
        Desktop.getDesktop().open(file);
    }
}
