package com.api.queryprice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryResponse {

    private int productId;
    private short brandId;
    private int rate;
    private String startDate;
    private String endDate;
    private BigDecimal finalPrice;

}
