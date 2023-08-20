package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleReportMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryMinDTO;
import com.devsuperior.dsmeta.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
        SaleMinDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<List<SaleSummaryMinDTO>> getSummaryForSql(
            @RequestParam(name = "minDate", defaultValue = "") String minDate,
            @RequestParam(name = "maxDate", defaultValue = "") String maxDate) {
        List<SaleSummaryMinDTO> summary = service.getSummarySql(minDate, maxDate);
        return ResponseEntity.ok(summary);
    }

    @GetMapping(value = "/summary/jpql")
    public ResponseEntity<List<SaleSummaryMinDTO>> getSummaryForJpql(
            @RequestParam(name = "minDate", defaultValue = "") String minDate,
            @RequestParam(name = "maxDate", defaultValue = "") String maxDate) {
        List<SaleSummaryMinDTO> summary = service.getSummaryJpql(minDate, maxDate);
        return ResponseEntity.ok(summary);
    }

    @GetMapping(value = "/report")
    public ResponseEntity<Page<SaleReportMinDTO>> getReportSql(
            @RequestParam(name = "minDate", defaultValue = "") String minDate,
            @RequestParam(name = "maxDate", defaultValue = "") String maxDate,
            @RequestParam(name = "name", defaultValue = "") String name,
            Pageable pageable
    ) {
        Page<SaleReportMinDTO> report = service.getReportSql(minDate, maxDate, name, pageable);
        return ResponseEntity.ok(report);
    }

    @GetMapping(value = "/report/jpql")
    public ResponseEntity<Page<SaleReportMinDTO>> getReportJpql(
            @RequestParam(name = "minDate", defaultValue = "") String minDate,
            @RequestParam(name = "maxDate", defaultValue = "") String maxDate,
            @RequestParam(name = "name", defaultValue = "") String name,
            Pageable pageable
    ) {
        Page<SaleReportMinDTO> report = service.getReportJpql(minDate, maxDate, name, pageable);
        return ResponseEntity.ok(report);
    }

}
