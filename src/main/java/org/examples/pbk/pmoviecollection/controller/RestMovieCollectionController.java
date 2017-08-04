package org.examples.pbk.pmoviecollection.controller;

import org.examples.pbk.pmoviecollection.model.MovieCollection;
import org.examples.pbk.pmoviecollection.service.MovieCollectionService;
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
 * Created by Vitalik on 04.08.2017.
 */
@RestController
@RequestMapping("/api")
public class RestMovieCollectionController {

    public static final Logger logger = LoggerFactory.getLogger(RestMovieCollectionController.class);

    @Autowired
    private MovieCollectionService movieCollectionService;

    // -------------------Retrieve All MovieCollections----------------------------------

    @RequestMapping(value = "/collection/", method = RequestMethod.GET)
    public ResponseEntity<List<MovieCollection>> listAllCollections() {
        List<MovieCollection> collections = movieCollectionService.findAllMovieCollections();
        if (collections.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<MovieCollection>>(collections, HttpStatus.OK);
    }

    // -------------------Retrieve Single MovieCollection--------------------------------

    @RequestMapping(value = "/collection/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCollection(@PathVariable("id") long id) {
        logger.info("Fetching MovieCollection with id {}", id);
        MovieCollection collection = movieCollectionService.findById(id);
        if (collection == null) {
            logger.error("MovieCollection with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("MovieCollection with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MovieCollection>(collection, HttpStatus.OK);
    }

    // -------------------Create a MovieCollection---------------------------------------

    @RequestMapping(value = "/collection/", method = RequestMethod.POST)
    public ResponseEntity<?> createCollection(@RequestBody MovieCollection collection, UriComponentsBuilder ucBuilder) {
        logger.info("Creating MovieCollection : {}", collection);

        if (movieCollectionService.isMovieCollectionExist(collection)) {
            logger.error("Unable to create. A collection with id {} already exists", collection.getId());
            return new ResponseEntity(new CustomErrorType("Unable to create. A collection with id " +
                    collection.getId() + " already exist"), HttpStatus.CONFLICT);
        }
        movieCollectionService.saveMovieCollection(collection);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/collection/{id}").buildAndExpand(collection.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a MovieCollection -------------------------------------

    @RequestMapping(value = "/collection/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCollection(@PathVariable("id") long id, @RequestBody MovieCollection collection) {
        logger.info("Updating MovieCollection with id {}", id);

        MovieCollection currentMovieCollection = movieCollectionService.findById(id);

        if (currentMovieCollection == null) {
            logger.error("Unable to update. MovieCollection with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to update. MovieCollection with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentMovieCollection.setCollection(collection.getCollection());

        movieCollectionService.updateMovieCollection(currentMovieCollection);
        return new ResponseEntity<MovieCollection>(currentMovieCollection, HttpStatus.OK);
    }

    // ------------------- Delete a MovieCollection--------------------------------------

    @RequestMapping(value = "/collection/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCollection(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting MovieCollection with id {}", id);

        MovieCollection collection = movieCollectionService.findById(id);
        if (collection == null) {
            logger.error("Unable to delete. MovieCollection with id {} not found", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. MovieCollection with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        movieCollectionService.deleteMovieCollectionById(id);
        return new ResponseEntity<MovieCollection>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All MovieCollections-----------------------------------

    @RequestMapping(value = "/collection/", method = RequestMethod.DELETE)
    public ResponseEntity<MovieCollection> deleteAllCollections() {
        logger.info("Deleting all MovieCollections");

        movieCollectionService.deleteAllMovieCollections();
        return new ResponseEntity<MovieCollection>(HttpStatus.NO_CONTENT);
    }
}
