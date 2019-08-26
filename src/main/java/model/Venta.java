package model;

import com.google.common.base.MoreObjects;
import java.time.LocalDateTime;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.NonNull;

public class Venta {

    private int id;
    private LocalDateTime fecha_hora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(@NonNull LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
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
        return getId();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("id", id)
                          .add("fecha_hora", fecha_hora)
                          .toString();
    }
}
