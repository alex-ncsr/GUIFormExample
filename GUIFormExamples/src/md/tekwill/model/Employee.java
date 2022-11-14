/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tekwill.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.time.LocalDateTime.now;

/**
 * @author user
 */
public class Employee implements Serializable {

    private int id;
    private String name;
    private String lastName;
    private Department department;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private static int idGenerator;


    public Employee() {
        generateId();
        generateDateTime();
    }

    public Employee(String name, String lastName) {
        this();
        this.name = name;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;

    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    private void generateId() {
        idGenerator++;
        id = idGenerator;
    }

    public void generateDateTime() {
        createdDate = now();
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", lastName=" + lastName + ", department=" + department + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }
}
