package org.examples.pbk.pmoviecollection;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.examples.pbk.pmoviecollection.model.Movie;

import java.util.List;

/**
 * Created by Vitalik on 26.07.2017.
 */
public class DiscoverMoviesJsonResponse {
    @JsonProperty(value = "page")
    private int page;

    @JsonProperty(value = "results")
    private List<Movie> results;

    @JsonProperty(value = "total_pages")
    private int totalPages;

    @JsonProperty(value = "total_results")
    private int totalResults;

    public DiscoverMoviesJsonResponse() {}

    public DiscoverMoviesJsonResponse(int page, List<Movie> results, int totalPages, int totalResults) {
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
