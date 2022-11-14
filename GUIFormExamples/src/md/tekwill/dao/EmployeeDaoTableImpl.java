package md.tekwill.dao;

import md.tekwill.jdbs.JdbcConnection;
import md.tekwill.model.Employee;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDaoTableImpl implements EmployeeDao {

    DepartmentDaoTableImpl departmentDaoImpl = new DepartmentDaoTableImpl();

    private final Connection connection = JdbcConnection.createConnection();

    private static final String SQL_CREATE_EMPLOYEE = "INSERT INTO EMPLOYEES5 " +
            "(ID, NAME, LASTNAME, DEPARTMENT_NAME) VALUES (?,?,?,?)";
    private static final String SQL_GET_EMPLOYEE_BY_ID = "SELECT * FROM EMPLOYEES5 WHERE id = ?";
    private static final String SQL_GET_ALL_EMPLOYEES = "SELECT * FROM EMPLOYEES5 ORDER BY id ASC";
    private static final String SQL_UPDATE_EMPLOYEE = "UPDATE EMPLOYEES5 SET name = ?, LASTNAME = ?" +
            ", department_name = ? WHERE ID = ?";
    private static final String SQL_DELETE_EMPLOYEE = "DELETE FROM EMPLOYEES5 WHERE id=?";

    @Override
    public void create(Employee employee) {
//        try (PreparedStatement pstmt = connection.prepareStatement(SQL_CREATE_EMPLOYEE)) {
//            pstmt.setInt(1, employee.getId());
//            pstmt.setString(2, employee.getName());
//            pstmt.setString(3, employee.getLastName());
//            pstmt.setInt(4, 1);
//            pstmt.executeUpdate();
//            pstmt.isClosed();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try (Statement pstmt = connection.createStatement()) {
            pstmt.executeUpdate("INSERT INTO EMPLOYEES5 (ID, NAME, LASTNAME, DEPARTMENT_NAME)" +
                    " VALUES ('" + employee.getId() + "', '" + employee.getName() + "', '" + employee.getLastName() + "', '" + employee.getDepartment().getName() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Employee read(int employeeId) {

        return getEmployeeWithPrepareStatement(employeeId);
    }

    private Employee getEmployeeWithPrepareStatement(int employeeId) {

        Employee employee = new Employee();

        try (PreparedStatement pstmt = connection.prepareStatement(SQL_GET_EMPLOYEE_BY_ID)) {
            pstmt.setInt(1, employeeId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    employee.setId(employeeId); //?
                    employee.setName(rs.getString("NAME"));
                    employee.setLastName(rs.getString("LASTNAME"));
//                    String departmentName = rs.getString(4);
                    employee.setDepartment(departmentDaoImpl.read(rs.getString(4)));
//                    int depId = rs.getInt(4);
//                    Department read = departmentDaoImpl.read(depId);
//                    employee.setDepartment(read);
//                    employee.setDepartment(departmentDaoImpl.read(rs.getInt(4)));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employee;
    }

    @Override
    public void update(int employeeId, Employee updatedEmployee) {

        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employees SET name = ?, lastName = ?, department_name = ? WHERE ID = " + employeeId);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_EMPLOYEE);

            preparedStatement.setString(1, updatedEmployee.getName());
            preparedStatement.setString(2, updatedEmployee.getLastName());
            preparedStatement.setString(3, updatedEmployee.getDepartment().getName());
            preparedStatement.setInt(4, employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int employeeId) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(SQL_DELETE_EMPLOYEE);
            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public Employee[] getAll() {

        List<Employee> allEmployees = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_GET_ALL_EMPLOYEES)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Employee employeeForAdd = read(rs.getInt(1));
                employeeForAdd.setDepartment(departmentDaoImpl.read(rs.getString(4)));
                allEmployees.add(employeeForAdd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allEmployees.toArray(new Employee[0]);
    }

    public void clearTable(){
        try (Statement pstmt = connection.createStatement()) {
            pstmt.executeUpdate("TRUNCATE TABLE EMPLOYEES");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeEmployee (Employee employee){
        try (
                ObjectOutputStream outputStream = new ObjectOutputStream(
                        new FileOutputStream("employees.txt"))) {
            outputStream.writeObject(employee);
            System.out.println("Employee serialized!");
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee readEmployee () {

        Employee employee = null;

        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream("employees.txt"))){
            employee = (Employee) inputStream.readObject();
            System.out.println(employee);
            return employee;
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
