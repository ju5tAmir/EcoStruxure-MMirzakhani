package com.se.ecostruxure_mmirzakhani.dal;

import com.se.ecostruxure_mmirzakhani.be.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeDAO {
    // ToDo: These attributes must be removed and replaces in the proper method and fetch these values from DB
    private final List<Employee> employees = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();
    private final List<Project> projects = new ArrayList<>();
    private final List<ProjectMember> projectMembers = new ArrayList<>();
    private final HashMap<Project, List<ProjectMember>> projectToMembers = new HashMap<>();
    private final HashMap<Employee, List<ProjectMember>> employeeToProjects = new HashMap<>();

//    private final DBConnection dbConnection;

    /**
     * Constructor
     */
    public EmployeeDAO() {
        initMockData();

    }

    private void initMockData() {
        // Initial Teams
        Team it = new Team(1, "IT");
        Team hr = new Team(2, "HR");
        Team marketing = new Team(3, "Marketing");
        Team finance = new Team(4, "Finance");
        Team sales = new Team(5, "Sales");
        Team operations = new Team(6, "Operations");
        Team customerService = new Team(7, "Customer Service");
        Team legal = new Team(8, "Legal");
        teams.add(it);
        teams.add(hr);
        teams.add(marketing);
        teams.add(finance);
        teams.add(sales);
        teams.add(operations);
        teams.add(customerService);
        teams.add(legal);

        // Employees
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setFirstName("Albert");
        employee1.setLastName("Einstein");
        employee1.setEmail("Emc2@gmail.com");

        Contract contract1 = new Contract();
        contract1.setId(1);
        contract1.setCurrency(Currency.USD);
        contract1.setAnnualSalary(80_000);       // 80K USD
        contract1.setFixedAnnualAmount(5_000);   // 5K USD
        contract1.setAnnualWorkHours(2000);
        contract1.setAverageDailyWorkHours(8);
        contract1.setOverhead(false);
        contract1.setOverheadPercentage(20);
        contract1.setTimeLine(new TimeLine(LocalDateTime.now().minusMonths(2), LocalDateTime.MAX));
        employee1.setContract(contract1);

        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setFirstName("Robbert");
        employee2.setLastName("Closeheimer");
        employee2.setEmail("Rclose20@gmail.com");

        Contract contract2 = new Contract();
        contract2.setId(2);
        contract2.setCurrency(Currency.EUR);
        contract2.setAnnualSalary(50_000);       // 50K EUR
        contract2.setFixedAnnualAmount(2_000);   // 2K EUR
        contract2.setAnnualWorkHours(1920);
        contract2.setAverageDailyWorkHours(8);
        contract2.setOverhead(true);
        contract2.setOverheadPercentage(20);
        contract2.setTimeLine(new TimeLine(LocalDateTime.now().minusMonths(10), LocalDateTime.MAX));
        employee2.setContract(contract2);

        Employee employee3 = new Employee();
        employee3.setId(3);
        employee3.setFirstName("Marie");
        employee3.setLastName("Curie");
        employee3.setEmail("MarieCurie@gmail.com");

        Contract contract3 = new Contract();
        contract3.setId(3);
        contract3.setCurrency(Currency.USD);
        contract3.setAnnualSalary(95_000);       // 95K USD
        contract3.setFixedAnnualAmount(4_000);   // 4K USD
        contract3.setAnnualWorkHours(1950);
        contract3.setAverageDailyWorkHours(8);
        contract3.setOverhead(true);
        contract3.setOverheadPercentage(15);
        contract3.setTimeLine(new TimeLine(LocalDateTime.now().minusMonths(5), LocalDateTime.MAX));
        employee3.setContract(contract3);

        Employee employee4 = new Employee();
        employee4.setId(4);
        employee4.setFirstName("Isaac");
        employee4.setLastName("Newton");
        employee4.setEmail("NewtonGravity@gmail.com");

        Contract contract4 = new Contract();
        contract4.setId(4);
        contract4.setCurrency(Currency.GBP);
        contract4.setAnnualSalary(85_000);       // 85K GBP
        contract4.setFixedAnnualAmount(3_000);   // 3K GBP
        contract4.setAnnualWorkHours(1900);
        contract4.setAverageDailyWorkHours(8);
        contract4.setOverhead(true);
        contract4.setOverheadPercentage(18);
        contract4.setTimeLine(new TimeLine(LocalDateTime.now().minusMonths(8), LocalDateTime.MAX));
        employee4.setContract(contract4);

        Employee employee5 = new Employee();
        employee5.setId(5);
        employee5.setFirstName("Nikola");
        employee5.setLastName("Tesla");
        employee5.setEmail("NTesla@gmail.com");

        Contract contract5 = new Contract();
        contract5.setId(5);
        contract5.setCurrency(Currency.EUR);
        contract5.setAnnualSalary(70_000);       // 70K EUR
        contract5.setFixedAnnualAmount(2_500);   // 2.5K EUR
        contract5.setAnnualWorkHours(1800);
        contract5.setAverageDailyWorkHours(7);
        contract5.setOverhead(false);
        contract5.setOverheadPercentage(17);
        contract5.setTimeLine(new TimeLine(LocalDateTime.now().minusMonths(3), LocalDateTime.MAX));
        employee5.setContract(contract5);

        Employee employee6 = new Employee();
        employee6.setId(6);
        employee6.setFirstName("Ada");
        employee6.setLastName("Lovelace");
        employee6.setEmail("ALovelace@gmail.com");

        Contract contract6 = new Contract();
        contract6.setId(6);
        contract6.setCurrency(Currency.USD);
        contract6.setAnnualSalary(92_000);       // 92K USD
        contract6.setFixedAnnualAmount(4_500);   // 4.5K USD
        contract6.setAnnualWorkHours(2000);
        contract6.setAverageDailyWorkHours(8);
        contract6.setOverhead(true);
        contract6.setOverheadPercentage(19);
        contract6.setTimeLine(new TimeLine(LocalDateTime.now().minusMonths(6), LocalDateTime.MAX));
        employee6.setContract(contract6);

        Employee employee7 = new Employee();
        employee7.setId(7);
        employee7.setFirstName("Charles");
        employee7.setLastName("Babbage");
        employee7.setEmail("CBabbage@gmail.com");

        Contract contract7 = new Contract();
        contract7.setId(7);
        contract7.setCurrency(Currency.GBP);
        contract7.setAnnualSalary(88_000);       // 88K GBP
        contract7.setFixedAnnualAmount(3_200);   // 3.2K GBP
        contract7.setAnnualWorkHours(1850);
        contract7.setAverageDailyWorkHours(8);
        contract7.setOverhead(false);
        contract7.setOverheadPercentage(16);
        contract7.setTimeLine(new TimeLine(LocalDateTime.now().minusMonths(4), LocalDateTime.MAX));
        employee7.setContract(contract7);

        Employee employee8 = new Employee();
        employee8.setId(8);
        employee8.setFirstName("Grace");
        employee8.setLastName("Hopper");
        employee8.setEmail("GHopper@gmail.com");

        Contract contract8 = new Contract();
        contract8.setId(8);
        contract8.setCurrency(Currency.USD);
        contract8.setAnnualSalary(100_000);      // 100K USD
        contract8.setFixedAnnualAmount(5_000);   // 5K USD
        contract8.setAnnualWorkHours(2050);
        contract8.setAverageDailyWorkHours(8);
        contract8.setOverhead(false);
        contract8.setOverheadPercentage(20);
        contract8.setTimeLine(new TimeLine(LocalDateTime.now().minusMonths(7), LocalDateTime.MAX));
        employee8.setContract(contract8);

        Employee employee9 = new Employee();
        employee9.setId(9);
        employee9.setFirstName("Alan");
        employee9.setLastName("Turing");
        employee9.setEmail("ATuring@gmail.com");

        Contract contract9 = new Contract();
        contract9.setId(9);
        contract9.setCurrency(Currency.USD);
        contract9.setAnnualSalary(110_000);      // 110K USD
        contract9.setFixedAnnualAmount(6_000);   // 6K USD
        contract9.setAnnualWorkHours(2100);
        contract9.setAverageDailyWorkHours(8);
        contract9.setOverhead(false);
        contract9.setOverheadPercentage(18);
        contract9.setTimeLine(new TimeLine(LocalDateTime.now().minusMonths(1), LocalDateTime.MAX));
        employee9.setContract(contract9);

        Employee employee10 = new Employee();
        employee10.setId(10);
        employee10.setFirstName("Katherine");
        employee10.setLastName("Johnson");
        employee10.setEmail("KJohnson@gmail.com");

        Contract contract10 = new Contract();
        contract10.setId(10);
        contract10.setCurrency(Currency.USD);
        contract10.setAnnualSalary(90_000);      // 90K USD
        contract10.setFixedAnnualAmount(4_000);  // 4K USD
        contract10.setAnnualWorkHours(1950);
        contract10.setAverageDailyWorkHours(8);
        contract10.setOverhead(false);
        contract10.setOverheadPercentage(17);
        contract10.setTimeLine(new TimeLine(LocalDateTime.now().minusMonths(9), LocalDateTime.MAX));
        employee10.setContract(contract10);

        // Projects
        Project p1 = new Project(1, "Project Alpha", Country.DENMARK);
        Project p2 = new Project(2, "Project Beta", Country.UNITED_STATES);
        Project p3 = new Project(3, "Project Charlie", Country.SWEDEN);

        projects.add(p1);
        projects.add(p2);
        projects.add(p3);

        // Project Members (ensure utilization does not exceed 100%)
        ProjectMember pm1 = new ProjectMember(employee1, it, 50);  // 50% utilization
        ProjectMember pm2 = new ProjectMember(employee1, hr, 50);  // 50% utilization

        ProjectMember pm3 = new ProjectMember(employee2, it, 30);  // 30% utilization
        ProjectMember pm4 = new ProjectMember(employee2, hr, 60);  // 60% utilization

        ProjectMember pm5 = new ProjectMember(employee3, marketing, 40);  // 40% utilization
        ProjectMember pm6 = new ProjectMember(employee3, finance, 40);    // 40% utilization

        ProjectMember pm7 = new ProjectMember(employee4, sales, 60);      // 60% utilization
        ProjectMember pm8 = new ProjectMember(employee4, operations, 20); // 20% utilization

        ProjectMember pm9 = new ProjectMember(employee5, customerService, 30); // 30% utilization
        ProjectMember pm10 = new ProjectMember(employee5, legal, 50);          // 50% utilization

        ProjectMember pm11 = new ProjectMember(employee6, it, 40);         // 40% utilization
        ProjectMember pm12 = new ProjectMember(employee6, hr, 30);         // 30% utilization

        ProjectMember pm13 = new ProjectMember(employee7, marketing, 40);  // 40% utilization
        ProjectMember pm14 = new ProjectMember(employee7, finance, 40);    // 40% utilization

        ProjectMember pm15 = new ProjectMember(employee8, sales, 50);      // 50% utilization
        ProjectMember pm16 = new ProjectMember(employee8, operations, 40); // 40% utilization

        ProjectMember pm17 = new ProjectMember(employee9, customerService, 70); // 70% utilization
        ProjectMember pm18 = new ProjectMember(employee9, legal, 20);          // 20% utilization

        ProjectMember pm19 = new ProjectMember(employee10, it, 50);        // 50% utilization
        ProjectMember pm20 = new ProjectMember(employee10, hr, 30);        // 30% utilization

        projectMembers.add(pm1);
        projectMembers.add(pm2);
        projectMembers.add(pm3);
        projectMembers.add(pm4);
        projectMembers.add(pm5);
        projectMembers.add(pm6);
        projectMembers.add(pm7);
        projectMembers.add(pm8);
        projectMembers.add(pm9);
        projectMembers.add(pm10);
        projectMembers.add(pm11);
        projectMembers.add(pm12);
        projectMembers.add(pm13);
        projectMembers.add(pm14);
        projectMembers.add(pm15);
        projectMembers.add(pm16);
        projectMembers.add(pm17);
        projectMembers.add(pm18);
        projectMembers.add(pm19);
        projectMembers.add(pm20);

        // List of project members to link
        List<ProjectMember> pmList1 = new ArrayList<>();
        pmList1.add(pm1);
        pmList1.add(pm2);
        pmList1.add(pm3);
        pmList1.add(pm4);
        pmList1.add(pm5);
        pmList1.add(pm6);

        List<ProjectMember> pmList2 = new ArrayList<>();
        pmList2.add(pm7);
        pmList2.add(pm8);
        pmList2.add(pm9);
        pmList2.add(pm10);
        pmList2.add(pm11);
        pmList2.add(pm12);

        List<ProjectMember> pmList3 = new ArrayList<>();
        pmList3.add(pm13);
        pmList3.add(pm14);
        pmList3.add(pm15);
        pmList3.add(pm16);
        pmList3.add(pm17);
        pmList3.add(pm18);
        pmList3.add(pm19);
        pmList3.add(pm20);

        // Update hashmap
        projectToMembers.put(p1, pmList1); // Project Alpha with members
        projectToMembers.put(p2, pmList2); // Project Beta with members
        projectToMembers.put(p3, new ArrayList<>()); // Project Charlie without members

        // Update Total Employees
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);
        employees.add(employee7);
        employees.add(employee8);
        employees.add(employee9);
        employees.add(employee10);
    }

