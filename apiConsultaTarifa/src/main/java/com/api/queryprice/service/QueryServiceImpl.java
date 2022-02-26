package com.api.queryprice.service;

import com.api.queryprice.entity.PricesDTO;
import com.api.queryprice.repository.PricesRepository;
import com.api.queryprice.response.QueryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class QueryServiceImpl implements QueryService {

    private final PricesRepository pricesRepository;

    public QueryServiceImpl(final PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    @Override
    public ResponseEntity<QueryResponse> queryProduct(short brandId, int productId, Date startDate) {
        Optional<PricesDTO> pricesData = pricesRepository.findProduct(brandId, productId, new Timestamp(startDate.getTime())).stream().findFirst();
        if (pricesData.isPresent()) {
            QueryResponse response = new QueryResponse();
            response.setProductId(pricesData.get().getProductId());
            response.setBrandId(pricesData.get().getBrandId());
            response.setRate(pricesData.get().getPriceList());
            response.setStartDate(pricesData.get().getStartDate().toString());
            response.setEndDate(pricesData.get().getEndDate().toString());
            response.setFinalPrice(pricesData.get().getPrice());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
