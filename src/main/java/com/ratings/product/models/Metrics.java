package com.ratings.product.models;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Metrics {

    private long validLines;
    private long invalidLines;
    private List<String> bestRatedProducts;
    private List<String> worstRatedProducts;
    private String mostRatedProduct;
    private String leastRatedProduct;

}
