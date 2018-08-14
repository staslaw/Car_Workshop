//package pl.coderslab.model;
//
//public class Orders {
//
//    private int id;
//    private Employee employee;
//    private Vehicle vehicle;
//    private Status status;
//    private String serviceAccept;
//    private String servicePlan;
//    private String serviceStart;
//    private String issueDesc;
//    private String repairDesc;
//    private double repairCost;
//    private double partsCost;
//    private double hourlyRate;
//    private int manHours;
//
//    public Orders() {
//    }
//
//    public Orders(Vehicle vehicle, String serviceAccept, String issueDesc, Status status) {
//        this.id = 0;
//        setVehicle(vehicle);
//        setServiceAccept(serviceAccept);
//        setIssueDesc(issueDesc);
//        setStatus(status);
//    }
//
//    public Orders(Vehicle vehicle, String serviceAccept, String issueDesc, Status status, Employee employee) {
//        this(vehicle, serviceAccept, issueDesc, status);
//        setEmployee(employee);
//    }
//
//    public Orders(Vehicle vehicle, String serviceAccept, String issueDesc, Status status, Employee employee, String servicePlan) {
//        this(vehicle, serviceAccept, issueDesc, status, employee);
//        setServicePlan(servicePlan);
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
//
//    public Vehicle getVehicle() {
//        return vehicle;
//    }
//
//    public void setVehicle(Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }
//
//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }
//
//    public String getServiceAccept() {
//        return serviceAccept;
//    }
//
//    public void setServiceAccept(String serviceAccept) {
//        this.serviceAccept = serviceAccept;
//    }
//
//    public String getServicePlan() {
//        return servicePlan;
//    }
//
//    public void setServicePlan(String servicePlan) {
//        this.servicePlan = servicePlan;
//    }
//
//    public String getServiceStart() {
//        return serviceStart;
//    }
//
//    public void setServiceStart(String serviceStart) {
//        this.serviceStart = serviceStart;
//    }
//
//    public String getIssueDesc() {
//        return issueDesc;
//    }
//
//    public void setIssueDesc(String issueDesc) {
//        this.issueDesc = issueDesc;
//    }
//
//    public String getRepairDesc() {
//        return repairDesc;
//    }
//
//    public void setRepairDesc(String repairDesc) {
//        this.repairDesc = repairDesc;
//    }
//
//
//    public double getRepairCost() {
//        return repairCost;
//    }
//
//    public void setRepairCost(double repairCost) {
//        this.repairCost = repairCost;
//    }
//
//    public double getPartsCost() {
//        return partsCost;
//    }
//
//    public void setPartsCost(double partsCost) {
//        this.partsCost = partsCost;
//    }
//
//    public double getHourlyRate() {
//        return hourlyRate;
//    }
//
//    public void setHourlyRate(double hourlyRate) {
//        this.hourlyRate = hourlyRate;
//    }
//
//    public int getManHours() {
//        return manHours;
//    }
//
//    public void setManHours(int manHours) {
//        this.manHours = manHours;
//    }
//
//}
