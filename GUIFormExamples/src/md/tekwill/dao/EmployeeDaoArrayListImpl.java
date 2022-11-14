package md.tekwill.dao;

import md.tekwill.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoArrayListImpl implements EmployeeDao {

    private static List<Employee> employeeList = new ArrayList<>();


    public void create(Employee employee) {

        employeeList.add(employee);

    }

    @Override
    public Employee read(int employeeId) {

        for (Employee employee : employeeList) {
            if (employee != null && employeeId == employee.getId()) {
                return employee;
            }
        }
        return null;

//       return Optional.ofNullable(employeeList.get(employeeId));

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
        //search employee by id
        for (int i = 0; i < employeeList.size(); i++) {
            Employee findEmployee = employeeList.get(i);

            if (findEmployee != null && findEmployee.getId() == employeeId) {
                employeeList.remove(i);
            }
        }

    }

    @Override
    public Employee[] getAll() {

        return employeeList.toArray(new Employee[0]);
    }
}
