package org.examples.pbk.pmoviecollection.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  User represents some person identified by unique id or unique username.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private long id;
    private String username;
    private String password;
    private List<MovieCollection> movieCollections;
    /* Rep invariant:
     *    username.length > 2
     *    all characters in username are drawn from {A..Z, a..z, 0..9, _, -}
     *    password.length > 5
     */

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.movieCollections = new ArrayList<MovieCollection>();
    }

    /**
     * Make a user with a known unique id and username.
     *
     * @param id
     *          unique identifier for the user
     * @param username
     *          unique string user identifier. Must be 3 character length at least.
     *          Required to be a username as defined by getUsername() below.
     * @param password
     *          string representation of user password.
     *          Must be 6 character length at least.
     */
    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.movieCollections = new ArrayList<MovieCollection>();
    }

    public User(long id, String username, String password, List<MovieCollection> movieCollections) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.movieCollections = movieCollections;
    }

    /**
     * @return unique identifier of this user
     */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return unique username of this user
     *         Username is a nonempty sequence of letters (A-Z or
     *         a-z), digits, underscore ("_"), or hyphen ("-").
     */
    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column (name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return list of MovieCollections of this user
     */
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    public List<MovieCollection> getMovieCollections() {
        return movieCollections;
    }

    public void setMovieCollections(List<MovieCollection> movieCollections) {
        this.movieCollections = movieCollections;
    }

    /**
     * Add MovieCollection to collections list of this user
     *
     * @param movieCollection Not null MovieCollection as defined in MovieCollection class.
     */
    public void addMovieCollection(MovieCollection movieCollection) {
        if (movieCollection == null) return;
        movieCollections.add(movieCollection);
    }

    /**
     * @see Object.toString()
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", movieCollections=" + movieCollections +
                '}';
    }

    /**
     * @see Object.equals()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;
    }

    /**
     * @see Object.hashCode()
     */
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
