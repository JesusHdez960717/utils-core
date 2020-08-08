/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.utils.jpa;

import com.clean.core.app.repo.CRUDRepository;
import com.jhw.utils.jackson.JACKSON;
import com.jhw.utils.others.Misc;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import com.clean.core.utils.SortBy;
import com.clean.core.utils.validation.Validable;
import com.clean.core.utils.validation.ValidationResult;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 * @param <Domain>
 * @param <Entity>
 */
public class JPACleanCRUDRepo<Domain, Entity> implements CRUDRepository<Domain> {

    public static final String PROP_COUNT = "count";
    public static final String PROP_CREATE = "create";
    public static final String PROP_DESTROY = "destroy";
    public static final String PROP_DESTROY_BY_ID = "destroyById";
    public static final String PROP_EDIT = "edit";
    public static final String PROP_FIND_ALL = "findAll";
    public static final String PROP_FIND_BY = "findBy";

    private transient final java.beans.PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private final JPAControllerGeneral<Entity> jpaController;
    private final Class<Domain> domainClass;
    private final Class<Entity> entityClass;

    public JPACleanCRUDRepo(EntityManagerFactory emf, Class<Domain> domainClass, Class<Entity> entityClass) {
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
    public Domain create(Domain domain) throws Exception {
        validateDomain(domain);

        //copy of the object to the oldValue
        Domain domainOld = JACKSON.convert(domain, domainClass);

        Entity entity = JACKSON.convert(domain, entityClass);
        jpaController.create(entity);
        domain = JACKSON.convert(entity, domainClass);

        firePropertyChange(PROP_CREATE, domainOld, domain);

        return domain;
    }

    @Override
    public Domain edit(Domain domain) throws Exception {
        validateDomain(domain);

        //copy of the object to the oldValue
        Domain domainOld = JACKSON.convert(domain, domainClass);

        Entity entity = JACKSON.convert(domain, entityClass);
        jpaController.edit(entity);
        domain = JACKSON.convert(entity, domainClass);

        firePropertyChange(PROP_EDIT, domainOld, domain);

        return domain;
    }

    @Override
    public Domain destroy(Domain domain) throws Exception {
        //copy of the object to the oldValue
        Domain domainOld = JACKSON.convert(domain, domainClass);

        Entity entity = JACKSON.convert(domain, entityClass);
        jpaController.destroy(entity);
        domain = JACKSON.convert(entity, domainClass);

        firePropertyChange(PROP_DESTROY, domainOld, domain);

        return domain;
    }

    @Override
    public Domain destroyById(Object keyId) throws Exception {
        Entity entity = jpaController.destroyById(keyId);
        Domain domain = JACKSON.convert(entity, domainClass);

        firePropertyChange(PROP_DESTROY_BY_ID, keyId, domain);

        return domain;
    }

    @Override
    public Domain findBy(Object keyId) throws Exception {
        Entity entity = jpaController.findBy(keyId);
        Domain domain = JACKSON.convert(entity, domainClass);

        firePropertyChange(PROP_FIND_BY, keyId, domain);

        return domain;
    }

    /**
     * Para hacer el sort por annotation se le hace un reverse a los fields para
     * darle mayor peso a los primeros.
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Domain> findAll() throws Exception {
        List<Entity> list = jpaController.findAll();//find all entities

        //convert entities to domain
        List<Domain> answ = new ArrayList<>(list.size());
        for (Entity job : list) {
            answ.add(JACKSON.convert(job, domainClass));
        }

        //sort domain list acording to SortBy annotation if exists
        SortBy annot[] = domainClass.getDeclaredAnnotationsByType(SortBy.class);
        if (annot == null || annot.length == 0) {//si no tiene el annotation no ordeno nada
            return answ;
        }
        for (SortBy actualAnnotation : Misc.reverse(annot)) {
            Misc.sortByAnnotation(answ, domainClass, Misc.reverse(actualAnnotation.priority()), actualAnnotation.order());
        }

        firePropertyChange(PROP_FIND_BY, null, answ);
        
        return answ;
    }

    @Override
    public int count() throws Exception {
        int count = jpaController.count();
        
        firePropertyChange(PROP_COUNT, 0, count);
        
        return count;
    }

    private void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

    private ValidationResult validateDomain(Domain domain) throws Exception {
        if (domain instanceof Validable) {
            return ((Validable) domain).validate().throwException();
        }
        throw new NullPointerException();
    }
}
