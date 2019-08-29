package app.model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
//@SequenceGenerator(name="default_generator", sequenceName = "categoria_seq", allocationSize=50)
public class Categoria extends Identifiable implements Serializable {

    @Column(length = 20, unique = true, nullable = false)
    private String nombre;

    public Categoria() {
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<Producto> productos = Lists.newArrayList();

    public String getNombre() {
        return nombre;
    }

    public Categoria setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
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
        return Objects.hashCode(getId());
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("id", id)
                          .add("nombre", nombre)
                          .toString();
    }
}
