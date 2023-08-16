package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/report")
    public ResponseEntity<?> getReport() {
        return null;
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<List<SummaryDTO>> getSummaryForSql(
            @RequestParam(name = "minDate", defaultValue = "") String minDate,
            @RequestParam(name = "maxDate", defaultValue = "") String maxDate) {
        List<SummaryDTO> summary = service.getSummarySql(minDate, maxDate);
        return ResponseEntity.ok(summary);
    }

    @GetMapping(value = "/summary/jpql")
    public ResponseEntity<List<SummaryDTO>> getSummaryForJpql(
            @RequestParam(name = "minDate", defaultValue = "") String minDate,
            @RequestParam(name = "maxDate", defaultValue = "") String maxDate) {
        List<SummaryDTO> summary = service.getSummaryJpql(minDate, maxDate);
        return ResponseEntity.ok(summary);
    }
}
