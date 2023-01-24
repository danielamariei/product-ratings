package com.ratings.product.comparators;

import com.ratings.product.models.RatingStats;

import java.util.Comparator;

public interface RatingComparator extends Comparator<RatingStats> {
    @Override
    int compare(RatingStats o1, RatingStats o2);
}
