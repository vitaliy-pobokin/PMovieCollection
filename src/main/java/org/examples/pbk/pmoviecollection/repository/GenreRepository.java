package org.examples.pbk.pmoviecollection.repository;

import org.examples.pbk.pmoviecollection.model.Genre;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Vitalik on 01.08.2017.
 */
public interface GenreRepository extends CrudRepository<Genre, Long> {
}
