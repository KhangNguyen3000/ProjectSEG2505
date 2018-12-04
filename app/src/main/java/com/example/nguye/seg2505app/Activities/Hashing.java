package com.example.nguye.seg2505app.Activities;

public class Hashing {

    static int hash(String s){
        int hash = 7;
        for(int i = 0; i < s.length(); i++){
            hash = hash*31 + s.charAt(i);
        }
        return hash;
    }
}
