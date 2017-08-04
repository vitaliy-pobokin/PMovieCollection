package org.examples.pbk.pmoviecollection.service;

import org.examples.pbk.pmoviecollection.model.Review;
import org.examples.pbk.pmoviecollection.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vitalik on 03.08.2017.
 */
@Service("reviewService")
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review findById(Long id) {
        return reviewRepository.findOne(id);
    }

    @Override
    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public void updateReview(Review review) {
        saveReview(review);
    }

    @Override
    public void deleteReviewById(Long id) {
        reviewRepository.delete(id);
    }

    @Override
    public void deleteAllReviews() {
        reviewRepository.deleteAll();
    }

    @Override
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public boolean isReviewExist(Review review) {
        return findById(review.getId()) != null;
    }
}
