package org.examples.pbk.pmoviecollection.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Vitalik on 26.07.2017.
 */
@Entity
@Table(name = "genre")
public class Genre implements Serializable {
    private long id;
    private String name;

    public Genre() {}

    public Genre(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        return id == genre.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
