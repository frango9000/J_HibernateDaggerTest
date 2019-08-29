package app.data;


import app.data.api.GenericDao;
import app.model.Identifiable;
import javax.persistence.EntityManager;

public class IdentifiableDao<V extends Identifiable> extends GenericDao<V, Integer> {


    public IdentifiableDao(EntityManager entityManager) {
        this(entityManager, null);
    }

    public IdentifiableDao(EntityManager entityManager, Class<V> clazz) {
        super(entityManager, clazz);
    }
}
