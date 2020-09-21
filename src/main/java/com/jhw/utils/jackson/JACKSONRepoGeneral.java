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
 * @param <T>
 */
public abstract class JACKSONRepoGeneral<T> implements ReadWriteRepository<T> {

    private transient final java.beans.PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    private final File file;
    private final Class<T> clazz;

    public JACKSONRepoGeneral(String file, Class clazz) {
        this.file = new File(file);
        if (this.file.getParentFile() != null) {
            this.file.getParentFile().mkdirs();
        }
        this.clazz = clazz;
    }

    @Override
    public T read() throws Exception {
        try {
            T t = JACKSON.read(file, clazz);
            firePropertyChange("read", null, t);
            return t;
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
        firePropertyChange("write", null, object);
    }

    public File getFile() {
        return file;
    }

    protected abstract void onReadError(Exception e);

    @Override
    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
}
