package org.examples.pbk.pmoviecollection.converter.themoviedb;

import org.examples.pbk.pmoviecollection.model.Genre;
import org.examples.pbk.pmoviecollection.model.Movie;
import org.examples.pbk.pmoviecollection.themoviedb.MovieResponseFull;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Vitalik on 26.07.2017.
 */
public class MovieResponseFullToMovieConverter {
    public static Movie convert(MovieResponseFull movieResponse) {
        long id = movieResponse.getId();
        String title = movieResponse.getTitle();
        String description = movieResponse.getDescription();

        // TODO: make date conversion
        Calendar releaseDate = Calendar.getInstance();

        double rating = movieResponse.getRating();
        List<Genre> genres = movieResponse.getGenres();

        return new Movie(id, title, description, releaseDate, rating, genres, "");
    }
}
