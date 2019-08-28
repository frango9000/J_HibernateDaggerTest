package data.api;

import com.google.common.collect.Sets;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import misc.Flogger;
import misc.Globals;

public abstract class GenericDao<E extends IEntity<I>, I extends Serializable> implements Globals {

    private EntityManager entityManager;
    protected Class<E> clazz; // E = Entity Class

    private CriteriaBuilder criteriaBuilder;

    private boolean isDefaultable;
    private boolean isActivable;
    private boolean isHiddenable;
    private boolean isInheritable;

    public GenericDao(EntityManager entityManager) {
        this(entityManager, null);
    }

    public GenericDao(EntityManager entityManager, Class<E> clazz) {
        setEntityManager(entityManager);
        if (clazz != null)
            this.clazz = clazz;
        else
            findGenericClass();
        checkGenericClass();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        criteriaBuilder    = entityManager.getCriteriaBuilder();
    }

    @SuppressWarnings("unchecked")
    public Class<E> findGenericClass() {
        if (clazz == null)
            clazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }

    public Optional<E> findById(I id) {
        E v = entityManager.find(clazz, id);
        return v != null ? Optional.of(v) : Optional.empty();
    }

    public Set<E> findAll() {
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<E> entity = criteriaQuery.from(clazz);
        criteriaQuery.select(entity).orderBy(criteriaBuilder.asc(entity.get(IEntity.P_ID)));
        return Sets.newHashSet(entityManager.createQuery(criteriaQuery).getResultList());
    }

    public Set<E> findSome(final I... ids) {
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<E> entity = criteriaQuery.from(clazz);
        criteriaQuery.select(entity)
                     .where(entity.get(IEntity.P_ID).in(ids))
                     .orderBy(criteriaBuilder.asc(entity.get(IEntity.P_ID)));
        return Sets.newHashSet(entityManager.createQuery(criteriaQuery).getResultList());
    }

    public long count() {
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<E> entity = query.from(clazz);
        query.select(criteriaBuilder.count(entity));
        return entityManager.createQuery(query).getSingleResult();
    }

    public Optional<E> save(E entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return Optional.of(entity);
        } catch (EntityExistsException e) {
            Flogger.atInfo().withCause(e).log();
            if (entityManager.getTransaction().isActive())
                entityManager.getTransaction().rollback();
        }
        return Optional.empty();
    }

    public final Set<E> save(final E... entities) {
        Set<E> returnSet = Sets.newHashSet();
        entityManager.getTransaction().begin();
        for (E entity : entities) {
            try {
                entityManager.persist(entity);
            } catch (EntityExistsException e) {
                returnSet.add(entity);
                Flogger.atInfo().withCause(e).log();
            }
        }
        entityManager.getTransaction().commit();
        return returnSet;
    }


    public void refresh(final E entity) {
        entityManager.refresh(entity);
    }


    public void delete(final I id) throws NoSuchElementException {
        delete(findById(id).orElseThrow());
    }


    public void delete(final I... ids) throws NoSuchElementException {
        deleteAll(findSome(ids), true);
    }


    public void delete(final E object) throws NoSuchElementException {
        if (isHiddenable) {
            ((IHiddenable) object).setHidden(true);
            entityManager.merge(object);
        } else {
            entityManager.remove(object);
        }
    }


    public void delete(final E... objects) throws NoSuchElementException {
        deleteAll(Arrays.asList(objects), true);
    }


    public void deleteAll() throws NoSuchElementException {
        deleteAll(findAll(), false);
    }

    private void deleteAll(final Collection<E> objects, boolean checkIdDefault) {
        if (checkIdDefault) {
            if (isDefaultable) {
                for (E object : objects) {
                    checkIfDefault(object);
                }
            }
        }
        if (isHiddenable) {
            for (E object : objects) {
                ((IHiddenable) object).setHidden(true);
                entityManager.merge(object);
            }
        } else {
            for (E object : objects) {
                entityManager.remove(object);
            }
        }
    }


    public void flush() {
        entityManager.flush();
    }


    public void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }


    public boolean isActivable() {
        return isActivable;
    }


    public boolean isDefaultable() {
        return isDefaultable;
    }


    public boolean isHiddenable() {
        return isHiddenable;
    }


    public boolean isInheritable() {
        return isInheritable;
    }

    private void checkGenericClass() {
        for (Class i : clazz.getInterfaces()) {
            if (i == IDefaultable.class) {
                isDefaultable = true;
            } else if (i == IActivable.class) {
                isActivable = true;
            } else if (i == IHiddenable.class) {
                isHiddenable = true;
            } else if (i == IInheritable.class) {
                isInheritable = true;
            }
        }
    }

    private void checkIfDefault(E entity) {
        if (((IDefaultable) entity).isDefault()) {
            throw new UnsupportedOperationException("can't delete default entity " + clazz + "#"
                                                    + entity.getId());
        }
    }
}
