package com.ratings.product.writer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratings.product.models.Metrics;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Profile("command.line.writer")
public class CommandLineMetricsWriter implements MetricsWriter {
    @SneakyThrows
    @Override
    public void write(Metrics metrics) {
        System.out.println(getJsonMetrics(metrics));
        System.out.flush();
    }

    private String getJsonMetrics(Metrics metrics) throws JsonProcessingException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(metrics);
    }
}
