package pl.coderslab.model;

public class Vehicle {

    private int id;
    private String model;
    private String make;
    private String productionDate;
    private String registration;
    private String nextService;
    private Client client;

    public Vehicle() {
    }

    public Vehicle(String model, String make, String productionDate, String registration, String nextService, Client client) {
        this.id = 0;
        setModel(model);
        setMake(make);
        setProductionDate(productionDate);
        setRegistration(registration);
        setNextService(nextService);
        setClient(client);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getNextService() {
        return nextService;
    }

    public void setNextService(String nextService) {
        this.nextService = nextService;
    }
}
