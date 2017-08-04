package org.examples.pbk.pmoviecollection.controller;

import org.examples.pbk.pmoviecollection.model.Genre;
import org.examples.pbk.pmoviecollection.service.GenreService;
import org.examples.pbk.pmoviecollection.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by pbkvi on 03.08.2017.
 */
@RestController
@RequestMapping("/api")
public class RestGenreController {

    public static final Logger logger = LoggerFactory.getLogger(RestGenreController.class);

    @Autowired
    private GenreService genreService;

    // -------------------Retrieve All Genres--------------------------------------------

    @RequestMapping(value = "/genre/", method = RequestMethod.GET)
    public ResponseEntity<List<Genre>> listAllGenres() {
        List<Genre> genres = genreService.findAllGenres();
        if (genres.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Genre>>(genres, HttpStatus.OK);
    }

    // -------------------Retrieve Single Genre------------------------------------------

    @RequestMapping(value = "/genre/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getGenres(@PathVariable("id") long id) {
        logger.info("Fetching Genre with id {}", id);
        Genre genre = genreService.findById(id);
        if (genre == null) {
            logger.error("Genre with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Genre with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Genre>(genre, HttpStatus.OK);
    }

    // -------------------Create a Genre-------------------------------------------------

    @RequestMapping(value = "/genre/", method = RequestMethod.POST)
    public ResponseEntity<?> createGenre(@RequestBody Genre genre, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Genre : {}", genre);

        if (genreService.isGenreExist(genre)) {
            logger.error("Unable to create. A genre with name {} already exists", genre.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A genre with name " +
                    genre.getName() + " already exist"), HttpStatus.CONFLICT);
        }
        genreService.saveGenre(genre);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/genre/{id}").buildAndExpand(genre.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Genre -----------------------------------------------

    @RequestMapping(value = "/genre/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateGenre(@PathVariable("id") long id, @RequestBody Genre genre) {
        logger.info("Updating Genre with id {}", id);

        Genre currentGenre = genreService.findById(id);

        if (currentGenre == null) {
            logger.error("Unable to update. Genre with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to update. Genre with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentGenre.setName(genre.getName());

        genreService.updateGenre(currentGenre);
        return new ResponseEntity<Genre>(currentGenre, HttpStatus.OK);
    }

    // ------------------- Delete a Genre------------------------------------------------

    @RequestMapping(value = "/genre/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGenre(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Genre with id {}", id);

        Genre genre = genreService.findById(id);
        if (genre == null) {
            logger.error("Unable to delete. Genre with id {} not found", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Genre with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        genreService.deleteGenreById(id);
        return new ResponseEntity<Genre>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Genres---------------------------------------------

    @RequestMapping(value = "/genre/", method = RequestMethod.DELETE)
    public ResponseEntity<Genre> deleteAllGenres() {
        logger.info("Deleting all Genres");

        genreService.deleteAllGenres();
        return new ResponseEntity<Genre>(HttpStatus.NO_CONTENT);
    }
}
