package com.ratings.product.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicLong;


@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RatingStats {
    @Getter
    @EqualsAndHashCode.Include
    private final String product;
    private AtomicLong ratingsSum = new AtomicLong();
    private AtomicLong ratingsCount = new AtomicLong();

    public RatingStats(String product) {
        this.product = product;
    }

    public void increaseSumBy(long sum) {
        ratingsSum.getAndAdd(sum);
    }

    public void increaseCountBy(long count) {
        ratingsCount.getAndAdd(count);
    }

    public double getAverageRating() {
        return ((double) getRatingsSum()) / getRatingsCount();
    }

    public long getRatingsSum() {
        return ratingsSum.get();
    }

    public long getRatingsCount() {
        return ratingsCount.get();
    }

}
