package com.example.nguye.seg2505app.Storables;

public class Provider extends Account {

    private String companyName;
    private String description;
    private int licensed;




    public String getCompanyName() { return this.companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }

    public int getLicensed() { return this.licensed; }
    public void setLicensed(int licensed) { this.licensed = licensed; }
}
