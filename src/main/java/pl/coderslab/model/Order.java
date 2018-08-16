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
    private Double repairCost;
    private Double partsCost;
    private Double hourlyRate;
    private Integer manHours;

    public Order() {
    }

    public Order(Vehicle vehicle,String servicePlan, String issueDesc, Status status, Employee employee ) {
        this.id = 0;
        setVehicle(vehicle);
        setServicePlan(servicePlan);
        setIssueDesc(issueDesc);
        setStatus(status);
        setEmployee(employee);
        setHourlyRate(this.employee.getHourly_rate());
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

    public Double getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(Double repairCost) {
        this.repairCost = repairCost;
    }

    public Double getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(Double partsCost) {
        this.partsCost = partsCost;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Integer getManHours() {
        return manHours;
    }

    public void setManHours(Integer manHours) {
        this.manHours = manHours;
    }

}
