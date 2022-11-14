package md.tekwill.dao;

import md.tekwill.model.Employee;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EmployeeDaoSetImpl implements EmployeeDao {

//    private static Set<Employee> employeeSet = new LinkedHashSet<>();
    private static Set<Employee> employeeSet = new HashSet<>();

    @Override
    public void create(Employee employee) {

        employeeSet.add(employee);
    }

    @Override
    public Employee read(int employeeId) {
        //search employee by id
        for (Employee employee : employeeSet) {
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
        Employee employeeForDelete = read(employeeId);
        //remove employee from employeeSet
        employeeSet.remove(employeeForDelete);

    }

    @Override
    public Employee[] getAll() {

        return employeeSet.toArray(new Employee[0]);
    }
}
