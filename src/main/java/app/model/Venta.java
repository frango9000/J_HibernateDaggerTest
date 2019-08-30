package app.model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
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
    private Set<Vendido> vendidos = Sets.newHashSet();

    private LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public Set<Vendido> getVendidos() {
        return vendidos;
    }

    public void setVendidos(Set<Vendido> vendidos) {
        this.vendidos = vendidos;
    }

    public void addVendidos(Vendido vendido) {
        vendidos.add(vendido);
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
