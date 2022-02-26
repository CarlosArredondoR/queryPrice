package com.api.queryprice.service;

import com.api.queryprice.response.QueryResponse;
import org.springframework.http.ResponseEntity;

import java.util.Date;


public interface QueryService {

    ResponseEntity<QueryResponse> queryProduct(short brandId, int productId, Date startDate);

}
