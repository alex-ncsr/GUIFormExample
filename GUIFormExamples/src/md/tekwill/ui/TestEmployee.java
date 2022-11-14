/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tekwill.ui;

import md.tekwill.dao.EmployeeDaoMapImpl;
import md.tekwill.model.Employee;
import md.tekwill.service.EmployeeService;
import md.tekwill.exceptions.EmployeeValidationException;

import java.util.Arrays;

/**
 * @author user
 */
public class TestEmployee {
    public static void main(String[] args) throws EmployeeValidationException {
        Employee employee = new Employee("Ion", "Bostan");
        Employee employee2 = new Employee("Vasile", "Grosu");
        Employee employee3 = new Employee("Alex", "Fomin");
        Employee emp4 = new Employee("Donald", "Trump");
        Employee employee5 = new Employee("", "belii");

        EmployeeService employeeService = new EmployeeService();
        EmployeeDaoMapImpl employeeDaoMap = new EmployeeDaoMapImpl();

        boolean isEmployeeSaved = employeeService.create(employee5);
        System.out.println(isEmployeeSaved);
//        employeeDaoMap.create(employee2);
//        employeeDaoMap.create(employee3);
//        employeeDaoMap.create(emp4);
//        employeeDaoMap.create(employee3);
//
//        System.out.println(Arrays.toString(employeeDaoMap.getAll()));
//
//        System.out.println("*************************");
//        System.out.println(employeeService.read(3));
//        System.out.println(employeeService.read(5));
//
//        System.out.println("*************************");
//        employeeService.update(2, new Employee("Tom", "Cruise"));
//        employeeService.listEmployees();
//
//        System.out.println("*************************");
//        employeeService.delete(1);
//        employeeService.listEmployees();
//
//        Department dep1 = new Department("IT");
//        Department dep2 = new Department("Sales");
//
//        employee.setDepartment(dep2);
//        System.out.println(employee);
//
//        employee2.setDepartment(dep1);
//        System.out.println(employee2);
//
//        Employee emp4 = new Employee("Donald", "Trump");
//
//        System.out.println(emp4);


    }
}
