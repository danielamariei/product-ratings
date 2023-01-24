package com.ratings.product.writer;

import com.ratings.product.models.Metrics;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
@Profile("captor.writer")
public class CaptorMetricsWriter implements MetricsWriter {

    private Metrics metrics;

    public Metrics getMetrics() {
        return metrics;
    }

    @Override
    public void write(Metrics metrics) {
        this.metrics = metrics;
    }

}
