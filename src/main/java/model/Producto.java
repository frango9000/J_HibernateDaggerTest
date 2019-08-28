package model;

import com.google.common.base.MoreObjects;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.checkerframework.checker.nullness.qual.NonNull;

@Entity
//@SequenceGenerator(name="default_generator", sequenceName = "producto_seq", allocationSize=50)
public class Producto extends Identifiable implements Serializable {

    @Column(length = 20, unique = true, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private float precio;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idCategoria", nullable = false)
    private Categoria categoria;

//    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
//    List<Vendido> ventas = Lists.newArrayList();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(@NonNull Categoria categoria) {
        this.categoria = categoria;
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
