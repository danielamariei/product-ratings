package com.ratings.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles(profiles = {"csv.reader", "captor.writer"})
@TestPropertySource(properties = "ratings.path=src/test/resources/ratings-0_0.csv")
class ApplicationTests {

    @Test
    void contextLoads() {
    }

}
