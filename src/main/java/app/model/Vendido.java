package app.model;

import app.data.api.IEntity;
import com.google.common.base.MoreObjects;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vendido implements IEntity<VendidoId> {

    @EmbeddedId
    private VendidoId vendidoId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idVenta", insertable = false, updatable = false, nullable = false)
    private Venta venta;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idProducto", insertable = false, updatable = false, nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private float precioUnidad;

    @Override
    public VendidoId getId() {
        return new VendidoId();
    }

    @Override
    public void setId(VendidoId id) {

    }

    public float getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(float precio) {
        this.precioUnidad = precio;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vendido vendido = (Vendido) o;
        return cantidad == vendido.cantidad &&
               Float.compare(vendido.getPrecioUnidad(), getPrecioUnidad()) == 0 &&
               Objects.equals(getVenta(), vendido.getVenta()) &&
               Objects.equals(getProducto(), vendido.getProducto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVenta(), getProducto());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("venta", venta)
                          .add("producto", producto)
                          .add("cantidad", cantidad)
                          .add("precioUnidad", precioUnidad)
                          .toString();
    }
}
