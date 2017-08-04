package org.examples.pbk.pmoviecollection.repository;

import org.examples.pbk.pmoviecollection.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pbkvi on 01.08.2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
