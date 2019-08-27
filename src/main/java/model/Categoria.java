package model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.checkerframework.checker.nullness.qual.NonNull;

@Entity
public class Categoria extends Identifiable implements Serializable {

    @Column(length = 20, unique = true, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "categoria")
    List<Producto> productos = Lists.newArrayList();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria setNombre(@NonNull String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Categoria categoria = (Categoria) o;
        return getId() == categoria.getId() &&
               Objects.equals(getNombre(), categoria.getNombre());
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("id", id)
                          .add("nombre", nombre)
                          .toString();
    }
}
