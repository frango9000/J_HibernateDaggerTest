package data;


import data.api.GenericDao;
import javax.persistence.EntityManager;
import model.Identifiable;

public class IdentifiableDao<V extends Identifiable> extends GenericDao<V, Integer> {


    public IdentifiableDao(EntityManager entityManager) {
        this(entityManager, null);
    }

    public IdentifiableDao(EntityManager entityManager, Class<V> clazz) {
        super(entityManager, clazz);
    }
}
