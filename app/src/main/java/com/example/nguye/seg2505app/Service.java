package com.example.nguye.seg2505app;

public class Service {
    private int ID;
    private String name;
    private double rate;

    public Service(int ID, String name, double rate){
        this.ID = ID;
        this.name = name;
        this.rate = rate;
    }

    public int getID(){
        return ID;
    }
    public String getName(){
        return name;
    }
    public double getRate(){
        return rate;
    }
}
