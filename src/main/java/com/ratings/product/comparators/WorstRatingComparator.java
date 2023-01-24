package com.ratings.product.comparators;

import com.ratings.product.models.RatingStats;
import org.springframework.stereotype.Component;

@Component
public class WorstRatingComparator implements RatingComparator {
    @Override
    public int compare(RatingStats r1, RatingStats r2) {
        return Double.compare(r1.getAverageRating(), r2.getAverageRating());
    }
}
