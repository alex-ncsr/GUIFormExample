/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package md.tekwill.dao;

import md.tekwill.model.Employee;

import java.util.Optional;


/**
 * @author user
 */
public interface EmployeeDao {

    void create(Employee employee);

       Employee read(int employeeId);
//    Optional<Employee> read(int employeeId);

    void update(int employeeId, Employee updatedEmployee);

    void delete(int employeeId);

    Employee[] getAll();

}
