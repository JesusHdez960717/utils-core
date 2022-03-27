/*
 * Copyright 2022 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
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
package dev.root101.repo.memory;

import static dev.root101.clean.core.app.PropertyChangeConstrains.*;
import dev.root101.clean.core.app.repo.ReadWriteRepository;
import dev.root101.clean.core.utils.validation.Validable;
import dev.root101.clean.core.utils.validation.ValidationResult;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 * @param <Domain>
 */
public class InMemoryReadWriteRepo<Domain> implements ReadWriteRepository<Domain> {

    private transient final java.beans.PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    private Domain object;

    @Override
    public Domain read() throws RuntimeException {
        firePropertyChange(BEFORE_READ, null, null);
        firePropertyChange(AFTER_READ, null, object);
        return object;
    }

    @Override
    public void write(Domain object) throws RuntimeException {
        validateDomain(object);
        try {
            firePropertyChange(BEFORE_WRITE, null, object);

            this.object = object;

            firePropertyChange(AFTER_WRITE, null, object);
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
    }

    protected void onReadError(Exception e){}

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

    private ValidationResult validateDomain(Domain domain) throws RuntimeException {
        if (domain instanceof Validable validable) {
            return validable.validate().throwException();
        }
        return new ValidationResult();
    }
}
