package org.examples.pbk.pmoviecollection.service;

import org.examples.pbk.pmoviecollection.model.Genre;
import org.examples.pbk.pmoviecollection.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vitalik on 03.08.2017.
 */
@Service("genreService")
@Transactional
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Genre findById(Long id) {
        return genreRepository.findOne(id);
    }

    @Override
    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }

    @Override
    public void saveGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void updateGenre(Genre genre) {
        saveGenre(genre);
    }

    @Override
    public void deleteGenreById(Long id) {
        genreRepository.delete(id);
    }

    @Override
    public void deleteAllGenres() {
        genreRepository.deleteAll();
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public boolean isGenreExist(Genre genre) {
        return findByName(genre.getName()) != null;
    }
}
