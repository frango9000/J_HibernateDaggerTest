import data.ConnectionFactory;
import javax.persistence.EntityManager;
import model.Categoria;

public class Main {

    public static void main(String[] args) {
        EntityManager manager = ConnectionFactory.getEntityManager();

        Categoria categoria = new Categoria().setNombre("Cat2");
        manager.getTransaction().begin();
        manager.persist(categoria);
        manager.getTransaction().commit();

        System.out.println(manager.find(Categoria.class, 1).toString());

        System.exit(0);
    }

}
