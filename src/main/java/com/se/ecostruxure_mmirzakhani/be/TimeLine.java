package com.se.ecostruxure_mmirzakhani.be;

import java.time.LocalDateTime;

public class TimeLine {
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;

    public TimeLine(){

    }

    public TimeLine(LocalDateTime validFrom, LocalDateTime validUntil) {
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }


    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }
}
