package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.ReportMinProjection;
import com.devsuperior.dsmeta.projections.SummaryMinProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.devsuperior.dsmeta.services.utils.DateSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public SaleMinDTO findById(Long id) {
        Optional<Sale> result = repository.findById(id);
        Sale entity = result.get();
        return new SaleMinDTO(entity);
    }

    public List<SaleSummaryMinDTO> getSummarySql(String minDate, String maxDate) {

        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate endDate = maxDate.equals("") ? today : LocalDate.parse(maxDate);
        LocalDate startDate = minDate.equals("") ? endDate.minusYears(1L) : LocalDate.parse(minDate);

        List<SummaryMinProjection> projection = repository.summarySaleForSql(startDate, endDate);
        List<SaleSummaryMinDTO> result = projection.stream().map(x -> new SaleSummaryMinDTO(x)).toList();

        return result;
    }

    public List<SaleSummaryMinDTO> getSummaryJpql(String minDate, String maxDate) {

        final DateSupport dateSupport = new DateSupport(minDate, maxDate);

        return this.repository.summarySaleForJpql(dateSupport.getMinDate(), dateSupport.getMaxDate());
    }

    public Page<SaleReportMinDTO> getReportSql(String minDate, String maxDate, String name, Pageable pageable) {

        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
        LocalDate min = minDate.equals("") ? max.minusYears(1L) : LocalDate.parse(minDate);

        Page<ReportMinProjection> projection = repository.reportSaleForSql(min, max, name, pageable);
        Page<SaleReportMinDTO> result = projection.map(x -> new SaleReportMinDTO(x));

        return result;
    }

    public Page<SaleReportMinDTO> getReportJpql(String minDate, String maxDate, String name, Pageable pageable) {

        final DateSupport dateSupport = new DateSupport(minDate, maxDate);

        return this.repository.reportSaleForJpql(dateSupport.getMinDate(), dateSupport.getMaxDate(), name, pageable);
    }
}
