package org.examples.pbk.pmoviecollection.service;

import org.examples.pbk.pmoviecollection.model.Genre;

import java.util.List;

/**
 * Created by Vitalik on 03.08.2017.
 */
public interface GenreService {

    Genre findById(Long id);

    Genre findByName(String name);

    void saveGenre(Genre genre);

    void updateGenre(Genre genre);

    void deleteGenreById(Long id);

    void deleteAllGenres();

    List<Genre> findAllGenres();

    boolean isGenreExist(Genre genre);
}
