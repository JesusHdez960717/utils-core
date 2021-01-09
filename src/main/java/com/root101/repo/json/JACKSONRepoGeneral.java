/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.root101.repo.json;

import com.root101.clean.core.app.repo.ReadWriteRepository;
import com.root101.clean.core.utils.validation.Validable;
import com.root101.clean.core.utils.validation.ValidationResult;
import com.root101.json.jackson.JACKSON;
import java.io.File;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
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
