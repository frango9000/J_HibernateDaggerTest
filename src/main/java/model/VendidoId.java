package model;

import java.io.Serializable;
import java.util.Objects;

public class VendidoId implements Serializable {

    public static final long serialVersionUID = 1L;

    private int venta;
    private int producto;

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        VendidoId vendidoId = (VendidoId) o;
        return getVenta() == vendidoId.getVenta() &&
               getProducto() == vendidoId.getProducto();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVenta(), getProducto());
    }
}
