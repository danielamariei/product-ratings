package com.ratings.product.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Take the command line arguments and construct beans that encapsulate the parameters.
 */
@Configuration
public class ParametersConfiguration {
    @Value("${ratings.path}")
    private String ratingsPath;


    @Value("${max.rated.products:3}")
    private int maxRatedProducts;

    @Bean
    public String ratingsPath() {
        return ratingsPath;
    }

    @Bean
    public int maxRatedProducts() {
        return maxRatedProducts;
    }

}
