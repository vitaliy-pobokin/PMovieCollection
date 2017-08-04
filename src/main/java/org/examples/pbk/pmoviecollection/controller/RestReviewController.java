package org.examples.pbk.pmoviecollection.controller;

import org.examples.pbk.pmoviecollection.model.Review;
import org.examples.pbk.pmoviecollection.service.ReviewService;
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
 * Created by Vitalik on 27.07.2017.
 */
@RestController
@RequestMapping("/api")
public class RestReviewController {

    public static final Logger logger = LoggerFactory.getLogger(RestReviewController.class);

    @Autowired
    private ReviewService reviewService;

    // -------------------Retrieve All Reviews-------------------------------------------

    @RequestMapping(value = "/review/", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> listAllReviews() {
        List<Review> reviews = reviewService.findAllReviews();
        if (reviews.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
    }

    // -------------------Retrieve Single Review-----------------------------------------

    @RequestMapping(value = "/review/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getReview(@PathVariable("id") long id) {
        logger.info("Fetching Review with id {}", id);
        Review review = reviewService.findById(id);
        if (review == null) {
            logger.error("Review with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Review with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Review>(review, HttpStatus.OK);
    }

    // -------------------Create a Review------------------------------------------------

    @RequestMapping(value = "/review/", method = RequestMethod.POST)
    public ResponseEntity<?> createReview(@RequestBody Review review, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Review : {}", review);

        if (reviewService.isReviewExist(review)) {
            logger.error("Unable to create. A review with id {} already exists", review.getId());
            return new ResponseEntity(new CustomErrorType("Unable to create. A review with id " +
                    review.getId() + " already exist"), HttpStatus.CONFLICT);
        }
        reviewService.saveReview(review);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/review/{id}").buildAndExpand(review.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Review ----------------------------------------------

    @RequestMapping(value = "/review/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateReview(@PathVariable("id") long id, @RequestBody Review review) {
        logger.info("Updating Review with id {}", id);

        Review currentReview = reviewService.findById(id);

        if (currentReview == null) {
            logger.error("Unable to update. Review with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to update. Review with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentReview.setReviewText(review.getReviewText());

        reviewService.updateReview(currentReview);
        return new ResponseEntity<Review>(currentReview, HttpStatus.OK);
    }

    // ------------------- Delete a Review-----------------------------------------------

    @RequestMapping(value = "/review/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteReview(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Review with id {}", id);

        Review review = reviewService.findById(id);
        if (review == null) {
            logger.error("Unable to delete. Review with id {} not found", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Review with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        reviewService.deleteReviewById(id);
        return new ResponseEntity<Review>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Reviews--------------------------------------------

    @RequestMapping(value = "/review/", method = RequestMethod.DELETE)
    public ResponseEntity<Review> deleteAllReviews() {
        logger.info("Deleting all Reviews");

        reviewService.deleteAllReviews();
        return new ResponseEntity<Review>(HttpStatus.NO_CONTENT);
    }
}
