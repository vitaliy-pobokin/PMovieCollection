package org.examples.pbk.pmoviecollection.controller;

import org.examples.pbk.pmoviecollection.model.MovieCollection;
import org.examples.pbk.pmoviecollection.model.User;
import org.examples.pbk.pmoviecollection.repository.MovieCollectionRepository;
import org.examples.pbk.pmoviecollection.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Vitalik on 01.08.2017.
 */
@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    @Transactional
    public User getUserInfo(@PathVariable String userId) {

        User user = userRepository.findOne(Long.parseLong(userId));

        return user;
    }

    @RequestMapping(value = "/user/{userId}/collection/", method = RequestMethod.GET)
    public List<MovieCollection> getUserCollections(@PathVariable String userId) {

        User user = userRepository.findOne(Long.parseLong(userId));
        List<MovieCollection> collections = user.getMovieCollections();

        return collections;
    }

}
