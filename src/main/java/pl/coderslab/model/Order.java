package pl.coderslab.model;

public class Order {

    private int id;
    private Employee employee;
    private Vehicle vehicle;
    private Status status;
    private String serviceAccept;
    private String servicePlan;
    private String serviceStart;
    private String issueDesc;
    private String repairDesc;
    private double repairCost;
    private double partsCost;
    private double hourlyRate;
    private int manHours;

    public Order() {
    }

    public Order(Vehicle vehicle,String servicePlan, String issueDesc, Status status, Employee employee ) {
        this.id = 0;
        setVehicle(vehicle);
        setServicePlan(servicePlan);
        setIssueDesc(issueDesc);
        setStatus(status);
        setEmployee(employee);
    }

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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getServiceAccept() {
        return serviceAccept;
    }

    public void setServiceAccept(String serviceAccept) {
        this.serviceAccept = serviceAccept;
    }

    public String getServicePlan() {
        return servicePlan;
    }

    public void setServicePlan(String servicePlan) {
        this.servicePlan = servicePlan;
    }

    public String getServiceStart() {
        return serviceStart;
    }

    public void setServiceStart(String serviceStart) {
        this.serviceStart = serviceStart;
    }

    public String getIssueDesc() {
        return issueDesc;
    }

    public void setIssueDesc(String issueDesc) {
        this.issueDesc = issueDesc;
    }

    public String getRepairDesc() {
        return repairDesc;
    }

    public void setRepairDesc(String repairDesc) {
        this.repairDesc = repairDesc;
    }

    public double getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(double repairCost) {
        this.repairCost = repairCost;
    }

    public double getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(double partsCost) {
        this.partsCost = partsCost;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getManHours() {
        return manHours;
    }

    public void setManHours(int manHours) {
        this.manHours = manHours;
    }

}
