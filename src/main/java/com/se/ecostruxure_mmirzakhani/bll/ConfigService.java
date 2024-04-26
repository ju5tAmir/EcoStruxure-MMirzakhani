package com.se.ecostruxure_mmirzakhani.bll;


import com.se.ecostruxure_mmirzakhani.be.Config;
import com.se.ecostruxure_mmirzakhani.be.Employee;

public class ConfigService {
    // ToDo: Implement methods to handle user custom configs based on Config entity using Math class.

    /**
     * Constructor
     */
    public ConfigService(){

    }



    /**
     * Translate the config operation and replace symbols with actual values from employee properties.
     * This method takes an Employee object and a Config object as parameters. It retrieves a formula
     * from the Config object, which contains symbols representing properties of the employee. It then
     * replaces these symbols with the actual values from the corresponding properties of the Employee
     * object. The translated formula is returned as a String.
     *
     * @param employee The Employee object containing the properties to be placed into the formula.
     * @param config The Config object containing the formula with symbols to be replaced.
     * @return The translated formula with actual values substituted.
     */
    public String translate(Employee employee, Config config){
        // String to replace user input symbols with actual values
        String translatedFormula = config.getFormula();

        translatedFormula = translatedFormula.replace(TranslateSymbols.ANNUAL_SALARY.getValue(), Double.toString(employee.getAnnualSalary()));

        return translatedFormula;
    }

}
