package model;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.NonNull;

public class Vendido {

    private Venta venta;
    private Producto producto;
    private float precio;

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(@NonNull Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(@NonNull Producto producto) {
        this.producto = producto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vendido vendido = (Vendido) o;
        return Float.compare(vendido.getPrecio(), getPrecio()) == 0 &&
               Objects.equals(getVenta(), vendido.getVenta()) &&
               Objects.equals(getProducto(), vendido.getProducto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVenta().getId(), getProducto().getId());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("venta", venta.toString())
                          .add("producto", producto.toString())
                          .add("precio", precio)
                          .toString();
    }
}
