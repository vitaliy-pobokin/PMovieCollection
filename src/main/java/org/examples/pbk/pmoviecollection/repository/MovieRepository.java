package org.examples.pbk.pmoviecollection.repository;

import org.examples.pbk.pmoviecollection.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vitalik on 01.08.2017.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findByTitle(String title);

}
