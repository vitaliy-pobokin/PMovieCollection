package org.examples.pbk.pmoviecollection.converter.themoviedb;

import org.examples.pbk.pmoviecollection.DiscoverMoviesJsonResponse;
import org.examples.pbk.pmoviecollection.model.Movie;
import org.examples.pbk.pmoviecollection.themoviedb.DiscoverMovieResponse;
import org.examples.pbk.pmoviecollection.themoviedb.MovieResponseShort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitalik on 26.07.2017.
 */
public class DiscoverMoviesJsonResponseConverter {

    public static DiscoverMoviesJsonResponse convert(DiscoverMovieResponse response) {
        int page = response.getPage();
        List<Movie> results = new ArrayList<>();
        for (MovieResponseShort movie : response.getResults()) {
            results.add(MovieResponseShortToMovieConverter.convert(movie));
        }
        int totalPages = response.getTotalPages();
        int totalResults = response.getTotalResults();

        return new DiscoverMoviesJsonResponse(page, results, totalPages, totalResults);
    }
}
