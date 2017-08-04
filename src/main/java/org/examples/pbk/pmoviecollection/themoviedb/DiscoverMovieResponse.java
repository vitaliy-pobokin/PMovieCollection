package org.examples.pbk.pmoviecollection.themoviedb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Vitalik on 26.07.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscoverMovieResponse {

    @JsonProperty(value = "page")
    private int page;

    @JsonProperty(value = "total_pages")
    private int totalPages;

    @JsonProperty(value = "total_results")
    private int totalResults;

    @JsonProperty(value = "results")
    private List<MovieResponseShort> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<MovieResponseShort> getResults() {
        return results;
    }

    public void setResults(List<MovieResponseShort> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "DiscoverMovieResponse{" +
                "page=" + page +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                ", results=" + results +
                '}';
    }
}
