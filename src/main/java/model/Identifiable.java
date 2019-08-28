package model;

import data.api.IEntity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Identifiable implements IEntity<Integer> {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = IEntity.P_ID, updatable = false, nullable = false)
    protected int id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
