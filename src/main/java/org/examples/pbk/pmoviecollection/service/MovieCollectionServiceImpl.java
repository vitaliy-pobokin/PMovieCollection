package org.examples.pbk.pmoviecollection.service;

import org.examples.pbk.pmoviecollection.model.MovieCollection;
import org.examples.pbk.pmoviecollection.repository.MovieCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vitalik on 03.08.2017.
 */
@Service("movieCollectionService")
@Transactional
public class MovieCollectionServiceImpl implements MovieCollectionService {

    @Autowired
    private MovieCollectionRepository movieCollectionRepository;

    @Override
    public MovieCollection findById(Long id) {
        return movieCollectionRepository.findOne(id);
    }

    @Override
    public MovieCollection findByCollectionName(String collectionName) {
        return movieCollectionRepository.findByCollectionName(collectionName);
    }

    @Override
    public void saveMovieCollection(MovieCollection movieCollection) {
        movieCollectionRepository.save(movieCollection);
    }

    @Override
    public void updateMovieCollection(MovieCollection movieCollection) {
        saveMovieCollection(movieCollection);
    }

    @Override
    public void deleteMovieCollectionById(Long id) {
        movieCollectionRepository.delete(id);
    }

    @Override
    public void deleteAllMovieCollections() {
        movieCollectionRepository.deleteAll();
    }

    @Override
    public List<MovieCollection> findAllMovieCollections() {
        return movieCollectionRepository.findAll();
    }

    @Override
    public boolean isMovieCollectionExist(MovieCollection movieCollection) {
        return findById(movieCollection.getId()) != null;
    }
}
