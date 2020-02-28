package com.javainuse.nastia.model;

import java.time.LocalDateTime;

public class Employee {

    private Integer empId;
    private String name;
    private String active;
    private Department department;

    public Employee() {
    }

    public Employee(String name, String active, Department department) {
        this.name = name;
        this.active = active;
        this.department = department;
    }

    public Employee(String name, String active) {
        this.name = name;
        this.active = active;
//        this.age = age;
    }

    public Employee(Integer empId, String name, Integer age, LocalDateTime createdDate) {
        this.empId = empId;
        this.name = name;
//        this.age = age;
//        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID=" + empId +
                ", name='" + name +
                ", active='" + active +
    //            ", departmentId='" + depId +
                ", department='" + department +
                '}';
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public LocalDateTime getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(LocalDateTime createdDate) {
//        this.createdDate = createdDate;
//    }
}
