package app.model;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"idVenta", "idProducto"}))
public class Vendido extends Identifiable {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idVenta", nullable = false)
    private Venta venta;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private float precioUnidad;

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
        this.venta.addVendidos(this);
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
        return getCantidad() == vendido.getCantidad() &&
               Float.compare(vendido.getPrecioUnidad(), getPrecioUnidad()) == 0 &&
               Objects.equals(getVenta(), vendido.getVenta()) &&
               Objects.equals(getProducto(), vendido.getProducto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
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
