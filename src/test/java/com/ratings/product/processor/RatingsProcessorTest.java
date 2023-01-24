package com.ratings.product.processor;

import com.ratings.product.models.Metrics;
import com.ratings.product.reader.RatingsReader;
import com.ratings.product.writer.CaptorMetricsWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.Collections;

@Testable
class RatingsProcessorTest {

    @Nested
    @SpringBootTest
    @ActiveProfiles(profiles = {"csv.reader", "captor.writer"})
    @TestPropertySource(properties = "ratings.path=src/test/resources/ratings-0_0.csv")
    public class Test0_0 {
        @Autowired
        private RatingsProcessor ratingsProcessor;
        @Autowired
        private RatingsReader ratingsReader;
        @Autowired
        private CaptorMetricsWriter metricsWriter;

        @Test
        public void test() {
            ratingsProcessor.process(ratingsReader, metricsWriter);
            Metrics actualMetrics = metricsWriter.getMetrics();
            Metrics expectedMetrics = getExpectedMetrics();
            Assertions.assertEquals(expectedMetrics, actualMetrics);
        }

        private Metrics getExpectedMetrics() {
            Metrics expectedMetrics = Metrics.builder()
                    .validLines(0)
                    .invalidLines(0)
                    .bestRatedProducts(Collections.emptyList())
                    .worstRatedProducts(Collections.emptyList())
                    .build();
            return expectedMetrics;
        }

    }

    @Nested
    @SpringBootTest
    @ActiveProfiles(profiles = {"csv.reader", "captor.writer"})
    @TestPropertySource(properties = "ratings.path=src/test/resources/ratings-1_1.csv")
    public class Test1_1 {
        @Autowired
        private RatingsProcessor ratingsProcessor;
        @Autowired
        private RatingsReader ratingsReader;
        @Autowired
        private CaptorMetricsWriter metricsWriter;

        @Test
        public void test() {
            ratingsProcessor.process(ratingsReader, metricsWriter);
            Metrics actualMetrics = metricsWriter.getMetrics();
            Metrics expectedMetrics = getExpectedMetrics();
            Assertions.assertEquals(expectedMetrics, actualMetrics);
        }

        private Metrics getExpectedMetrics() {
            Metrics expectedMetrics = Metrics.builder()
                    .validLines(1)
                    .invalidLines(1)
                    .bestRatedProducts(Arrays.asList("p-01"))
                    .worstRatedProducts(Arrays.asList("p-01"))
                    .mostRatedProduct("p-01")
                    .leastRatedProduct("p-01")
                    .build();
            return expectedMetrics;
        }

    }

    @Nested
    @SpringBootTest
    @ActiveProfiles(profiles = {"csv.reader", "captor.writer"})
    @TestPropertySource(properties = "ratings.path=src/test/resources/ratings-3_3.csv")
    public class Test3_3 {
        @Autowired
        private RatingsProcessor ratingsProcessor;
        @Autowired
        private RatingsReader ratingsReader;
        @Autowired
        private CaptorMetricsWriter metricsWriter;

        @Test
        public void test() {
            ratingsProcessor.process(ratingsReader, metricsWriter);
            Metrics actualMetrics = metricsWriter.getMetrics();
            Metrics expectedMetrics = getExpectedMetrics();
            Assertions.assertEquals(expectedMetrics, actualMetrics);
        }

        private Metrics getExpectedMetrics() {
            Metrics expectedMetrics = Metrics.builder()
                    .validLines(3)
                    .invalidLines(3)
                    .bestRatedProducts(Arrays.asList("p-01", "p-02"))
                    .worstRatedProducts(Arrays.asList("p-02", "p-01"))
                    .mostRatedProduct("p-02")
                    .leastRatedProduct("p-01")
                    .build();
            return expectedMetrics;
        }
    }

    @Nested
    @SpringBootTest
    @ActiveProfiles(profiles = {"csv.reader", "captor.writer"})
    @TestPropertySource(properties = "ratings.path=src/test/resources/ratings-8_5.csv")
    public class Test8_0 {
        @Autowired
        private RatingsProcessor ratingsProcessor;
        @Autowired
        private RatingsReader ratingsReader;
        @Autowired
        private CaptorMetricsWriter metricsWriter;

        @Test
        public void test() {
            ratingsProcessor.process(ratingsReader, metricsWriter);
            Metrics actualMetrics = metricsWriter.getMetrics();
            Metrics expectedMetrics = getExpectedMetrics();
            Assertions.assertEquals(expectedMetrics, actualMetrics);
        }

        private Metrics getExpectedMetrics() {
            Metrics expectedMetrics = Metrics.builder()
                    .validLines(8)
                    .invalidLines(5)
                    .bestRatedProducts(Arrays.asList("p-02", "p-01", "p-03"))
                    .worstRatedProducts(Arrays.asList("p-04", "p-03", "p-01"))
                    .mostRatedProduct("p-02")
                    .leastRatedProduct("p-03")
                    .build();
            return expectedMetrics;
        }

    }

    @Nested
    @SpringBootTest
    @ActiveProfiles(profiles = {"csv.reader", "captor.writer"})
    @TestPropertySource(properties = "ratings.path=src/test/resources/ratings-15_5.csv")
    public class Test15_5 {
        @Autowired
        private RatingsProcessor ratingsProcessor;
        @Autowired
        private RatingsReader ratingsReader;
        @Autowired
        private CaptorMetricsWriter metricsWriter;

        @Test
        public void test() {
            ratingsProcessor.process(ratingsReader, metricsWriter);
            Metrics actualMetrics = metricsWriter.getMetrics();
            Metrics expectedMetrics = getExpectedMetrics();
            Assertions.assertEquals(expectedMetrics, actualMetrics);
        }

        private Metrics getExpectedMetrics() {
            Metrics expectedMetrics = Metrics.builder()
                    .validLines(15)
                    .invalidLines(5)
                    .bestRatedProducts(Arrays.asList("p-05", "p-04", "p-03"))
                    .worstRatedProducts(Arrays.asList("p-01", "p-02", "p-03"))
                    .mostRatedProduct("p-05")
                    .leastRatedProduct("p-01")
                    .build();
            return expectedMetrics;
        }

    }


}