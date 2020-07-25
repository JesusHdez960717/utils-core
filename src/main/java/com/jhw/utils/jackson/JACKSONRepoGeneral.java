/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.utils.jackson;

import com.clean.core.app.repo.ReadWriteRepository;
import java.io.File;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public abstract class JACKSONRepoGeneral<T> implements ReadWriteRepository<T> {

    private final File file;
    private final Class<T> clazz;

    public JACKSONRepoGeneral(String file, Class clazz) {
        this.file = new File(file);
        this.clazz = clazz;
    }

    @Override
    public T read() throws Exception {
        try {
            return JACKSON.read(file, clazz);
        } catch (Exception e) {
            T neww = clazz.newInstance();
            try {
                write(neww);
            } catch (Exception ex) {
            }
            onReadError(e);
            return neww;
        }
    }

    @Override
    public void write(T object) throws Exception {
        JACKSON.write(file, object);
    }

    public File getFile() {
        return file;
    }

    protected abstract void onReadError(Exception e);

}
