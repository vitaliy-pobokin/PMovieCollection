package org.examples.pbk.pmoviecollection.service;

import org.examples.pbk.pmoviecollection.model.MovieCollection;

import java.util.List;

/**
 * Created by Vitalik on 03.08.2017.
 */
public interface MovieCollectionService {

    MovieCollection findById(Long id);

    MovieCollection findByCollectionName(String collectionName);

    void saveMovieCollection(MovieCollection movieCollection);

    void updateMovieCollection(MovieCollection movieCollection);

    void deleteMovieCollectionById(Long id);

    void deleteAllMovieCollections();

    List<MovieCollection> findAllMovieCollections();

    boolean isMovieCollectionExist(MovieCollection movieCollection);
}
