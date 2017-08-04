package org.examples.pbk.pmoviecollection.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Vitalik on 26.07.2017.
 */
@Entity(name = "Review")
@Table(name = "review")
public class Review implements Serializable {
    private long id;
    private String reviewText;

    public Review() {}

    public Review(String reviewText) {
        this.reviewText = reviewText;
    }

    public Review(long id, String reviewText) {
        this.id = id;
        this.reviewText = reviewText;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "review_text", nullable = false)
    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
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
