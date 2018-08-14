package pl.coderslab.model;

public class Status {

    private int id;
    private String name;

    public Status() {
    }

    public Status(String name) {
        setName(name);
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
