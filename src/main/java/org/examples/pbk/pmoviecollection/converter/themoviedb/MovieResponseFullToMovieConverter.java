package org.examples.pbk.pmoviecollection.converter.themoviedb;

import org.examples.pbk.pmoviecollection.model.Genre;
import org.examples.pbk.pmoviecollection.model.Movie;
import org.examples.pbk.pmoviecollection.themoviedb.MovieResponseFull;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * Created by Vitalik on 26.07.2017.
 */
public class MovieResponseFullToMovieConverter {
    public static Movie convert(MovieResponseFull movieResponse) {
        long id = movieResponse.getId();
        String title = movieResponse.getTitle();
        String description = movieResponse.getDescription();

        // TODO: make date conversion
        Calendar releaseDate = parseDate(movieResponse.getReleaseDate());

        double rating = movieResponse.getRating();
        Set<Genre> genres = movieResponse.getGenres();

        return new Movie(id, title, description, releaseDate, rating, genres, "");
    }

    private static Calendar parseDate(String date) {
        String[] data = date.split("-");
        int year = Integer.parseInt(data[0]);
        int month = Integer.parseInt(data[1]);
        int day = Integer.parseInt(data[2]);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);

        return cal;
    }
}
