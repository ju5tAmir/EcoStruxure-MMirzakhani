package com.se.ecostruxure_mmirzakhani.be.entities;

import java.time.LocalDateTime;

public class TimeLine {
    private LocalDateTime from;
    private LocalDateTime to;


    // ******************** Constructors *********************************
    public TimeLine(){

    }


    // ******************** Methods **************************************
    public TimeLine(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "TimeLine{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }


}
