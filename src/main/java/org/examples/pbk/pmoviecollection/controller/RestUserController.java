package org.examples.pbk.pmoviecollection.controller;

import org.examples.pbk.pmoviecollection.model.User;
import org.examples.pbk.pmoviecollection.service.UserService;
import org.examples.pbk.pmoviecollection.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Vitalik on 01.08.2017.
 */
@RestController
@RequestMapping("/api")
public class RestUserController {

    public static final Logger logger = LoggerFactory.getLogger(RestUserController.class);

    @Autowired
    UserService userService;

    // -------------------Retrieve All Users---------------------------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    // -------------------Retrieve Single User------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        logger.info("Fetching User with id {}", id);
        User user = userService.findById(id);
        if (user == null) {
            logger.error("User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // -------------------Create a User-------------------------------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User : {}", user);

        if (userService.isUserExist(user)) {
            logger.error("Unable to create. A user with username {} already exists", user.getUsername());
            return new ResponseEntity(new CustomErrorType("Unable to create. A user with username " +
                user.getUsername() + " already exist"), HttpStatus.CONFLICT);
        }
        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a User ------------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        logger.info("Updating User with id {}", id);

        User currentUser = userService.findById(id);

        if (currentUser == null) {
            logger.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to update. User with id " + id + " not found."),
                HttpStatus.NOT_FOUND);
        }

        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());

        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    // ------------------- Delete a User-------------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting User with id {}", id);

        User user = userService.findById(id);
        if (user == null) {
            logger.error("Unable to delete. User with id {} not found", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Users----------------------------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        logger.info("Deleting all Users");

        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    /*@Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieCollectionRepository movieCollectionRepository;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @Transactional
    // should be rewrite later due to security reasons )))
    public User addNewUser(HttpEntity<User> httpEntity) {

        User user = httpEntity.getBody();

        User savedUser = userRepository.save(user);

        return savedUser;
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User getUserInfo(@PathVariable Long userId) {

        User user = userRepository.findOne(userId);

        return user;
    }

    @RequestMapping(value = "/user/{userId}/collection", method = RequestMethod.GET)
    public List<MovieCollection> getUserCollections(@PathVariable Long userId) {

        User user = userRepository.findOne(userId);
        List<MovieCollection> collections = user.getMovieCollections();

        return collections;
    }

    @RequestMapping(value = "/user/{userId}/collection", method = RequestMethod.POST)
    public Iterable<MovieCollection> addMovieCollection(@PathVariable Long userId, HttpEntity<MovieCollection> httpEntity) {

        MovieCollection movieCollection = httpEntity.getBody();

        //MovieCollection savedCollection = movieCollectionRepository.save(movieCollection);
        User user = userRepository.findOne(userId);
        user.addMovieCollection(movieCollection);
        User savedUser = userRepository.save(user);

        //MovieCollection savedCollection = movieCollectionRepository.findOne(movieCollection);

        //System.out.println(movieCollection);

        return movieCollectionRepository.findAll();
    }*/

}
