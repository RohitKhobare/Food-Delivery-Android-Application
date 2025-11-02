package com.example.myapplication.models;

public class Information {
    private String name,phno;

    public Information() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public Information( String name, String phno) {
        this.name = name;
        this.phno = phno;
    }
}
