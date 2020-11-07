/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.utils.jpa;

import com.clean.core.app.repo.CRUDLightweightRepo;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class JPACleanCRUDLightRepo<Domain, Entity> extends JPACleanCRUDRepo<Domain, Entity> implements CRUDLightweightRepo<Domain> {

    public JPACleanCRUDLightRepo(EntityManagerFactory emf, Class<Domain> domainClass, Class<Entity> entityClass) {
        super(emf, domainClass, entityClass);
    }

    @Override
    public void create_light(Domain newObject) throws Exception {
        super.create(newObject);
        firePropertyChange("create_light", null, null);
    }

    @Override
    public void edit_light(Domain objectToUpdate) throws Exception {
        super.edit(objectToUpdate);
        firePropertyChange("edit_light", null, null);
    }

    @Override
    public void destroy_light(Domain objectToDestroy) throws Exception {
        super.destroy(objectToDestroy);
        firePropertyChange("destroy_light", null, null);
    }

    @Override
    public void destroyById_light(Object keyId) throws Exception {
        super.destroyById(keyId);
        firePropertyChange("destroyById_light", null, null);
    }

}
