package org.examples.pbk.pmoviecollection.service;

import org.examples.pbk.pmoviecollection.model.Review;

import java.util.List;

/**
 * Created by Vitalik on 03.08.2017.
 */
public interface ReviewService {

    Review findById(Long id);

    void saveReview(Review review);

    void updateReview(Review review);

    void deleteReviewById(Long id);

    void deleteAllReviews();

    List<Review> findAllReviews();

    boolean isReviewExist(Review review);
}
