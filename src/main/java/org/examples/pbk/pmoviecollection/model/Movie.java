package org.examples.pbk.pmoviecollection.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 *  This immutable datatype represents a movie.
 */
@Entity
@Table(name = "movie")
public class Movie implements Serializable {

    private long id;
    private String title;
    private String description;
    private Calendar releaseDate;
    private double rating;
    private Set<Genre> genres;
    private String posterPath;
    private List<Review> reviews;
    /* Rep invariant:
     *    title.length > 0
     *    _director.length > 0
     *    all characters in _director are drawn from {A..Z, a..z, -}
     *    releaseDate must be after 18/03/1895
     *    rating [0 .. 10]
     */

    public Movie() {
    }

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
    public Movie(long id, String title, String description, Calendar releaseDate, double rating, Set<Genre> genres, String posterPath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = (Calendar) releaseDate.clone();
        this.rating = rating;
        this.genres = new HashSet<>(genres);
        this.posterPath = posterPath;
        this.reviews = new ArrayList<>();
    }

    public Movie(long id, String title, String description, Calendar releaseDate, double rating, Set<Genre> genres, String posterPath, List<Review> reviews) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.genres = genres;
        this.posterPath = posterPath;
        this.reviews = reviews;
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "release_date", nullable = false)
    public Calendar getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Calendar releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Column(name = "rating", nullable = false)
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @ManyToMany
    @JoinTable(name = "movie_genre",
        joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @Column(name = "poster_path")
    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    //one to many
    @OneToMany/*(*//*cascade = CascadeType.ALL, *//*orphanRemoval = true)
    @JoinColumn(table = "review", name = "id")*/
    @JoinTable(name = "movie_review",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "review_id", referencedColumnName = "id"))
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }


    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                ", genres=" + genres +
                ", posterPath='" + posterPath + '\'' +
                ", reviews=" + reviews +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return id == movie.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}
