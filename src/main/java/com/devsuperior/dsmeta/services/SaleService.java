package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SummaryMinProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<?> getReport() {
        return null;
    }

    public List<SummaryDTO> getSummarySql(String minDate, String maxDate) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate endDate = maxDate.equals("") ? today : LocalDate.parse(maxDate);
        LocalDate startDate = minDate.equals("") ? endDate.minusYears(1L) : LocalDate.parse(minDate);

        List<SummaryMinProjection> projection = repository.summarySaleForSql(startDate, endDate);
        List<SummaryDTO> result = projection.stream().map(x -> new SummaryDTO(x)).toList();

        return result;
    }

    public List<SummaryDTO> getSummaryJpql(String minDate, String maxDate) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate endDate = maxDate.equals("") ? today : LocalDate.parse(maxDate);
        LocalDate startDate = minDate.equals("") ? endDate.minusYears(1L) : LocalDate.parse(minDate);

        List<SummaryDTO> result = repository.summarySaleForJpql(startDate, endDate);

        return result;
    }
}
