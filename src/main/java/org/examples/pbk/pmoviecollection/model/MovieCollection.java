package org.examples.pbk.pmoviecollection.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  MovieCollection represents collection of the movies that might be
 *  held by some person.
 *  Or collection of the movies that some person wants to watch.
 */
@Entity
@Table (name = "moviecollection")
public class MovieCollection implements Serializable {

    private long id;
    private String collectionName;
    private List<Movie> collection;

    private User user;

    public MovieCollection() {
    }

    public MovieCollection(String collectionName, List<Movie> movies, User user) {
        this.collectionName = collectionName;
        this.collection = movies;
        this.user = user;
    }

    public MovieCollection(long id, String collectionName, List<Movie> movies, User user) {
        this.id = id;
        this.collectionName = collectionName;
        this.collection = movies;
        this.user = user;
    }

    /**
     * @return unique identifier of this collection
     */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column (name = "collection_name")
    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    /**
     * @return list of movies storing in this collection.
     */
    @OneToMany
    @JoinTable(name = "moviecollection_movie",
            joinColumns = @JoinColumn(name = "collection_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    public List<Movie> getCollection() {
        return collection;
    }

    public void setCollection(List<Movie> collection) {
        this.collection = collection;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    //@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "MovieCollection{" +
                "id=" + id +
                ", collectionName='" + collectionName + '\'' +
                ", collection=" + collection +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieCollection that = (MovieCollection) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
