package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleReportMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.ReportMinProjection;
import com.devsuperior.dsmeta.projections.SummaryMinProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true, value = "SELECT tb_seller.name, SUM(tb_sales.amount) as total " +
            "FROM tb_sales " +
            "INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id " +
            "WHERE tb_sales.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY tb_seller.name ")
    List<SummaryMinProjection> summarySaleForSql(LocalDate minDate, LocalDate maxDate);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummaryMinDTO(obj.seller.name, SUM(obj.amount)) " +
            "FROM Sale obj " +
            "WHERE obj.date >= :minDate " +
            "AND obj.date <= :maxDate " +
            "GROUP BY obj.seller.name ")
    List<SaleSummaryMinDTO> summarySaleForJpql(LocalDate minDate, LocalDate maxDate);

    @Query(nativeQuery = true, value = "SELECT tb_sales.id, tb_sales.date, tb_sales.amount, tb_seller.name "
            + "FROM tb_sales "
            + "INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id "
            + "WHERE tb_sales.date BETWEEN :minDate AND :maxDate "
            + "AND UPPER (tb_seller.name) LIKE UPPER(CONCAT('%', :name, '%')) ",
            countQuery = "SELECT COUNT(tb_sales.id) "
                    + "FROM tb_sales "
                    + "INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id "
                    + "WHERE tb_sales.date BETWEEN :minDate AND :maxDate "
                    + "AND UPPER (tb_seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<ReportMinProjection> reportSaleForSql(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleReportMinDTO(obj.id, obj.date, obj.amount, obj.seller.name) "
            + "FROM Sale obj "
            + "WHERE obj.date BETWEEN :minDate AND :maxDate "
            + "AND (:name = '' OR LOWER(obj.seller.name) LIKE LOWER(CONCAT('%', :name, '%'))) ")
    Page<SaleReportMinDTO> reportSaleForJpql(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

}
