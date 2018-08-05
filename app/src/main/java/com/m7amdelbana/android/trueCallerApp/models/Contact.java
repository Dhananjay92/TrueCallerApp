package com.m7amdelbana.android.trueCallerApp.models;

import java.io.Serializable;

public class Contact implements Serializable {

    private int ID;
    private String name;
    private String company;
    private String phone;
    private String notes;

    public Contact(int ID, String name, String company, String phone, String notes) {
        this.ID = ID;
        this.name = name;
        this.company = company;
        this.phone = phone;
        this.notes = notes;
    }

    public Contact(String name, String company, String phone, String notes) {
        this.name = name;
        this.company = company;
        this.phone = phone;
        this.notes = notes;
    }

    public Contact(String string) {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
