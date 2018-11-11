package com.example.nguye.seg2505app;

/**
 * This class is used to store the information of the account currently logged in.
 */
public class CurrentAccount {
    private static Account current = new Account();

    public static Account getCurrentAccount(){
        return current;
    }

    public static void setCurrentAccount(Account account){
        current = account;
    }

}
