package com.ratings.product.processor;

import com.ratings.product.converters.MetricsConverter;
import com.ratings.product.reader.RatingsReader;
import com.ratings.product.writer.MetricsWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RatingsProcessor {

    private final MetricsConverter metricsConverter;

    public void process(RatingsReader ratingsReader, MetricsWriter metricsWriter) {
        RatingsAccumulator ratingsAccumulator = new RatingsAccumulator();

        ratingsReader.read().parallel().forEach(ratingsAccumulator::accumulate);

        metricsWriter.write(metricsConverter.convert(ratingsAccumulator));
    }
}
