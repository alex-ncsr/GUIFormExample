package md.tekwill.dao;

import md.tekwill.model.Employee;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoLinkedListImpl implements EmployeeDao{

    private static List<Employee> employeeLinkedList = new LinkedList<>();

    public void create(Employee employee) {

        employeeLinkedList.add(employee);
    }

    @Override
    public Employee read(int employeeId) {
        for (Employee employee : employeeLinkedList) {
            if (employee != null && employeeId == employee.getId()) {
                return employee;
            }
        }
        return null;
    }

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

        for (int i = 0; i < employeeLinkedList.size(); i++) {
            Employee findEmployee = employeeLinkedList.get(i);
            if (findEmployee != null && findEmployee.getId() == employeeId) {
                employeeLinkedList.remove(i);
            }
        }
    }

    @Override
    public Employee[] getAll() {

        return employeeLinkedList.toArray(new Employee[0]);
    }
}
