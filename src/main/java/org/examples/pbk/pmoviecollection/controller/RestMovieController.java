package org.examples.pbk.pmoviecollection.controller;

import org.examples.pbk.pmoviecollection.model.Movie;
import org.examples.pbk.pmoviecollection.service.MovieService;
import org.examples.pbk.pmoviecollection.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


/**
 * Created by pbkvi on 26.07.2017.
 */
@RestController
@RequestMapping("/api")
public class RestMovieController {

    public static final Logger logger = LoggerFactory.getLogger(RestMovieController.class);

    @Autowired
    private MovieService movieService;

    // -------------------Retrieve All Movies--------------------------------------------

    @RequestMapping(value = "/movie/", method = RequestMethod.GET)
    public ResponseEntity<List<Movie>> listAllMovies() {
        List<Movie> movies = movieService.findAllMovies();
        if (movies.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
    }

    // -------------------Retrieve Single Movie------------------------------------------

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMovie(@PathVariable("id") long id) {
        logger.info("Fetching Movie with id {}", id);
        Movie movie = movieService.findById(id);
        if (movie == null) {
            logger.error("Movie with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Movie with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }

    // -------------------Create a Movie-------------------------------------------------

    @RequestMapping(value = "/movie/", method = RequestMethod.POST)
    public ResponseEntity<?> createMovie(@RequestBody Movie movie, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Movie : {}", movie);

        if (movieService.isMovieExist(movie)) {
            logger.error("Unable to create. A movie with title {} already exists", movie.getTitle());
            return new ResponseEntity(new CustomErrorType("Unable to create. A movie with title " +
                    movie.getTitle() + " already exist"), HttpStatus.CONFLICT);
        }
        movieService.saveMovie(movie);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/movie/{id}").buildAndExpand(movie.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Movie -----------------------------------------------

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMovie(@PathVariable("id") long id, @RequestBody Movie movie) {
        logger.info("Updating Movie with id {}", id);

        Movie currentMovie = movieService.findById(id);

        if (currentMovie == null) {
            logger.error("Unable to update. Movie with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to update. Movie with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentMovie.setTitle(movie.getTitle());
        currentMovie.setDescription(movie.getDescription());
        currentMovie.setRating(movie.getRating());
        currentMovie.setGenres(movie.getGenres());
        currentMovie.setReleaseDate(movie.getReleaseDate());
        currentMovie.setPosterPath(movie.getPosterPath());
        currentMovie.setReviews(movie.getReviews());

        movieService.updateMovie(currentMovie);
        return new ResponseEntity<Movie>(currentMovie, HttpStatus.OK);
    }

    // ------------------- Delete a Movie------------------------------------------------

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMovie(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Movie with id {}", id);

        Movie movie = movieService.findById(id);
        if (movie == null) {
            logger.error("Unable to delete. Movie with id {} not found", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Movie with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        movieService.deleteMovieById(id);
        return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Movies---------------------------------------------

    @RequestMapping(value = "/movie/", method = RequestMethod.DELETE)
    public ResponseEntity<Movie> deleteAllMovies() {
        logger.info("Deleting all Movies");

        movieService.deleteAllMovies();
        return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
    }

    /*@RequestMapping(value = "/movie/{movieId}", method = RequestMethod.GET)
    @Transactional
    public Movie getMovieInfo(@PathVariable Long movieId,
                               @RequestParam(value="language", required = false, defaultValue = "ru-RU") String language) {

        // if there is no such movie in a database:
        Movie movie = movieRepository.findOne(movieId);
        if (movie == null) {
            String url = String.format("https://api.themoviedb.org/3/movie/%s?api_key=%s&language=%s", movieId, AppConfig.API_KEY, language);

            System.out.println(url);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpEntity = new HttpEntity<>(headers);

            ResponseEntity<MovieResponseFull> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, MovieResponseFull.class);

            MovieResponseFull response = responseEntity.getBody();

            Movie result = MovieResponseFullToMovieConverter.convert(response);

            genreRepository.save(result.getGenres());

            movieRepository.save(result);

            return result;
        }

        return movie;
    }*/
}
