package com.se.ecostruxure_mmirzakhani.be;

public class Assignment {
    private int                     id;
    private Employee                employee;
    private Project                 project;
    private Team                    team;
    private double                  utilizationPercentage;
    private EmployeeType            employeeType;
    private TimeLine                timeLine;


    // ******************** Constructors *********************************
    public Assignment(){

    }

    public Assignment(int id, Employee employee, Team team, double utilizationPercentage) {
        this.id                     = id;
        this.employee               = employee;
        this.team                   = team;
        this.utilizationPercentage  = utilizationPercentage;
    }

    public Assignment(Employee employee, Team team, double utilizationPercentage) {
        this.employee = employee;
        this.team = team;
        this.utilizationPercentage = utilizationPercentage;
    }


    // ******************** Methods **************************************
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public double getUtilizationPercentage() {
        return utilizationPercentage;
    }

    public void setUtilizationPercentage(double utilizationPercentage) {
        this.utilizationPercentage = utilizationPercentage;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public TimeLine getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(TimeLine timeLine) {
        this.timeLine = timeLine;
    }


    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", employee=" + employee +
                ", project=" + project +
                ", team=" + team +
                ", utilizationPercentage=" + utilizationPercentage +
                ", employeeType=" + employeeType +
                ", timeLine=" + timeLine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assignment that)) return false;

        if (getId() != that.getId()) return false;
        if (Double.compare(getUtilizationPercentage(), that.getUtilizationPercentage()) != 0) return false;
        if (!getEmployee().equals(that.getEmployee())) return false;
        if (!getProject().equals(that.getProject())) return false;
        if (!getTeam().equals(that.getTeam())) return false;
        if (getEmployeeType() != that.getEmployeeType()) return false;
        return getTimeLine() != null ? getTimeLine().equals(that.getTimeLine()) : that.getTimeLine() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + getEmployee().hashCode();
        result = 31 * result + getProject().hashCode();
        result = 31 * result + getTeam().hashCode();
        temp = Double.doubleToLongBits(getUtilizationPercentage());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getEmployeeType().hashCode();
        result = 31 * result + (getTimeLine() != null ? getTimeLine().hashCode() : 0);
        return result;
    }
}
