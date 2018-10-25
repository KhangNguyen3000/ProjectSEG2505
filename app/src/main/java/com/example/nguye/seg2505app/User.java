package com.example.pc1.livrable1;

public class User {
    private String userName;
    private String password;
    private String telephone;
    private String email;
    public User(String userName,String password, String telephone, String email ){
        this.userName = userName;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
    }
    public boolean Credentials(String userName,String password ){
        return((this.userName==userName)&&(this.password==password));
    }
}
