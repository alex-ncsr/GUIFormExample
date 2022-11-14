package md.tekwill.dao;

import md.tekwill.model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoSerializImpl implements EmployeeDao{

    @Override
    public void create(Employee employee) {

        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream("employees.txt"))) {
            outputStream.writeObject(employee);
            System.out.println("Employee serialized!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Employee read(int employeeId) {

        Employee employee = null;

        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream("employees.txt"))) {

            employee = (Employee) inputStream.readObject();
            System.out.println("Employee deserialized!");
            System.out.println(employee);
            return employee;
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }


//        return employee;
    }

    @Override
    public void update(int employeeId, Employee updatedEmployee) {

    }

    @Override
    public void delete(int employeeId) {

    }

    @Override
    public Employee[] getAll() {
        return new Employee[0];
    }
}
