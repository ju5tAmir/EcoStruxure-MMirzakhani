package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Currency;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Project;
import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.dal.EmployeeDAO;
import com.se.ecostruxure_mmirzakhani.utils.CurrencyService;

import java.util.List;

public class EmployeeService {

    private Currency systemCurrency;

    public EmployeeService(){

    }

    public EmployeeService(Currency systemCurrency){
        this.systemCurrency = systemCurrency;
    }

    public void setCurrency(Currency systemCurrency) {
        this.systemCurrency = systemCurrency;
    }
    private static final EmployeeDAO dao = new EmployeeDAO();

    public List<Employee> getAllEmployees(){
        return dao.getAllEmployees();
    }

    public List<Team> getAllTeams() {
        return dao.getAllTeams();
    }

    public List<Project> getAllProjects() {
        return dao.getAllProjects();
    }

    /**
     * Calculate the total cost for the given employee
     */
    public double getTotalCost(Employee employee){
        double      annualSalary                 = employee.getContract().getAnnualSalary();
        double      annualFixedAmount            = employee.getContract().getFixedAnnualAmount();
        double      overheadPercentage           = employee.getContract().getOverheadPercentage();
        double      overheadAmount               = annualSalary * overheadPercentage / 100;
        double      totalAmountInLocalCurrency   = annualSalary + annualFixedAmount + overheadAmount;
        Currency    localCurrency                = employee.getContract().getCurrency();

        double      convertedRate                = CurrencyService.convert(localCurrency, systemCurrency, totalAmountInLocalCurrency);

        return convertedRate;
    }
}
