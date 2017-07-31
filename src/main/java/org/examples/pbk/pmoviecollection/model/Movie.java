package org.examples.pbk.pmoviecollection;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *  This immutable datatype represents a movie.
 */
public class Movie {
    @JsonProperty(value = "id")
    private final long _id;
    private String _title;
    private String _description;
    private Calendar _releaseDate;
    private double _rating;
    private List<Genre> _genres;
    private String _posterPath;
    private final List<Review> _reviews;
    /* Rep invariant:
     *    _title.length > 0
     *    _director.length > 0
     *    all characters in _director are drawn from {A..Z, a..z, -}
     *    _releaseDate must be after 18/03/1895
     *    _rating [0 .. 10]
     */

    /**
     *  Make a movie with a known unique id.
     *
     * @param id
     *          unique identifier for the movie taken from some public movie api
     * @param title
     *          movie title. Must be set to non empty String
     * @param description
     *          movie description (optional. Can be empty string).
     * @param releaseDate
     *          date of the first appearance movie on the screens
     *          Date must be after 18/03/1895.
     * @param rating
     *          rating of the movie. Must be between 0 and 10
     */
    public Movie(long id, String title, String description, Calendar releaseDate, double rating, List<Genre> genres, String posterPath) {
        this._id = id;
        this._title = title;
        this._description = description;
        this._releaseDate = (Calendar) releaseDate.clone();
        this._rating = rating;
        this._genres = new ArrayList<>(genres);
        this._posterPath = posterPath;
        this._reviews = new ArrayList<Review>();
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
        return (Calendar) _releaseDate.clone();
    }

    /**
     * @return rating of this movie between 0 and 10
     */
    public double getRating() {
        return _rating;
    }

    public List<Genre> getGenres() {
        return new ArrayList<>(_genres);
    }

    public String getPosterPath() {
        return _posterPath;
    }

    public List<Review> getReviews() {
        return new ArrayList<>(_reviews);
    }

    public void addReview(Review review) {
        _reviews.add(review);
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public void set_releaseDate(Calendar _releaseDate) {
        this._releaseDate = _releaseDate;
    }

    public void set_rating(double _rating) {
        this._rating = _rating;
    }

    public void set_genres(List<Genre> _genres) {
        this._genres = _genres;
    }

    public void set_posterPath(String _posterPath) {
        this._posterPath = _posterPath;
    }

    /**
     * @see Object.toString()
     */
    @Override
    public String toString() {
        return "Movie{" +
                "_id=" + _id +
                ", _title='" + _title + '\'' +
                ", _description='" + _description + '\'' +
                ", _releaseDate=" + _releaseDate +
                ", _rating=" + _rating +
                ", _genres=" + _genres +
                ", _posterPath='" + _posterPath + '\'' +
                ", _reviews=" + _reviews +
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
