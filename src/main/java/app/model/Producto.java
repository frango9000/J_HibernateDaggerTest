package app.model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
//@SequenceGenerator(name="default_generator", sequenceName = "producto_seq", allocationSize=50)
public class Producto extends Identifiable implements Serializable {

    @Column(length = 20, unique = true, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int precio;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idCategoria", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private Set<Vendido> ventas = Sets.newHashSet();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<Vendido> getVentas() {
        return ventas;
    }

    public void setVentas(Set<Vendido> ventas) {
        this.ventas = ventas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Producto producto = (Producto) o;
        return getId() == producto.getId() &&
               Float.compare(producto.getPrecio(), getPrecio()) == 0 &&
               Objects.equals(getNombre(), producto.getNombre()) &&
               Objects.equals(getCategoria(), producto.getCategoria());
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
                          .add("precio", precio)
                          .add("categoria", categoria.toString())
                          .toString();
    }
}
