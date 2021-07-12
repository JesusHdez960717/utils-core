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
package com.root101.repo.jpa;

import static com.root101.clean.core.app.PropertyChangeConstrains.*;
import com.root101.clean.core.app.repo.CRUDRepository;
import com.root101.clean.core.app.repo.Converter;
import com.root101.utils.others.Misc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import com.root101.clean.core.utils.SortBy;
import com.root101.clean.core.utils.validation.Validable;
import com.root101.clean.core.utils.validation.ValidationResult;
import com.root101.clean.core.app.services.ConverterHandler;
import java.util.Collections;
import java.util.function.Supplier;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 * @param <Domain>
 * @param <Entity>
 */
public class JPACleanCRUDRepo<Domain, Entity> implements CRUDRepository<Domain> {

    private transient final java.beans.PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    private final JPAControllerGeneral<Entity> jpaController;
    private final Class<Domain> domainClass;
    private final Class<Entity> entityClass;

    /**
     * Converter por defecto, conversion por el servicio generalizado via JSON
     */
    protected Converter<Domain, Entity> converter = new Converter<Domain, Entity>() {
        @Override
        public Domain from(Entity entity) throws RuntimeException {
            return ConverterHandler.convert(entity, domainClass);
        }

        @Override
        public Entity to(Domain domain) throws RuntimeException {
            return ConverterHandler.convert(domain, entityClass);
        }
    };

    public Converter<Domain, Entity> getConverter() {
        return converter;
    }

    public void setConverter(Converter<Domain, Entity> converter) {
        this.converter = converter;
    }

    /**
     * Pasarle el supplier<EMF> para si hay cambios en runtime
     *
     * @param emf
     * @param domainClass
     * @param entityClass
     * @deprecated
     */
    @Deprecated
    public JPACleanCRUDRepo(EntityManagerFactory emf, Class<Domain> domainClass, Class<Entity> entityClass) {
        this.domainClass = domainClass;
        this.entityClass = entityClass;
        jpaController = new JPAControllerGeneral(emf, entityClass);
    }

    public JPACleanCRUDRepo(Supplier<EntityManagerFactory> emf, Class<Domain> domainClass, Class<Entity> entityClass) {
        this.domainClass = domainClass;
        this.entityClass = entityClass;
        jpaController = new JPAControllerGeneral(emf, entityClass);
    }

    public JPAControllerGeneral<Entity> getJPAController() {
        return jpaController;
    }

    public EntityManager getEntityManager() {
        return jpaController.getEntityManager();
    }

    @Override
    public Domain create(Domain domain) throws RuntimeException {
        firePropertyChange(BEFORE_CREATE, null, domain);
        validateDomain(domain);

        Entity entity = converter.to(domain);
        jpaController.create(entity);
        domain = converter.from(entity);

        firePropertyChange(AFTER_CREATE, null, domain);

        return domain;
    }

    @Override
    public Domain edit(Domain domain) throws RuntimeException {
        firePropertyChange(BEFORE_EDIT, null, domain);
        validateDomain(domain);

        Entity entity = converter.to(domain);
        jpaController.edit(entity);
        domain = converter.from(entity);

        firePropertyChange(AFTER_EDIT, null, domain);

        return domain;
    }

    @Override
    public Domain destroy(Domain domain) throws RuntimeException {
        firePropertyChange(BEFORE_DESTROY, null, domain);
        
        Entity entity = converter.to(domain);
        jpaController.destroy(entity);
        domain = converter.from(entity);

        firePropertyChange(AFTER_DESTROY, null, domain);

        return domain;
    }

    @Override
    public Domain destroyById(Object keyId) throws RuntimeException {
        firePropertyChange(BEFORE_DESTROY_BY_ID, null, keyId);
        
        Entity entity = jpaController.destroyById(keyId);
        Domain domain = converter.from(entity);

        firePropertyChange(AFTER_DESTROY_BY_ID, null, domain);

        return domain;
    }

    @Override
    public Domain findBy(Object keyId) throws RuntimeException {
        firePropertyChange(BEFORE_FIND_BY, null, keyId);
        
        Entity entity = jpaController.findBy(keyId);
        Domain domain = converter.from(entity);

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
        List<Entity> list = jpaController.findAll();//find all entities

        //convert entities to domain
        List<Domain> answ = converter.from(list);

        
        //-------------------------SORT---------------------------------
        //compara por el comparable si lo implementa
        if (Comparable.class.isAssignableFrom(domainClass)) {
            Collections.sort(answ, (g1, g2) -> {
                return ((Comparable) g1).compareTo(g2);
            });
        }

        //sort domain list acording to SortBy annotation if exists
        SortBy annot[] = domainClass.getDeclaredAnnotationsByType(SortBy.class);
        if (annot == null || annot.length == 0) {//si no tiene el annotation no ordeno nada
            return answ;
        }
        for (SortBy actualAnnotation : Misc.reverse(annot)) {
            Misc.sortByAnnotation(answ, domainClass, Misc.reverse(actualAnnotation.priority()), actualAnnotation.order());
        }
        //----------------------------------------------------------
        
        firePropertyChange(AFTER_FIND_ALL, null, answ);

        return answ;
    }

    @Override
    public int count() throws RuntimeException {
        firePropertyChange(BEFORE_COUNT, 0, 0);
        
        int count = jpaController.count();

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
