package app.data;

import app.model.Categoria;
import app.model.Producto;
import app.model.Venta;
import javax.persistence.EntityManager;

public class DataStore {

    private static EntityManager manager = ConnectionFactory.getEntityManager();

    private static IdentifiableDao<Categoria> categorias = new IdentifiableDao<>(manager, Categoria.class);
    private static IdentifiableDao<Producto> productos = new IdentifiableDao<>(manager, Producto.class);
    private static IdentifiableDao<Venta> ventas = new IdentifiableDao<>(manager, Venta.class);

//    private static GenericDao<Vendido, VendidoId> categorias = new IdentifiableDao<>(manager, Categoria.class);


    public static EntityManager getManager() {
        return manager;
    }

    public static IdentifiableDao<Categoria> getCategorias() {
        return categorias;
    }

    public static IdentifiableDao<Producto> getProductos() {
        return productos;
    }

    public static IdentifiableDao<Venta> getVentas() {
        return ventas;
    }

    public static void initialQuery() {
        categorias.findAll();
        productos.findAll();
        ventas.findAll();

    }
}