//
//    private void initMockData(){
//        // Initial Teams
//        Team it = new Team(1, "IT");
//        Team hr = new Team(2, "HR");
//        Team marketing = new Team(3, "Marketing");
//        Team finance = new Team(4, "Finance");
//        Team sales = new Team(5, "Sales");
//        Team operations = new Team(6, "Operations");
//        Team customerService = new Team(7, "Customer Service");
//        Team legal = new Team(8, "Legal");
//        teams.add(marketing);
//        teams.add(finance);
//        teams.add(sales);
//        teams.add(operations);
//        teams.add(customerService);
//        teams.add(legal);
//        teams.add(it); teams.add(hr);
//
//        Employee employee1 = new Employee();
//        employee1.setId(1);
//        employee1.setFirstName("Albert");
//        employee1.setLastName("Einstein");
//        employee1.setEmail("Emc2@gmail.com");
//
//        Contract contract1 = new Contract();
//        contract1.setId(1);
//        contract1.setCurrency(Currency.USD);
//        contract1.setAnnualSalary(80_000);       // 80K USD
//        contract1.setFixedAnnualAmount(5_000);   // 5K USD
//        contract1.setAnnualWorkHours(2000);
//        contract1.setAverageDailyWorkHours(8);
//        contract1.setOverhead(false);
//        contract1.setOverheadPercentage(20);
//        contract1.setTimeLine(new TimeLine(LocalDateTime.now().minusMonths(2), LocalDateTime.MAX));
//        employee1.setContract(contract1);
//
//        Project p1 = new Project(1, "Project Alpha", Country.DENMARK);
//        Project p2 = new Project(2, "Project Beta", Country.UNITED_STATES);
//
//        projects.add(p1); projects.add(p2);
//
//        // ***** 2 *****
//        Employee employee2 = new Employee();
//        employee2.setId(2);
//        employee2.setFirstName("Robbert");
//        employee2.setLastName("Closeheimer");
//        employee2.setEmail("Rclose20@gmail.com");
//
//        Contract contract2 = new Contract();
//        contract2.setId(2);
//        contract2.setCurrency(Currency.EUR);
//        contract2.setAnnualSalary(50_000);
//        contract2.setFixedAnnualAmount(2_000);
//        contract2.setAnnualWorkHours(1920);
//        contract2.setAverageDailyWorkHours(8);
//        contract2.setOverhead(true);
//        contract2.setOverheadPercentage(20);
//        contract2.setTimeLine(new TimeLine(LocalDateTime.now().minusMonths(10), LocalDateTime.MAX));
//
//        employee2.setContract(contract2);
//
//        Project p3 = new Project(3, "Project Charlie", Country.SWEDEN);
//        projects.add(p3);
//
//
//        // Project Members
//        ProjectMember pm1 = new ProjectMember(employee1, it, 20);
//        ProjectMember pm2 = new ProjectMember(employee1, hr, 50);
//        ProjectMember pm3 = new ProjectMember(employee2, it, 80);
//        ProjectMember pm4 = new ProjectMember(employee2, it, 20);
//        projectMembers.add(pm1);projectMembers.add(pm2);projectMembers.add(pm3); projectMembers.add(pm4);
//
//        // List of project members to link
//        List<ProjectMember> pmList = new ArrayList<>();
//        pmList.add(pm1); pmList.add(pm2); pmList.add(pm3);
//
//        List<ProjectMember> pmList2 = new ArrayList<>();
//        pmList2.add(pm4);
//
//        // Update hashmap
//        projectToMembers.put(p1, pmList);
//        projectToMembers.put(p2, pmList2);
//
//
//        // Update Total Employees
//        employees.add(employee1);
//        employees.add(employee2);
//    }


    public List<ProjectMember> getProjectMembers(Project project){
        // Retrieve all projects members from db linked to the given project

        return projectToMembers.get(project);
    }

    public HashMap<Project, List<ProjectMember>> getAllProjectsWithTheirMembers(){
        return projectToMembers;
    }

    /**
     * Retrieve all the employees
     */
    public List<Employee> getAllEmployees(){
        return employees;
    }

    /**
     * Retrieve all the Teams
     */
    public List<Team> getAllTeams(){
        return teams;
    }

    public List<Project> getAllProjects(Employee employee){
        List<Project> projectList = new ArrayList<>();

        // Return all the projects associated with the employee
        for (Project project: projectToMembers.keySet()){
            for (ProjectMember pm: projectToMembers.get(project)){
                if (pm.getEmployee().equals(employee)){
                    projectList.add(project);
                }
            }
        }
        return projectList;
    }

    public List<Project> getAllProjects(){
        return projects;
    }


    public boolean createEmployee(Employee employee, List<Project> projects) {
        // Create Employee + Update employee object ID

        // Add Projects    + Update project objects ID

        return true;
    }

    public History getEmployeeHistory(Employee employee){
        // Fake History ToDo: Must be replaced with real history from db

        Contract c1 =new Contract();
        c1.setAnnualSalary(50000);
        c1.setId(3);
        c1.setTimeLine(new TimeLine(LocalDateTime.now().minusYears(4), LocalDateTime.now().minusYears(3)));


        Contract c2 = new Contract();
        c2.setAnnualSalary(80000);
        c2.setTimeLine(new TimeLine(LocalDateTime.now().minusYears(2), LocalDateTime.now().minusYears(1)));


        Contract c3 = new Contract();
        c3.setAnnualSalary(99000);
        c3.setTimeLine(new TimeLine(LocalDateTime.now().minusYears(1), LocalDateTime.MAX));


        // ToDo: Don't forget to get also the latest item for Contract or Project which will not be in the History table in DB.
        List<Contract> listOfContracts = new ArrayList<>();

        listOfContracts.add(c1); listOfContracts.add(c2); listOfContracts.add(c3);

        History history = new History();
        history.setContracts(listOfContracts);



        return history;


    }


}
//
//    public boolean createEmployee(Employee employee) throws ExceptionHandler {
//        // Insert query to Employee table
//        String insertEmployee = "INSERT INTO Employees(FirstName, LastName, Region, Country) VALUES (?,?,?,?)";
//        // Insert query for connection an employee to a team (if possible)
//        String insertTeam = "INSERT INTO Team(TeamName, EmployeeID) VALUES (?,?)";
//        // Insert contract information
//        String insertContract = "INSERT INTO Contract(EmployeeID, AnnualSalary, FixedAnnualAmount, AnnualWorkHours, AverageDailyWorkHours, OverheadPercentage, UtilizationPercentage, MarkupPercentage, GrossMarginPercentage, IsOverhead) VALUES (?,?,?,?,?,?,?,?,?,?)";
//
//        try (Connection conn = dbConnection.getConnection()) {
//            // Starting a transaction
//            conn.setAutoCommit(false);
//            // Protect from update/delete for this row
//            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
//
//            // Statements
//            try (PreparedStatement employeeStatement = conn.prepareStatement(insertEmployee, Statement.RETURN_GENERATED_KEYS);
//                 PreparedStatement teamStatement = conn.prepareStatement(insertTeam);
//                 PreparedStatement contractStatement = conn.prepareStatement(insertContract)) {
//
//                // Insert employee information
//                employeeStatement.setString(1, employee.getFirstName());
//                employeeStatement.setString(2, employee.getLastName());
//                employeeStatement.setString(3, employee.getRegion().getName());
//                employeeStatement.setString(4, employee.getCountry().getValue());
//
//                employeeStatement.executeUpdate();
//
//                // Retrieve the generated id for the employee
//                try (ResultSet rs = employeeStatement.getGeneratedKeys()) {
//                    if (rs.next()) {
//                        employee.setId(rs.getInt(1));
//                    } else {
//                        throw new ExceptionHandler(ExceptionMessage.KEY_GENERATION_FAILURE.getValue());
//                    }
//                }
//
//                // Insert team details if employee is part of a team
//                if (employee.getTeam() != null) {
//                    teamStatement.setString(1, employee.getTeam().getName());
//                    teamStatement.setInt(2, employee.getId());
//                    teamStatement.addBatch();
//                }
//
//                // Insert contract information
//                contractStatement.setInt(1, employee.getId());
//                contractStatement.setDouble(2, employee.getContract().getAnnualSalary());
//                contractStatement.setDouble(3, employee.getContract().getFixedAnnualAmount());
//                contractStatement.setDouble(4, employee.getContract().getAnnualWorkHours());
//                contractStatement.setDouble(5, employee.getContract().getAverageDailyWorkHours());
//                contractStatement.setDouble(6, employee.getContract().getOverheadPercentage());
//                contractStatement.setDouble(7, employee.getContract().getUtilizationPercentage());
//                contractStatement.setDouble(8, employee.getContract().getMarkupPercentage());
//                contractStatement.setDouble(9, employee.getContract().getGrossMarginPercentage());
//                contractStatement.setBoolean(10, employee.getContract().isOverhead());
//                contractStatement.addBatch();
//
//                // Execute batches
//                employeeStatement.executeBatch();
//                teamStatement.executeBatch();
//                contractStatement.executeBatch();
//
//                // Commit transaction
//                conn.commit();
//            } catch (SQLException e) {
//                // Rollback if something went wrong
//                conn.rollback();
//                throw new ExceptionHandler(ExceptionMessage.EMPLOYEE_INSERT_FAILED.getValue(), e.getMessage());
//            }
//            return true;
//        } catch (SQLException ex) {
//
//            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue(), ex.getMessage());
//        }
//    }
//
//    public Employee getEmployee(int id) throws ExceptionHandler{
//        // Employee object to update
//        Employee employee = new Employee();
//
//        // SQL Query to fetch an employee by id
//        String getEmployeeQuery = "SELECT Employees.*, T.*, C.* " +
//                "FROM Employees " +
//                "JOIN dbo.Team T " +
//                "ON Employees.EmployeeID = T.EmployeeID " +
//                "JOIN dbo.Contract C ON " +
//                "Employees.EmployeeID = C.EmployeeID " +
//                "WHERE dbo.Employees.EmployeeID = ?";
//
//        try (Connection conn = dbConnection.getConnection();
//             PreparedStatement statement = conn.prepareStatement(getEmployeeQuery, Statement.RETURN_GENERATED_KEYS)){
//            statement.setInt(1, id);
//
//            // Results
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()){
//                // Set employee properties
//                employee.setFirstName(rs.getString("FirstName"));
//                employee.setLastName(rs.getString("LastName"));
//                employee.setRegion(Region.valueOf(rs.getString("Region").toUpperCase()));
//                employee.setCountry(Country.valueOf(rs.getString("Country").toUpperCase()));
//
//                // Set team properties
//                Team team = new Team();
//                team.setId(rs.getInt("TeamID"));
//                team.setName(rs.getString("TeamName"));
//                employee.setTeam(team);
//
//
//                // Set contract properties
//                Profile contract = new Profile();
//                contract.setAnnualSalary(rs.getDouble("AnnualSalary"));
//                contract.setAnnualWorkHours(rs.getDouble("AnnualWorkHours"));
//                contract.setAverageDailyWorkHours(rs.getDouble("AverageDailyWorkHours"));
//                contract.setFixedAnnualAmount(rs.getDouble("FixedAnnualAmount"));
//                contract.setOverheadPercentage(rs.getDouble("OverheadPercentage"));
//                contract.setUtilizationPercentage(rs.getDouble("UtilizationPercentage"));
//                contract.setMarkupPercentage(rs.getDouble("MarkupPercentage"));
//                contract.setGrossMarginPercentage(rs.getDouble("GrossMarginPercentage"));
//                contract.setOverhead(rs.getBoolean("IsOverhead"));
//                contract.setValidFrom(rs.getTimestamp("SysStartTime").toLocalDateTime());
//                contract.setValidUntil(rs.getTimestamp("SysEndTime").toLocalDateTime());
//
//                employee.setContract(contract);
//            }
//            return employee;
//
//        } catch (SQLException e){
//            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue(), e.getMessage());
//        }
//    }
//
//    public List<Employee> getAllEmployees() throws ExceptionHandler {
//        List<Employee> employees = new ArrayList<>();
//
//        // SQL Query to fetch all employees
//        String getAllEmployeesQuery = "SELECT Employees.*, T.*, C.* " +
//                "FROM Employees " +
//                "JOIN dbo.Team T " +
//                "ON Employees.EmployeeID = T.EmployeeID " +
//                "JOIN dbo.Contract C ON " +
//                "Employees.EmployeeID = C.EmployeeID";
//
//        try (Connection conn = dbConnection.getConnection();
//             PreparedStatement statement = conn.prepareStatement(getAllEmployeesQuery)) {
//
//            // Results
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                Employee employee = new Employee();
//
//                // Set employee properties
//                employee.setFirstName(rs.getString("FirstName"));
//                employee.setLastName(rs.getString("LastName"));
//                try {
//                    employee.setRegion(Region.valueOf(rs.getString("Region").toUpperCase()));
//                    employee.setCountry(Country.valueOf(rs.getString("Country").toUpperCase()));
//                } catch (RuntimeException e){
//                    employee.setRegion(Region.EUROPE);
//                    employee.setCountry(Country.DENMARK);
//                }
//
//                // Set team properties
//                Team team = new Team();
//                team.setId(rs.getInt("TeamID"));
//                team.setName(rs.getString("TeamName"));
//                employee.setTeam(team);
//
//                // Set contract properties
//                Profile contract = new Profile();
//                contract.setAnnualSalary(rs.getDouble("AnnualSalary"));
//                contract.setAnnualWorkHours(rs.getDouble("AnnualWorkHours"));
//                contract.setAverageDailyWorkHours(rs.getDouble("AverageDailyWorkHours"));
//                contract.setFixedAnnualAmount(rs.getDouble("FixedAnnualAmount"));
//                contract.setOverheadPercentage(rs.getDouble("OverheadPercentage"));
//                contract.setUtilizationPercentage(rs.getDouble("UtilizationPercentage"));
//                contract.setMarkupPercentage(rs.getDouble("MarkupPercentage"));
//                contract.setGrossMarginPercentage(rs.getDouble("GrossMarginPercentage"));
//                contract.setOverhead(rs.getBoolean("IsOverhead"));
//                contract.setValidFrom(rs.getTimestamp("SysStartTime").toLocalDateTime());
//                contract.setValidUntil(rs.getTimestamp("SysEndTime").toLocalDateTime());
//
//                employee.setContract(contract);
//
//                employees.add(employee);
//            }
//            return employees;
//
//        } catch (SQLException e) {
//            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue(), e.getMessage());
//        }
//    }
//
//    public boolean updateEmployee(Employee employee) throws ExceptionHandler {
//        String updateEmployee = "UPDATE Employees SET FirstName = ?, LastName = ?, Region = ?, Country = ? WHERE EmployeeID = ?";
//        String updateTeam = "UPDATE Team SET TeamName = ? WHERE EmployeeID = ?";
//        String updateContract = "UPDATE Contract SET AnnualSalary = ?, FixedAnnualAmount = ?, AnnualWorkHours = ?, AverageDailyWorkHours = ?, OverheadPercentage = ?, UtilizationPercentage = ?, MarkupPercentage = ?, GrossMarginPercentage = ?, IsOverhead = ? WHERE EmployeeID = ?";
//
//        try (Connection conn = dbConnection.getConnection()) {
//            conn.setAutoCommit(false);
//            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
//
//            try (PreparedStatement employeeStmt = conn.prepareStatement(updateEmployee);
//                 PreparedStatement teamStmt = conn.prepareStatement(updateTeam);
//                 PreparedStatement contractStmt = conn.prepareStatement(updateContract)) {
//
//                // Set parameters for updating employee
//                employeeStmt.setString(1, employee.getFirstName());
//                employeeStmt.setString(2, employee.getLastName());
//                employeeStmt.setString(3, employee.getRegion().getName());
//                employeeStmt.setString(4, employee.getCountry().getValue());
//                employeeStmt.setInt(5, employee.getId());
//                employeeStmt.executeUpdate();
//
//                // Set parameters for updating team
//                if (employee.getTeam() != null) {
//                    teamStmt.setString(1, employee.getTeam().getName());
//                    teamStmt.setInt(2, employee.getId());
//                    teamStmt.executeUpdate();
//                }
//
//                // Set parameters for updating contract
//                Profile contract = employee.getContract();
//                contractStmt.setDouble(1, contract.getAnnualSalary());
//                contractStmt.setDouble(2, contract.getFixedAnnualAmount());
//                contractStmt.setDouble(3, contract.getAnnualWorkHours());
//                contractStmt.setDouble(4, contract.getAverageDailyWorkHours());
//                contractStmt.setDouble(5, contract.getOverheadPercentage());
//                contractStmt.setDouble(6, contract.getUtilizationPercentage());
//                contractStmt.setDouble(7, contract.getMarkupPercentage());
//                contractStmt.setDouble(8, contract.getGrossMarginPercentage());
//                contractStmt.setBoolean(9, contract.isOverhead());
//                contractStmt.setInt(10, employee.getId());
//                contractStmt.executeUpdate();
//
//                // Commit the transaction
//                conn.commit();
//                return true;
//            } catch (SQLException e) {
//                // Rollback the transaction in case of SQL exception
//                conn.rollback();
//                throw new ExceptionHandler("Failed to update employee: " + e.getMessage());
//            }
//        } catch (SQLException ex) {
//            throw new ExceptionHandler("Database connection failure: " + ex.getMessage());
//        }
//    }
//
//
//    public boolean deleteEmployee(int employeeId) throws ExceptionHandler {
//        String deleteContract = "DELETE FROM Contract WHERE EmployeeID = ?";
//        String deleteTeam = "DELETE FROM Team WHERE EmployeeID = ?";
//        String deleteEmployee = "DELETE FROM Employees WHERE EmployeeID = ?";
//
//        try (Connection conn = dbConnection.getConnection()) {
//            conn.setAutoCommit(false);
//            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
//
//            try (PreparedStatement contractStmt = conn.prepareStatement(deleteContract);
//                 PreparedStatement teamStmt = conn.prepareStatement(deleteTeam);
//                 PreparedStatement employeeStmt = conn.prepareStatement(deleteEmployee)) {
//
//                contractStmt.setInt(1, employeeId);
//                contractStmt.executeUpdate();
//
//                teamStmt.setInt(1, employeeId);
//                teamStmt.executeUpdate();
//
//                employeeStmt.setInt(1, employeeId);
//                employeeStmt.executeUpdate();
//
//                conn.commit();
//                return true;
//            } catch (SQLException e) {
//                conn.rollback();
//                throw new ExceptionHandler(e.getMessage());
//            }
//        } catch (SQLException ex) {
//            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue(), ex.getMessage());
//        }
//    }
//}
