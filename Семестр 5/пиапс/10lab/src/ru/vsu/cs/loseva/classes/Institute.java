package ru.vsu.cs.loseva.classes;

public class Institute {
    private String name;
    private String address;
    private ResearchAssociate[] employees;

    public Institute(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ResearchAssociate[] getEmployees() {
        return this.employees;
    }

    public void setEmployees(ResearchAssociate[] employees) {
        this.employees = employees;
    }

    public void hire(ResearchAssociate employee) {
        throw new UnsupportedOperationException();
    }

    public void fire(ResearchAssociate employee) {
        throw new UnsupportedOperationException();
    }
}
