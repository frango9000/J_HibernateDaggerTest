package app.model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
//@SequenceGenerator(name="default_generator", sequenceName = "venta_seq", allocationSize=50)
public class Venta extends Identifiable implements Serializable {

    @Column
    private LocalDateTime fecha_hora;

    @OneToMany(mappedBy = "venta", fetch = FetchType.EAGER)
    private List<Vendido> productos = Lists.newArrayList();

    private LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public List<Vendido> getProductos() {
        return productos;
    }

    public void setProductos(List<Vendido> productos) {
        this.productos = productos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Venta venta = (Venta) o;
        return getId() == venta.getId() &&
               Objects.equals(getFecha_hora(), venta.getFecha_hora());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("id", id)
                          .add("fecha_hora", fecha_hora)
                          .toString();
    }
}
