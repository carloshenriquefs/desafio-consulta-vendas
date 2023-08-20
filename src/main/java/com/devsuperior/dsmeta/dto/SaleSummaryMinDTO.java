package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SummaryMinProjection;

public class SaleSummaryMinDTO {

    private String sellerName;
    private Double total;

    public SaleSummaryMinDTO() {
    }

    public SaleSummaryMinDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SaleSummaryMinDTO(SummaryMinProjection projection) {
        this.sellerName = projection.getName();
        this.total = projection.getTotal();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
