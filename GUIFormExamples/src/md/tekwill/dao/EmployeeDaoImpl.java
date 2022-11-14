/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package md.tekwill.dao;

import md.tekwill.model.Employee;

import java.util.Optional;

/**
 *
 * @author user
 */
public class EmployeeDaoImpl implements EmployeeDao{
    
    private static final Employee[] employeesArr = new Employee[10];

    @Override
    public void create(Employee employee) {
        for (int i = 0; i < employeesArr.length; i++) {
            Employee existingEmployee = employeesArr[i];
            if (existingEmployee == null) {
                employeesArr[i] = employee;
                break;
            }
        }
    }

    @Override
    public Employee read(int employeeId) {
        //search employee by id
        for (Employee searchingEmployee : employeesArr) {
            if (searchingEmployee != null && searchingEmployee.getId() == employeeId) {
                return searchingEmployee;
            }
        }
        return null;
    }

//    @Override
//    public Optional<Employee> read(int employeeId) {
//
//        Optional<Employee[]> optionalEmployees = Optional.ofNullable(employeesArr);
//        optionalEmployees.filter(el->el.g)
//
//    }

    @Override
    public void update(int employeeId, Employee updatedEmployee) {
        //search employee by id
        Employee findEmployee = read(employeeId);

        // update data from updatedEmployee to the old employee from employeesArr
        if (findEmployee != null) {
            findEmployee.setName(updatedEmployee.getName());
            findEmployee.setLastName(updatedEmployee.getLastName());
            findEmployee.setDepartment(updatedEmployee.getDepartment());
        }
    }

    @Override
    public void delete(int employeeId) {
        //search employee by id
        Employee findEmployee = read(employeeId);
        // set to null
        employeesArr[findEmployee.getId() - 1] = null;
    }

    @Override
    public Employee[] getAll() {

        return employeesArr;
    }
}
