package data;

import com.google.common.collect.Sets;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import misc.Globals;
import model.Identifiable;

public class DataRepository<V extends Identifiable, K> implements Globals {

    private EntityManager entityManager;
    private Class<V> clazz;


    @SuppressWarnings("unchecked")
    public Class<V> getGenericClass() throws ClassNotFoundException {
        if (clazz == null) {
            Type mySuperclass = getClass().getGenericSuperclass();
            Type tType = ((ParameterizedType) mySuperclass).getActualTypeArguments()[0];
            String className = tType.toString().split(" ")[1];
            clazz = (Class<V>) Class.forName(className);
        }
        return clazz;
    }

    public DataRepository(EntityManager entityManager, Class<V> clazz) {
        this.entityManager = entityManager;
        this.clazz         = clazz;
    }

    public DataRepository(EntityManager entityManager) throws ClassNotFoundException {
        this.entityManager = entityManager;
        getGenericClass();
    }

    public Optional<V> findById(Integer id) {
        V v = entityManager.find(clazz, id);
        return v != null ? Optional.of(v) : Optional.empty();
    }

    public Set<V> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<V> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<V> entity = criteriaQuery.from(clazz);
        criteriaQuery.select(entity).orderBy(criteriaBuilder.asc(entity.get("id")));
        return Sets.newHashSet(entityManager.createQuery(criteriaQuery).getResultList());
    }

    public Set<V> findSome(Set<Integer> ids) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<V> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<V> entity = criteriaQuery.from(clazz);
        criteriaQuery.select(entity).where(entity.get("id").in(ids)).orderBy(criteriaBuilder.asc(entity.get("id")));
        return Sets.newHashSet(entityManager.createQuery(criteriaQuery).getResultList());
    }

}
