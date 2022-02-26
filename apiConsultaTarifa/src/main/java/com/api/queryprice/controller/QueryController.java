package com.api.queryprice.controller;

import com.api.queryprice.response.QueryResponse;
import com.api.queryprice.service.QueryService;
import io.micrometer.core.annotation.Timed;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;


@Validated
@RestController
@RequestMapping("/api/v1")
public class QueryController {

    private final QueryService queryService;

    public QueryController(final QueryService queryService) {
        this.queryService = queryService;
    }

    @Timed(value = "getproductprice.time", description = "Time taken to return a product price")
    @GetMapping("/brands/{brandId}/products/{productId}")
    public ResponseEntity<QueryResponse> getProduct(@PathVariable("brandId") short brandId, @PathVariable("productId") int productId, @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date startDate) {
        return queryService.queryProduct(brandId, productId, startDate);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getCause(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleMismatchException(MethodArgumentTypeMismatchException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getRootCause(), HttpStatus.BAD_REQUEST);
    }

}
