package com.ratings.product;

import com.ratings.product.processor.RatingsProcessor;
import com.ratings.product.reader.RatingsReader;
import com.ratings.product.writer.MetricsWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ComputeMetrics {
    @Autowired
    private RatingsProcessor ratingsProcessor;

    @Autowired
    private RatingsReader ratingsReader;

    @Autowired
    private MetricsWriter metricsWriter;

    @PostConstruct
    public void process() {
        ratingsProcessor.process(ratingsReader, metricsWriter);
    }

}
