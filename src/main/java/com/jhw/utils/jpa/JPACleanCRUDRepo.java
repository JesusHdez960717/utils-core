/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.utils.jpa;

import com.clean.core.app.repo.CRUDRepository;
import com.jhw.utils.others.Misc;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import com.clean.core.utils.SortBy;
import com.clean.core.utils.validation.Validable;
import com.clean.core.utils.validation.ValidationResult;
import com.jhw.utils.services.ConverterService;
import java.util.Collections;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 * @param <Domain>
 * @param <Entity>
 */
public class JPACleanCRUDRepo<Domain, Entity> implements CRUDRepository<Domain> {

    private transient final java.beans.PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);

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
        
        Entity entity = ConverterService.convert(domain, entityClass);
        jpaController.create(entity);
        domain = ConverterService.convert(entity, domainClass);

        firePropertyChange("create", null, domain);

        return domain;
    }

    @Override
    public Domain edit(Domain domain) throws Exception {
        validateDomain(domain);
        
        Entity entity = ConverterService.convert(domain, entityClass);
        jpaController.edit(entity);
        domain = ConverterService.convert(entity, domainClass);

        firePropertyChange("edit", null, domain);

        return domain;
    }

    @Override
    public Domain destroy(Domain domain) throws Exception {
        Entity entity = ConverterService.convert(domain, entityClass);
        jpaController.destroy(entity);
        domain = ConverterService.convert(entity, domainClass);

        firePropertyChange("destroy", null, domain);

        return domain;
    }

    @Override
    public Domain destroyById(Object keyId) throws Exception {
        Entity entity = jpaController.destroyById(keyId);
        Domain domain = ConverterService.convert(entity, domainClass);

        firePropertyChange("destroyById", null, domain);

        return domain;
    }

    @Override
    public Domain findBy(Object keyId) throws Exception {
        Entity entity = jpaController.findBy(keyId);
        Domain domain = ConverterService.convert(entity, domainClass);

        firePropertyChange("findBy", null, domain);

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
            answ.add(ConverterService.convert(job, domainClass));
        }

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

        firePropertyChange("findAll", null, answ);

        return answ;
    }

    @Override
    public int count() throws Exception {
        int count = jpaController.count();

        firePropertyChange("count", 0, count);

        return count;
    }

    private void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
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
    public void removePropertyChangeListener(java.beans.PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private ValidationResult validateDomain(Domain domain) throws Exception {
        if (domain instanceof Validable) {
            return ((Validable) domain).validate().throwException();
        }
        return new ValidationResult();
    }
}
