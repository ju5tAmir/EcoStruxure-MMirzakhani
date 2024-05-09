package com.se.ecostruxure_mmirzakhani.be;

import java.time.LocalDateTime;

public class RateRecord {
    private double hourlyRate;
    private LocalDateTime time;

    public RateRecord(double hourlyRate, LocalDateTime time) {
        this.hourlyRate = hourlyRate;
        this.time = time;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
