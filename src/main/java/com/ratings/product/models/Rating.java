package com.ratings.product.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
public class Rating {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z][A-Za-z0-9]*$")
    @JsonProperty(value = "Buyer Id", required = true)
    private String buyerId;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z][A-Za-z0-9]*$")
    @JsonProperty(value = "Shop Id", required = true)
    private String shopId;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z][A-Za-z0-9-]*-((0[1-9])|([1-9]\\d))$")
    @JsonProperty(value = "Product Id", required = true)
    private String productId;

    @Min(1)
    @Max(5)
    @NotNull
    @JsonProperty(value = "Rating", required = true)
    private Short rating;

}
