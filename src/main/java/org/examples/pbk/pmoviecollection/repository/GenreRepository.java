package org.examples.pbk.pmoviecollection.repository;

import org.examples.pbk.pmoviecollection.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vitalik on 01.08.2017.
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);

}
