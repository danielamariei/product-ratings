package com.ratings.product.processor;

import com.ratings.product.models.Rating;
import com.ratings.product.models.RatingStats;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class RatingsAccumulator {

    private final Map<String, RatingStats> ratings = new ConcurrentHashMap<>();
    private final AtomicLong validObjects = new AtomicLong();
    private final AtomicLong invalidObjects = new AtomicLong();

    public void accumulate(Optional<Rating> rating) {
        accumulateObjectCount(rating);
        accumulateRatings(rating);
    }

    private void accumulateObjectCount(Optional<Rating> rating) {
        if (rating.isPresent()) {
            validObjects.getAndIncrement();
        } else {
            invalidObjects.getAndIncrement();
        }
    }

    private void accumulateRatings(Optional<Rating> rating) {
        rating.ifPresent(this::accumulateRatings);
    }

    private void accumulateRatings(Rating rating) {
        RatingStats ratingStats = ratings.computeIfAbsent(rating.getProductId(), (key) -> new RatingStats(rating.getProductId()));
        ratingStats.increaseSumBy(rating.getRating());
        ratingStats.increaseCountBy(1);
    }

    public long getValidObjects() {
        return validObjects.get();
    }

    public long getInvalidObjects() {
        return invalidObjects.get();
    }

    public Collection<RatingStats> getRatingsStats() {
        return ratings.values();
    }
}
