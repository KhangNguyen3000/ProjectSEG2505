package com.example.nguye.seg2505app.Bench;

import com.example.nguye.seg2505app.Storables.Account;

/**
 * This class is used to store the information of the account currently logged in.
 */
public class CurrentAccount extends Account {
    private static Account current = null;


    public static Account getCurrentAccount() {
        return current;
    }

    public static void setCurrentAccount(Account account) {
        current = account;
    }

    public static void logout() {
        current = null;
    }

}
