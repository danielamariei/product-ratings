package com.ratings.product.reader;

import com.ratings.product.models.Rating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.annotation.Testable;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Testable
class CsvRatingsReaderTest {

    private static Validator validator;

    @BeforeAll
    public static void beforeAll() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    private static List<Arguments> testCases() {
        return Arrays.asList(
                Arguments.of(new CsvRatingsReader("src/test/resources/ratings-0_0.csv", validator), 0, 0),
                Arguments.of(new CsvRatingsReader("src/test/resources/ratings-1_1.csv", validator), 1, 1),
                Arguments.of(new CsvRatingsReader("src/test/resources/ratings-3_3.csv", validator), 3, 3),
                Arguments.of(new CsvRatingsReader("src/test/resources/ratings-4_0.csv", validator), 4, 0),
                Arguments.of(new CsvRatingsReader("src/test/resources/ratings-8_5.csv", validator), 8, 5));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void read(RatingsReader ratingsReader, int expectedValidObjects, int expectedInvalidObjects) {
        List<Optional<Rating>> ratings = ratingsReader.read().collect(Collectors.toList());

        long actualValidObjects = ratings.stream().filter(Optional::isPresent).count();
        Assertions.assertEquals(expectedValidObjects, actualValidObjects);


        long actualInvalidObjects = ratings.stream().filter(Optional::isEmpty).count();
        Assertions.assertEquals(expectedInvalidObjects, actualInvalidObjects);
    }
}