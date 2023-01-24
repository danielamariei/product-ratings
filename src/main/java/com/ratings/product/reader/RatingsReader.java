package com.ratings.product.reader;

import com.ratings.product.models.Rating;

import java.util.Optional;
import java.util.stream.Stream;

public interface RatingsReader {
    Stream<Optional<Rating>> read();
}
