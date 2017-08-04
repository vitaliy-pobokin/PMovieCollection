package org.examples.pbk.pmoviecollection.service;

import org.examples.pbk.pmoviecollection.model.Movie;
import org.examples.pbk.pmoviecollection.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vitalik on 03.08.2017.
 */
@Service("movieService")
@Transactional
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie findById(Long id) {
        return movieRepository.findOne(id);
    }

    @Override
    public Movie findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void updateMovie(Movie movie) {
        saveMovie(movie);
    }

    @Override
    public void deleteMovieById(Long id) {
        movieRepository.delete(id);
    }

    @Override
    public void deleteAllMovies() {
        movieRepository.deleteAll();
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public boolean isMovieExist(Movie movie) {
        return findByTitle(movie.getTitle()) != null;
    }
}
