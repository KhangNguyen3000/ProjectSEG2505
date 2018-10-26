package com.example.nguye.seg2505app;

public class Account {
    private String email;
    private int type;
    private String firstName;
    private String lastName;
    private int streetNumber;
    private String streetName;
//    private String apartment;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private long phoneNumber;
    private String password;

    // Constructor
    public Account(){

    }

    // Setters and getters
    public void setEmail(String email){this.email = email;}
    public void setType(int type){this.type = type;}
    public void setFirstName(String firstName){this.firstName = firstName;}
    public void setLastName(String lastName){this.lastName = lastName;}
    public void setStreetNumber(int streetNumber){this.streetNumber = streetNumber;}
    public void setStreetName(String streetName){this.streetName = streetName;}
//    public void setApartment(String appartment){this.apartment = appartment;}
    public void setCity(String city){this.city = city;}
    public void setProvince(String province){this.province = province;}
    public void setCountry(String country){this.country = country;}
    public void setPostalCode(String postalCode){this.postalCode = postalCode;}
    public void setPhoneNumber(long phoneNumber){this.phoneNumber = phoneNumber;}
    public void setPassword(String password){this.password = password;}
    public String getEmail(){return this.email;}
    public int getType(){return this.type;}
    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}
    public int getStreetNumber(){return this.streetNumber;}
    public String getStreetName(){return this.streetName;}
//    public String getApartment(){return this.apartment;}
    public String getCity(){return this.city;}
    public String getProvince(){return this.province;}
    public String getCountry(){return this.country;}
    public String getPostalCode(){return this.postalCode;}
    public long getPhoneNumber(){return this.phoneNumber;}
    public String getPassword(){return this.password;}

}
