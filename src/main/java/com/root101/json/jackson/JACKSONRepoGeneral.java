/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.json.jackson;

import com.root101.clean.core.app.repo.ReadWriteRepository;
import com.root101.clean.core.utils.validation.Validable;
import com.root101.clean.core.utils.validation.ValidationResult;
import java.io.File;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 * @param <Domain>
 */
public abstract class JACKSONRepoGeneral<Domain> implements ReadWriteRepository<Domain> {

    private transient final java.beans.PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    private final File file;
    private final Class<Domain> clazz;

    public JACKSONRepoGeneral(String file, Class clazz) {
        this.file = new File(file);
        if (this.file.getParentFile() != null) {
            this.file.getParentFile().mkdirs();
        }
        this.clazz = clazz;
    }

    @Override
    public Domain read() throws Exception {
        try {
            Domain t = JACKSON.read(file, clazz);
            firePropertyChange("read", null, t);
            return t;
        } catch (Exception e) {
            Domain neww = clazz.newInstance();
            try {
                write(neww);
            } catch (Exception ex) {
            }
            onReadError(e);
            return neww;
        }
    }

    @Override
    public void write(Domain object) throws Exception {
        validateDomain(object);
        
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

    private ValidationResult validateDomain(Domain domain) throws Exception {
        if (domain instanceof Validable) {
            return ((Validable) domain).validate().throwException();
        }
        return new ValidationResult();
    }
}
