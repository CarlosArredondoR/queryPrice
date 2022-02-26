package com.api.queryprice.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "PRICES")
public class PricesDTO {
    @Column(name = "BRAND_ID")
    private short brandId;
    @Column(name = "START_DATE")
    private Timestamp startDate;
    @Column(name = "END_DATE")
    private Timestamp endDate;
    @Id
    @Column(name = "PRICE_LIST")
    private int priceList;
    @Column(name = "PRODUCT_ID")
    private int productId;
    @Column(name = "PRIORITY")
    private short priority;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "CURR")
    private String currency;
}
