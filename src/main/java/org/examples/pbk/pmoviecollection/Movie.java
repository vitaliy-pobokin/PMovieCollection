package org.examples.pbk.pmoviecollection;

import java.util.Calendar;

/**
 *  This immutable datatype represents a movie.
 */
public class Movie {
    private final long _id;
    private final String _title;
    private final String _director;
    private final String _description;
    private final Calendar _premiereDate;
    private final double _rating;
    /* Rep invariant:
     *    _title.length > 0
     *    _director.length > 0
     *    all characters in _director are drawn from {A..Z, a..z, -}
     *    _premiereDate must be after 18/03/1895
     *    _rating [0 .. 10]
     */

    /**
     *  Make a movie with a known unique id.
     *
     * @param id
     *          unique identifier for the movie taken from some public movie api
     * @param title
     *          movie title. Must be set to non empty String
     * @param director
     *          movie director. Must be set to non empty String
     * @param description
     *          movie description (optional. Can be empty string).
     * @param premiereDate
     *          date of the first appearance movie on the screens
     *          Date must be after 18/03/1895.
     * @param rating
     *          rating of the movie. Must be between 0 and 10
     */
    public Movie(long id, String title, String director, String description, Calendar premiereDate, double rating) {
        this._id = id;
        this._title = title;
        this._director = director;
        this._description = description;
        this._premiereDate = (Calendar) premiereDate.clone();
        this._rating = rating;
    }

    /**
     * @return unique identifier of this movie
     */
    public long getId() {
        return _id;
    }

    /**
     * @return title of this movie. Non-empty string
     */
    public String getTitle() {
        return _title;
    }

    /**
     * @return director of this movie.
     *         A movie director string is a nonempty sequence of letters
     *         (A-Z or a-z), or hyphen ("-").
     */
    public String getDirector() {
        return _director;
    }

    /**
     * @return short description of this movie
     *         Optional. Can be empty string.
     */
    public String getDescription() {
        return _description;
    }

    /**
     * @return date of the first appearance movie on the screens.
     *         Date must be after 18/03/1895.
     */
    public Calendar getPremiereDate() {
        return _premiereDate;
    }

    /**
     * @return rating of this movie between 0 and 10
     */
    public double getRating() {
        return _rating;
    }

    /**
     * @see Object.toString()
     */
    @Override
    public String toString() {
        return "Movie{" +
                "_id=" + _id +
                ", _title='" + _title + '\'' +
                ", _director='" + _director + '\'' +
                ", _description='" + _description + '\'' +
                ", _premiereDate=" + _premiereDate +
                ", _rating=" + _rating +
                '}';
    }

    /**
     * @see Object.equals()
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return _id == movie._id;
    }

    /**
     * @see Object.hashCode()
     */
    @Override
    public int hashCode() {
        return (int) (_id ^ (_id >>> 32));
    }

}
