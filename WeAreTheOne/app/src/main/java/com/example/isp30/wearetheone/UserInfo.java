package com.example.isp30.wearetheone;

public class UserInfo {
    private static String name;
    private String email;
    public UserInfo(){

    }
    public UserInfo(String name){
        this.name = name;
    }
    public UserInfo(String name, String email){
        this.name = name;
        this.email = email;

    }
    public String getUserInfo(){
        return this.name;
    }

    public String getEmail() {
        return email;
    }
}


