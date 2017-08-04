package org.examples.pbk.pmoviecollection.themoviedb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.examples.pbk.pmoviecollection.model.Genre;

import java.util.List;
import java.util.Set;

/**
 * Created by Vitalik on 26.07.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResponseFull {

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

    @JsonProperty(value = "genres")
    private Set<Genre> genres;

    @JsonProperty(value = "budget")
    private int budget;

    @JsonProperty(value = "revenue")
    private int revenue;

    @JsonProperty(value = "runtime")
    private int runtime;

    public MovieResponseFull(){}

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

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    @Override
    public String toString() {
        return "MovieResponseFull{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", rating=" + rating +
                ", genres=" + genres +
                ", budget=" + budget +
                ", revenue=" + revenue +
                ", runtime=" + runtime +
                '}';
    }
}
