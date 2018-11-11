package com.example.nguye.seg2505app;

/**
 * Service types established by the administrator.
 * The providers will be able to offer one or more of those services
 */
public class ServiceType {
    private int ID;
    private String name;
    private double rate; // Max hourly rate of the service. The provider will not be able to set a higher hourly rate.

    // Constructors 1
    public ServiceType(String name, double rate){
        this.name = name;
        this.rate = rate;
    }

    // Constructors 2
    public ServiceType(int ID, String name, double rate){
        this.ID = ID;
        this.name = name;
        this.rate = rate;
    }

    // Getters
    public int getID(){ return ID; }
    public String getName(){ return name; }
    public double getRate(){ return rate; }

    // Setters
    public void setID(int ID) { this.ID = ID; }
    public void setName(String name) { this.name = name; }
    public void setRate(double rate) { this.rate = rate; }
}
