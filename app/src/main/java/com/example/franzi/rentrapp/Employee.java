package com.example.franzi.rentrapp;

public class Employee {

    private int age;
    private String department;
    private String jobPosition;
    private int employmentPeriod;

    public void setAge(int age){
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void setDepartment (String department){
        this.department = department;
    }

    public String getDepartment(){
        return this.department;
    }

    public void setJobPosition(String jobPosition){
        this.jobPosition = jobPosition;
    }

    public String getJobPosition (){
        return this.jobPosition;
    }

    public void setEmploymentPeriod(int employmentPeriod){
        this.employmentPeriod = employmentPeriod;
    }

    public int getEmploymentPeriod() {
        return this.employmentPeriod;
    }
}
