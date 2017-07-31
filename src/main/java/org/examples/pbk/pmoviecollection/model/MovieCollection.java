package org.examples.pbk.pmoviecollection;

import java.util.ArrayList;
import java.util.List;

/**
 *  MovieCollection represents collection of the movies that might be
 *  held by some person.
 *  Or collection of the movies that some person wants to watch.
 */
public class MovieCollection {

    private final long _id;
    private final List<Movie> _collection;
    /* Rep invariant:
     *    _collection.length > 0
     */

    /**
     *  Make a movie collection with a known unique id.
     *
     * @param id
     *          unique identifier for the movie collection
     * @param movies
     *          list of movies to be stored in the collection.
     *          Must contain one element at least.
     */
    public MovieCollection(long id, List<Movie> movies) {
        this._id = id;
        this._collection = new ArrayList<Movie>();
        _collection.addAll(movies);
    }

    /**
     * @return unique identifier of this collection
     */
    public long getId() {
        return _id;
    }

    /**
     * @return list of movies storing in this collection.
     */
    public List<Movie> getCollection() {
        List<Movie> movies = new ArrayList<Movie>();
        movies.addAll(_collection);
        return movies;
    }

    /**
     * @see Object.toString()
     */
    @Override
    public String toString() {
        return "MovieCollection{" +
                "_id=" + _id +
                ", _collection=" + _collection +
                '}';
    }

    /**
     * @see Object.equals()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieCollection that = (MovieCollection) o;

        return _id == that._id;
    }

    /**
     * @see Object.hashCode()
     */
    @Override
    public int hashCode() {
        return (int) (_id ^ (_id >>> 32));
    }
}
