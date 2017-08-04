package org.examples.pbk.pmoviecollection.themoviedb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

/**
 * Created by Vitalik on 26.07.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResponseShort {

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "overview")
    private String description;

    @JsonProperty(value = "release_date")
    private String releaseDate;

    @JsonProperty(value = "vote_average")
    private double rating;

    @JsonProperty(value = "genre_ids")
    private Set<Long> genreIds;

    public MovieResponseShort() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Set<Long> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(Set<Long> genreIds) {
        this.genreIds = genreIds;
    }

    @Override
    public String toString() {
        return "MovieResponseShort{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", rating=" + rating +
                ", genreIds=" + genreIds +
                '}';
    }
}
