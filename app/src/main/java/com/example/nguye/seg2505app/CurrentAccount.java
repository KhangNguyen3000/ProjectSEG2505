package com.example.nguye.seg2505app;

public class CurrentAccount {
    Account current = null;

    public Account getCurrentAccount(){
        return current;
    }
    public Account setCurrentAccount(Account utili){
           current = utili;
    }

}
