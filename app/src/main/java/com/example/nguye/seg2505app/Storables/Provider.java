package com.example.nguye.seg2505app.Storables;

public class Provider extends Account {

    private String companyName;
    private String description;
    private boolean licensed;




    public String getCompanyName() { return this.companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isLicensed() { return this.licensed; }
    public void setLicensed(boolean licensed) { this.licensed = licensed; }
}
