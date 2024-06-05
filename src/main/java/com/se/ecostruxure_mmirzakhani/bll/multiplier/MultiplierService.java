package com.se.ecostruxure_mmirzakhani.bll.multiplier;

import com.se.ecostruxure_mmirzakhani.be.entities.Multiplier;
import com.se.ecostruxure_mmirzakhani.be.entities.Project;
import com.se.ecostruxure_mmirzakhani.dal.multiplier.MultiplierDAO;
import com.se.ecostruxure_mmirzakhani.dal.project.ProjectDAO;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.util.List;

public class MultiplierService {

    private final MultiplierDAO dao;

    public MultiplierService(){
        this.dao = new MultiplierDAO();
    }

    /**
     * Applies a gross margin multiplier to the original rate.
     *
     * @param originalRate The original rate (hourly or daily).
     * @param grossMarginPercentage The gross margin percentage (e.g., 0.2 for 20%).
     * @return The new rate after applying the gross margin multiplier.
     */
    public static double applyGrossMarginMultiplier(double originalRate, double grossMarginPercentage) {
        return originalRate / (1 - grossMarginPercentage);
    }

    /**
     * Applies a markup multiplier to the original rate.
     *
     * @param originalRate The original rate (hourly or daily).
     * @param markupPercentage The markup percentage (e.g., 0.2 for 20%).
     * @return The new rate after applying the markup multiplier.
     */
    public static double applyMarkupMultiplier(double originalRate, double markupPercentage) {
        return originalRate * (1 + markupPercentage);
    }

    public boolean save(Multiplier multiplier) throws ExceptionHandler {
        // Checks if the given multiplier exists
        if (dao.doesMultiplierExist(multiplier)){

            // if exists, update it
            return dao.updateMultiplier(multiplier);
        } else {

            // If it does not exist, create it
            dao.createMultiplier(multiplier);
        }
        return false;
    }

    public List<Multiplier> getMultipliers(Project project) throws ExceptionHandler{
        return dao.getMultipliersByProject(project);
    }
}
