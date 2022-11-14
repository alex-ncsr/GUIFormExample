package md.tekwill.dao;

import md.tekwill.model.Employee;

import java.util.*;

public class EmployeeDaoMapImpl implements EmployeeDao {

    private static final Map<Integer, Employee> employeeMap = new HashMap<>();

    @Override
    public void create(Employee employee) {

        employeeMap.put(employee.getId(), employee);
    }

    @Override
    public Employee read(int employeeId) {

        return employeeMap.get(employeeId);
    }

//    @Override
//    public Optional<Employee> read(int employeeId) {
//
//        return Optional.ofNullable(employeeMap.get(employeeId));
//    }

    @Override
    public void update(int employeeId, Employee updatedEmployee) {
        //search employee by id
//        Employee findEmployee = read(employeeId);
        Employee findEmployee = employeeMap.get(employeeId);

        // update data from updatedEmployee to the old employee from employeeSet
        if (findEmployee != null) {
            findEmployee.setName(updatedEmployee.getName());
            findEmployee.setLastName(updatedEmployee.getLastName());
            findEmployee.setDepartment(updatedEmployee.getDepartment());
        }
    }

    @Override
    public void delete(int employeeId) {
        //search employee by id
//        Employee employeeForDelete = read(employeeId);
        //remove employee from employeeSet
//        employeeMap.remove(employeeForDelete.getId());
        employeeMap.remove(employeeId);

        getAll();
    }

    @Override
    public Employee[] getAll() {

        return employeeMap.values().toArray(new Employee[0]);
    }
}
