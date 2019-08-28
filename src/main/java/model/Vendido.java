package model;

import com.google.common.base.MoreObjects;
import data.api.IEntity;
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
    private VendidoId id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(insertable = false, updatable = true, nullable = false)
    private Venta venta;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(insertable = false, updatable = true, nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private float precio;

    @Override
    public VendidoId getId() {
        return id;
    }

    @Override
    public void setId(VendidoId id) {
        this.id = id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vendido vendido = (Vendido) o;
        return Float.compare(vendido.getPrecio(), getPrecio()) == 0 &&
               Objects.equals(getId(), vendido.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("id", id)
                          .add("precio", precio)
                          .toString();
    }
}
