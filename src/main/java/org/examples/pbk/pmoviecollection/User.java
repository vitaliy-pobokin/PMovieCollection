package org.examples.pbk.pmoviecollection;

import java.util.ArrayList;
import java.util.List;

/**
 *  User represents some person identified by unique id or unique username.
 */
public class User {

    private final long _id;
    private final String _username;
    private final String _password;
    private final List<MovieCollection> _movieCollections;
    /* Rep invariant:
     *    _username.length > 2
     *    _password.length > 5
     */

    /**
     * Make a user with a known unique id and username.
     *
     * @param id
     * @param username
     * @param password
     */
    public User(long id, String username, String password) {
        this._id = id;
        this._username = username;
        this._password = password;
        this._movieCollections = new ArrayList<MovieCollection>();
    }

    /**
     * @return unique identifier of this user
     */
    public long getId() {
        return _id;
    }

    /**
     * @return unique username of this user
     */
    public String getUsername() {
        return _username;
    }

    /**
     * @return list of MovieCollections of this user
     */
    public List<MovieCollection> getMovieCollections() {
        List<MovieCollection> collectionList = new ArrayList<MovieCollection>();
        collectionList.addAll(_movieCollections);
        return collectionList;
    }

    /**
     * Add MovieCollection to collections list of this user
     *
     * @param movieCollection Not null MovieCollection as defined in MovieCollection class.
     */
    public void addMovieCollection(MovieCollection movieCollection) {
        if (movieCollection == null) return;
        _movieCollections.add(movieCollection);
    }

    /**
     * @see Object.toString()
     */
    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", _username='" + _username + '\'' +
                ", _movieCollections=" + _movieCollections +
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

        return _id == user._id;
    }

    /**
     * @see Object.hashCode()
     */
    @Override
    public int hashCode() {
        return (int) (_id ^ (_id >>> 32));
    }
}
