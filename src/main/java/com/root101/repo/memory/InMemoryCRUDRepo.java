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
package com.root101.repo.memory;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import com.root101.clean.core.app.repo.CRUDRepository;
import com.root101.clean.core.utils.validation.Validable;
import com.root101.clean.core.utils.validation.ValidationResult;
import static com.root101.clean.core.app.PropertyChangeConstrains.*;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 * @param <Domain>
 */
public class InMemoryCRUDRepo<Domain extends Comparable<Domain>> implements CRUDRepository<Domain> {

    private transient final java.beans.PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    private final TreeSet<Domain> list = new TreeSet<>();

    @Override
    public Domain create(Domain domain) throws RuntimeException {
        firePropertyChange(BEFORE_CREATE, null, domain);
        validateDomain(domain);

        list.add(domain);

        firePropertyChange(AFTER_CREATE, null, domain);

        return domain;
    }

    @Override
    public Domain edit(Domain domain) throws RuntimeException {
        firePropertyChange(BEFORE_EDIT, null, domain);
        validateDomain(domain);

        list.remove(domain);
        list.add(domain);

        firePropertyChange(AFTER_EDIT, null, domain);

        return domain;
    }

    @Override
    public Domain destroy(Domain domain) throws RuntimeException {
        firePropertyChange(BEFORE_DESTROY, null, domain);

        list.remove(domain);

        firePropertyChange(AFTER_DESTROY, null, domain);

        return domain;
    }

    @Override
    public Domain destroyById(Object keyId) throws RuntimeException {
        firePropertyChange(BEFORE_DESTROY_BY_ID, null, keyId);

        Domain domain = findBy(keyId);
        list.remove(domain);

        firePropertyChange(AFTER_DESTROY_BY_ID, null, domain);

        return domain;
    }

    @Override
    public Domain findBy(Object keyId) throws RuntimeException {
        firePropertyChange(BEFORE_FIND_BY, null, keyId);

        Domain domain = list.stream().findFirst().get();

        firePropertyChange(AFTER_FIND_BY, null, domain);

        return domain;
    }

    /**
     * Para hacer el sort por annotation se le hace un reverse a los fields para
     * darle mayor peso a los primeros.
     *
     * @return
     * @throws RuntimeException
     */
    @Override
    public List<Domain> findAll() throws RuntimeException {
        firePropertyChange(BEFORE_FIND_ALL, null, null);

        //convert entities to domain
        List<Domain> answ = list.stream().collect(Collectors.toList());

        firePropertyChange(AFTER_FIND_ALL, null, answ);

        return answ;
    }

    @Override
    public int count() throws RuntimeException {
        firePropertyChange(BEFORE_COUNT, 0, 0);

        int count = list.size();

        firePropertyChange(AFTER_COUNT, 0, count);

        return count;
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    @Override
    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    @Override
    public void removePropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private ValidationResult validateDomain(Domain domain) throws RuntimeException {
        if (domain instanceof Validable) {
            return ((Validable) domain).validate().throwException();
        }
        return new ValidationResult();
    }
}
