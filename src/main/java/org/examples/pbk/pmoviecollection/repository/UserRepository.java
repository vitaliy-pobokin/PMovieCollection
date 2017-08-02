package org.examples.pbk.pmoviecollection.repository;

import org.examples.pbk.pmoviecollection.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pbkvi on 01.08.2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
