package app.model;

import com.google.common.base.MoreObjects;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class VendidoId implements Serializable {

    public static final long serialVersionUID = 1L;

    private int idVenta;
    private int idProducto;

    public VendidoId() {
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        VendidoId vendidoId = (VendidoId) o;
        return getIdVenta() == vendidoId.getIdVenta() &&
               getIdProducto() == vendidoId.getIdProducto();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdVenta(), getIdProducto());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("venta", idVenta)
                          .add("producto", idProducto)
                          .toString();
    }
}
