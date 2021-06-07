package com.collegeapp.developerss.android;

/**
 * Created by shobhitsagar on 21/01/18.
 */

public class getData {

    String id;
    String user;
    String department;
    String batch;
    String year;
    String email;
    String name;

    public getData() {
        super();
    }

    public getData(String name, String id, String user, String department, String batch, String year, String email, String name1) {
        this.name = name;
        this.id = id;
        this.user = user;
        this.department = department;
        this.batch = batch;
        this.year = year;
        this.email = email;
        this.name = name1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
