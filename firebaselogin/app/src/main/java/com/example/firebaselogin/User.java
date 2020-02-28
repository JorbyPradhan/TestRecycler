package com.example.firebaselogin;

public class User {
    private String userName;
    private String id;
    private String Password;
    public User() {
    }

    public User(String id,String userName, String password) {
        this.id = id;
        this.userName = userName;
        Password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
