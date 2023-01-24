package com.ratings.product.converters;

import com.google.common.collect.MinMaxPriorityQueue;
import com.ratings.product.comparators.RatingComparator;
import com.ratings.product.models.Metrics;
import com.ratings.product.models.RatingStats;
import com.ratings.product.processor.RatingsAccumulator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Component
@RequiredArgsConstructor
public class MetricsConverter {

    private final int maxRatedProducts;
    private final RatingComparator bestRatingComparator;
    private final RatingComparator worstRatingComparator;


    public Metrics convert(RatingsAccumulator ratingsAccumulator) {
        return Metrics
                .builder()
                .validLines(ratingsAccumulator.getValidObjects())
                .invalidLines(ratingsAccumulator.getInvalidObjects())
                .bestRatedProducts(getBestRatedProducts(ratingsAccumulator))
                .worstRatedProducts(getWorstRatedProducts(ratingsAccumulator))
                .mostRatedProduct(getMostRatedProduct(ratingsAccumulator))
                .leastRatedProduct(getLeastRatedProduct(ratingsAccumulator))
                .build();
    }

    private List<String> getBestRatedProducts(RatingsAccumulator ratingsAccumulator) {
        MinMaxPriorityQueue<RatingStats> bestRatedProducts = getPriorityQueueFor(bestRatingComparator, maxRatedProducts);
        ratingsAccumulator.getRatingsStats().forEach(bestRatedProducts::add);

        List<RatingStats> ratingStats = new LinkedList<>();

        while (!bestRatedProducts.isEmpty()) {
            ratingStats.add(bestRatedProducts.pollFirst());
        }

        return ratingStats
                .stream()
                .map(RatingStats::getProduct)
                .collect(Collectors.toList());
    }

    private List<String> getWorstRatedProducts(RatingsAccumulator ratingsAccumulator) {
        MinMaxPriorityQueue<RatingStats> worstRatedProducts = getPriorityQueueFor(worstRatingComparator, maxRatedProducts);
        ratingsAccumulator.getRatingsStats().forEach(worstRatedProducts::add);

        List<RatingStats> ratingStats = new LinkedList<>();

        while (!worstRatedProducts.isEmpty()) {
            ratingStats.add(worstRatedProducts.pollFirst());
        }

        return ratingStats
                .stream()
                .map(RatingStats::getProduct)
                .collect(Collectors.toList());
    }

    private String getMostRatedProduct(RatingsAccumulator ratingsAccumulator) {
        return ratingsAccumulator
                .getRatingsStats()
                .parallelStream()
                .max(Comparator.comparingLong(RatingStats::getRatingsCount))
                .orElse(new RatingStats(null))
                .getProduct();
    }

    private String getLeastRatedProduct(RatingsAccumulator ratingsAccumulator) {
        return ratingsAccumulator
                .getRatingsStats()
                .parallelStream()
                .max((r1, r2) -> -Long.compare(r1.getRatingsCount(), r2.getRatingsCount()))
                .orElse(new RatingStats(null))
                .getProduct();
    }

    private MinMaxPriorityQueue<RatingStats> getPriorityQueueFor(RatingComparator ratingComparator, int maxElements) {
        return MinMaxPriorityQueue.orderedBy(ratingComparator).maximumSize(maxElements).create();
    }

}


