package com.ratings.product.reader;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.ratings.product.models.Rating;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;


@Slf4j
@Component
@Profile("csv.reader")
@RequiredArgsConstructor
public class CsvRatingsReader implements RatingsReader {


    private final String ratingsPath;
    private final Validator validator;

    @SneakyThrows
    @Override
    public Stream<Optional<Rating>> read() {
        return getReader()
                .lines()
                .skip(1)
                .map(this::toRating)
                .map(this::validate);
    }

    private BufferedReader getReader() throws FileNotFoundException {
        return new BufferedReader(new FileReader(ratingsPath));
    }

    private Optional<Rating> toRating(String ratingLine) {
        ObjectReader objectReader = getCsvObjectReader();

        try {
            return Optional.of(objectReader.readValue(ratingLine));
        } catch (Exception e) {
            log.info("Encountered an error while trying to parse a rating line: {}", e.getMessage());
        }

        return Optional.empty();
    }

    private Optional<Rating> validate(Optional<Rating> rating) {
        if (rating.isEmpty()) {
            return rating;
        }

        Set<ConstraintViolation<Rating>> validate = validator.validate(rating.get());
        return validate.isEmpty() ? rating : Optional.empty();
    }

    private ObjectReader getCsvObjectReader() {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema =
                CsvSchema.builder()
                        .setColumnSeparator(',')
                        .addColumn("Buyer Id")
                        .addColumn("Shop Id")
                        .addColumn("Product Id")
                        .addColumn("Rating")
                        .build();
        return csvMapper.readerFor(Rating.class).with(schema);
    }

}
