package com.example.nguye.seg2505app;

public class CurrentAccount {
    private static Account current = null;

    public static Account getCurrentAccount(){
        return current;
    }

    public static void setCurrentAccount(Account account){
        current = account;
    }

}
