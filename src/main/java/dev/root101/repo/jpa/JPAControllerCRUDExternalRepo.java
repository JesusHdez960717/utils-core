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
package dev.root101.repo.jpa;

import dev.root101.clean.core.exceptions.ValidationException;
import dev.root101.clean.core.utils.validation.Validable;
import dev.root101.clean.core.utils.validation.ValidationMessage;
import dev.root101.clean.core.utils.validation.ValidationResult;
import java.util.List;
import java.util.function.Supplier;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Para hacer la coneccion dinamica, se puede lo mismo pasarle el Supplier que
 * sobreescribir el metodo entityManagerFactory().
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 * @param <Entity>
 */
public class JPAControllerCRUDExternalRepo<Entity> implements Validable {

    private Supplier<EntityManagerFactory> emf = null;
    private final Class<Entity> classType;


    public JPAControllerCRUDExternalRepo(Supplier<EntityManagerFactory> emfSupplier, Class<Entity> classType) {
        this.emf = emfSupplier;
        this.classType = classType;
        validate();
    }

    protected EntityManagerFactory entityManagerFactory() {
        return emf.get();
    }

    protected EntityManager getEntityManager() {
        return entityManagerFactory().createEntityManager();
    }

    public Entity create(Entity object) throws RuntimeException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return object;
    }

    public Entity edit(Entity object) throws RuntimeException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            Object id = null;
            //find id
            try {
                id = JPAControllerGeneralUtils.getId(object);//lanza excepcion por el refractor
            } catch (Exception e) {
            }

            //check the id isn't null
            if (id == null) {//solo es null si dio error el getId de arriba
                throw new NonExistingEntityException("To edit " + object + " the id can't be null");
            }

            //check if still exist
            Entity persistedObject = findBy(id);
            if (persistedObject == null) {
                throw new NonExistingEntityException(object + " no longer exists.");
            }

            //edit it
            em.merge(object);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return object;
    }

    public Entity findBy(Object id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(classType, id);//return null if don't exits
        } finally {
            em.close();
        }
    }

    public Entity destroy(Entity object) throws RuntimeException {
        try {
            return destroyById(JPAControllerGeneralUtils.getId(object));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Entity destroyById(Object id) throws RuntimeException {
        EntityManager em = null;
        Entity persistedObject;

        try {
            em = getEntityManager();
            em.getTransaction().begin();

            persistedObject = em.find(classType, id);
            if (persistedObject == null) {
                throw new NonExistingEntityException(id + " no longer exists.");
            }

            em.remove(persistedObject);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return persistedObject;
    }

    public List<Entity> findAll() {
        return findAll(true, -1, -1);
    }

    public List<Entity> findAll(int maxResults, int firstResult) {
        return findAll(false, maxResults, firstResult);
    }

    private List<Entity> findAll(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(classType));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entity> rt = cq.from(classType);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public ValidationResult validate() throws ValidationException {
        ValidationResult val = ValidationResult.build();
        if (!JPAControllerGeneralUtils.isEntity(classType)) {
            val.add(ValidationMessage.from("classType", classType, classType + "isn't an javax.persistence.Entity"));
        }
        return val.throwException();
    }
}
