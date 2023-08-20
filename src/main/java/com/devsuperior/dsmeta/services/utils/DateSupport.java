package com.devsuperior.dsmeta.services.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateSupport {

    private LocalDate minDate;
    private LocalDate maxDate;

    public DateSupport(String minDate, String maxDate) {
        this.minDate = (minDate.equals("")) ? this.maxDate.minusYears(1)
                : LocalDate.parse(minDate, DateTimeFormatter.ISO_LOCAL_DATE);

        this.maxDate = (maxDate.equals("")) ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())
                : LocalDate.parse(maxDate, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public void DateSupportConvert(String minDate, String maxDate) {

        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        this.minDate = (minDate.equals("")) ? this.maxDate.minusYears(1L) : LocalDate.parse(minDate);

        this.maxDate = maxDate.equals("") ? today : LocalDate.parse(maxDate);
    }

    public LocalDate getMinDate() {
        return minDate;
    }

    public LocalDate getMaxDate() {
        return maxDate;
    }
}
