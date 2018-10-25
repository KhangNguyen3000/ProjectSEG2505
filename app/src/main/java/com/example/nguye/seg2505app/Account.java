package com.example.nguye.seg2505app;

public class Account {
    private String email;
    private int type;
    private String firstName,lastName;
    private int streetNumber;
    private String streetName;
    private String appartment;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private String password;
    public Account(){

    }
    public void setEmail(String email){this.email = email;}
    public void setName(String name, String lastName){this.firstName = name; this.lastName = lastName;}
    public void setStreetNumber(int streetNumber){this.streetNumber = streetNumber;}
    public void setStreetName(String streetName){this.streetName = streetName;}
    public void setAppartment(String appartment){this.appartment = appartment;}
    public void setCity(String city){this.city = city;}
    public void setProvince(String province){this.province = province;}
    public void setCountry(String country){this.country = country;}
    public void setPostalCode(String postalCode){this.postalCode = postalCode;}
    public void setPassword(String password){this.password = password;}
    public String getEmail(){return email;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public int getStreetNumber(){return streetNumber;}
    public String getStreetName(){return streetName;}
    public String getAppartment(){return appartment;}
    public String getCity(){return city;}
    public String getProvince(){return province;}
    public String getCountry(){return country;}
    public String getPostalCode(){return postalCode;}
    public String getPassword(){return password;}

}
