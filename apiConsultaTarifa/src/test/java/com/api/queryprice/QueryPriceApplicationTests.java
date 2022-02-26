package com.api.queryprice;

import com.api.queryprice.controller.QueryController;
import com.api.queryprice.response.QueryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QueryPriceApplicationTests {

    final static SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Autowired
    private QueryController queryController;

    @Test
    void testFirstScenario() throws ParseException {
        ResponseEntity<QueryResponse> response = queryController.getProduct((short) 1,35455, FORMATTER.parse("2020-06-14T10:00:00"));
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, Objects.requireNonNull(response.getBody()).getRate());
        assertEquals(new BigDecimal("35.50").setScale(2, RoundingMode.HALF_UP), response.getBody().getFinalPrice().setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void testSecondScenario() throws ParseException {
        ResponseEntity<QueryResponse> response = queryController.getProduct((short) 1,35455, FORMATTER.parse("2020-06-14T16:00:00"));
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, Objects.requireNonNull(response.getBody()).getRate());
        assertEquals(new BigDecimal("25.45").setScale(2, RoundingMode.HALF_UP), response.getBody().getFinalPrice().setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void testThirdScenario() throws ParseException {
        ResponseEntity<QueryResponse> response = queryController.getProduct((short) 1,35455, FORMATTER.parse("2020-06-14T21:00:00"));
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, Objects.requireNonNull(response.getBody()).getRate());
        assertEquals(new BigDecimal("35.50").setScale(2, RoundingMode.HALF_UP), response.getBody().getFinalPrice().setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void testFourthScenario() throws ParseException {
        ResponseEntity<QueryResponse> response = queryController.getProduct((short) 1,35455, FORMATTER.parse("2020-06-15T10:00:00"));
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(3, Objects.requireNonNull(response.getBody()).getRate());
        assertEquals(new BigDecimal("30.50").setScale(2, RoundingMode.HALF_UP), response.getBody().getFinalPrice().setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void testFifthScenario() throws ParseException {
        ResponseEntity<QueryResponse> response = queryController.getProduct((short) 1,35455, FORMATTER.parse("2020-06-16T21:00:00"));
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(4, Objects.requireNonNull(response.getBody()).getRate());
        assertEquals(new BigDecimal("38.95").setScale(2, RoundingMode.HALF_UP), response.getBody().getFinalPrice().setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void testSixthScenario() throws ParseException {
        ResponseEntity<QueryResponse> response = queryController.getProduct((short) 1,35455, FORMATTER.parse("2021-06-16T21:00:00"));
        assertEquals(404, response.getStatusCodeValue());
    }

}
