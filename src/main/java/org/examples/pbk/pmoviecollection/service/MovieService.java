package org.examples.pbk.pmoviecollection.service;

import org.examples.pbk.pmoviecollection.model.Movie;

import java.util.List;

/**
 * Created by Vitalik on 03.08.2017.
 */
public interface MovieService {

    Movie findById(Long id);

    Movie findByTitle(String title);

    void saveMovie(Movie movie);

    void updateMovie(Movie movie);

    void deleteMovieById(Long id);

    void deleteAllMovies();

    List<Movie> findAllMovies();

    boolean isMovieExist(Movie movie);

}
