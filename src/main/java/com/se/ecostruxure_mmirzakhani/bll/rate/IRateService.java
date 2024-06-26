package com.se.ecostruxure_mmirzakhani.bll.rate;

public interface IRateService {
    double getHourlyRate();
    double getDailyRate();
    double getDirectCosts();
    double getOverheadCosts();
    double getTotalCosts();
}
