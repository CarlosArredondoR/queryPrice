package com.api.queryprice.repository;

import com.api.queryprice.entity.PricesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;


public interface PricesRepository extends JpaRepository<PricesDTO, Long> {

    @Query(value = "SELECT * FROM PRICES u WHERE u.BRAND_ID = :brandId AND u.PRODUCT_ID = :productId AND :startDate BETWEEN u.START_DATE AND u.END_DATE ORDER BY PRIORITY DESC",
            nativeQuery = true)
    List<PricesDTO> findProduct(@Param("brandId") short brandId, @Param("productId") int productId, @Param("startDate") Timestamp startDate);
}
