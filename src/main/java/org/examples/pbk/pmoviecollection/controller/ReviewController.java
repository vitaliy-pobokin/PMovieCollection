package org.examples.pbk.pmoviecollection.controller;

import org.examples.pbk.pmoviecollection.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Vitalik on 27.07.2017.
 */
@RestController
public class ReviewController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "movie/{movieId}/review", method = RequestMethod.POST, consumes = "application/json")
    public Review saveReview(@PathVariable String movieId, HttpEntity<Review> httpEntity) {

        //RequestEntity<Movie> requestEntity = httpEntity.getBody();

        Review review = httpEntity.getBody();
//        URI url = request.getUrl();
//        System.out.println(url);
//        System.out.println(request.getMethod());
//        System.out.println(request.getHeaders());

        //TODO: update movie in a database. Update

        System.out.println(review);
        return review;
    }
}
