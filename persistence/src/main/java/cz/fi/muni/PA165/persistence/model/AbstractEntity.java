package cz.fi.muni.PA165.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Abstract superclass
 * @param <PK> Primary key for given entity
 * @author Boris Jadus
 */
@MappedSuperclass
public class AbstractEntity<PK extends Serializable> implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, insertable = false)
    private PK id;

    public AbstractEntity(){

    }

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                '}';
    }
}
