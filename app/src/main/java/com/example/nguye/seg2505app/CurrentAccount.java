package com.example.nguye.seg2505app;

import com.example.nguye.seg2505app.Storables.Account;

/**
 * This class is used to store the information of the account currently logged in.
 */
public class CurrentAccount extends Account {
    private static Account current = null;

//    static int ID;
//    static String email;
//    static int type;
//    static String firstName;
//    static String lastName;
//    static int streetNumber;
//    static String streetName;
//    static String city;
//    static String province;
//    static String country;
//    static String postalCode;
//    static String phoneNumber;
//    static String password;

    public static Account getCurrentAccount() {
        return current;
    }

    public static void setCurrentAccount(Account account) {
        current = account;
    }

//    public static void login(Account account) {
//        ID =  account.getID();
//        email = account.getEmail();
//        type = account.getType();
//        firstName = account.getFirstName();
//        lastName = account.getLastName();
//        streetNumber = account.getStreetNumber();
//        streetName = account.getStreetName();
//        city = account.getCity();
//        province = account.getProvince();
//        country = account.getCountry();
//        postalCode = account.getPostalCode();
//        phoneNumber = account.getPhoneNumber();
//        password = account.getPassword();
//    }

    public static void logout() {
        current = null;
    }

}
