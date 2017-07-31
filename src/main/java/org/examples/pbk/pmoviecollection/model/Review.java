package org.examples.pbk.pmoviecollection.model;

/**
 * Created by Vitalik on 26.07.2017.
 */
public class Review {
    private long id;
    private String reviewText;

    public Review() {}

    public Review(long id, String reviewText) {
        this.id = id;
        this.reviewText = reviewText;
    }

    public long getId() {
        return id;
    }

    public String getReviewText() {
        return reviewText;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", reviewText='" + reviewText + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        return id == review.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
