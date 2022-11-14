/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tekwill.service;

import md.tekwill.dao.*;
import md.tekwill.exceptions.EmployeeValidationException;
import md.tekwill.model.Employee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author user
 */
public class EmployeeService {

//    private EmployeeDao employeeDao = new EmployeeDaoArrayListImpl();

    private EmployeeDao employeeDao = new EmployeeDaoMapImpl();

//    private EmployeeDaoSerializImpl employeeDaoSerializ = new EmployeeDaoSerializImpl();


    public boolean create(Employee employee) throws EmployeeValidationException {

//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println(localDateTime);

        String name = employee.getName();
        String lastname = employee.getLastName();

        if (employee.getName().isEmpty() || employee.getLastName().isEmpty()) {
            throw new EmployeeValidationException("Employee validation failed! Please insert one character at least!");
        } else if (Character.isDigit(name.charAt(0)) || Character.isDigit(lastname.charAt(0))) {
            throw new EmployeeValidationException("Employee validation failed! characters only allowed!");
        } else if (employee.getName().length() > 0 &&
                employee.getLastName().length() > 0) {
            employeeDao.create(employee);
//            employeeDaoSerializ.create(employee);
            System.out.println("Employee was created at " + timeNow());
//            ObjectOutoutStream().writeObject()
            return true;
        }
        return false;
    }

    public String timeNow (){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    public void showTimeNow() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String format = LocalDateTime.now().format(formatter);
        System.out.println(format);
    }

    public Employee read(int employeeId) {
        if (employeeDao.read(employeeId) == null) {
            System.out.println("Employee with id " + employeeId + " doesn't exist");
        }

//        employeeDaoSerializ.read(employeeId);
        return employeeDao.read(employeeId);
    }

//    public Optional<Employee> read(int employeeId) {
//        if (employeeDao.read(employeeId) == null) {
//            System.out.println("Employee with id " + employeeId + " doesn't exist");
//        }
//        return employeeDao.read(employeeId);
//    }

    public void update(int employeeId, Employee updatedEmployee) {
        updatedEmployee.setUpdatedDate(LocalDateTime.now());
        employeeDao.update(employeeId, updatedEmployee);
        System.out.println("Employee with id " + employeeId + " was updated at " + timeNow());
    }

    public void delete(int employeeId) {
        if (employeeDao.read(employeeId) == null) {
            System.out.println("Employee with id " + employeeId + " doesn't exist");
        }
        employeeDao.delete(employeeId);
        System.out.println("Employee with id " + employeeId + " was deleted");
    }

    public List<Employee> filtrationById(List<Employee> employeeList, Integer id) {
        System.out.println("Filtered by id " + id);
        List<Employee> collect = employeeList.stream()
                .filter(employee -> employee.getId() == id)
                .collect(Collectors.toList());
        System.out.println(collect);
        return collect;
    }

    public List<Employee> filtrationByName(List<Employee> employeeList, String name) {
        System.out.println("Filtered by name " + name);
        List<Employee> collect = employeeList.stream()
                .filter(employee -> employee.getName().equals(name))
                .collect(Collectors.toList());
        System.out.println(collect);
        return collect;
    }

    public List<Employee> filtrationByLastName(List<Employee> employeeList, String lastName) {
        System.out.println("Filtered by last name " + lastName);
        List<Employee> collect = employeeList.stream()
                .filter(employee -> employee.getLastName().equals(lastName))
                .collect(Collectors.toList());
        System.out.println(collect);
        return collect;
    }

//    public List<Employee> filtrationByDepartment(List<Employee> employeeList, Department department) {
//        System.out.println("Filtered by department " + department);
//        List<Employee> collect = employeeList.stream()
//                .filter(employee -> employee.getDepartment().getName().equals(department.getName()))
//                .collect(Collectors.toList());
//        return collect;
//    }

    public List<Employee> filtrationByDepartment(List<Employee> employeeList, String departmentName) {
        System.out.println("Filtered by department " + departmentName);
        List<Employee> collect = employeeList.stream()
                .filter(employee -> employee.getDepartment().getName().equals(departmentName))
                .collect(Collectors.toList());
        System.out.println(collect);
        return collect;
    }

    public void listEmployees() {
        for (Employee employee : employeeDao.getAll()) {
            if (employee != null) {
                System.out.println("ID: " + employee.getId());
                System.out.println("Name: " + employee.getName());
                System.out.println("Last name: " + employee.getLastName());
                System.out.println("Department: " + employee.getDepartment());
            }
        }
    }

    public List<Employee> getAll() {

        return Arrays.asList(employeeDao.getAll());
    }


}
