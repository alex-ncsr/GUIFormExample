package md.tekwill.dao;

import md.tekwill.jdbs.JdbcConnection;
import md.tekwill.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DepartmentDaoTableImpl implements DepartmentDao{


    private final Connection connection = JdbcConnection.createConnection();

    private static final String SQL_CREATE_DEPARTMENT = "INSERT INTO DEPARTMENTS5 (id,name) VALUES (?,?)";
    //    private static final String SQL_CREATE_DEPARTMENT = "INSERT INTO departments (name) VALUES (?)";
    private static final String SQL_GET_DEPARTMENT_BY_ID = "SELECT * FROM DEPARTMENTS5 WHERE id = ?";
    private static final String SQL_GET_DEPARTMENT_BY_NAME = "SELECT * FROM DEPARTMENTS5 WHERE NAME = ?";

    private static final String SQL_GET_ALL_DEPARTMENTS = "SELECT * FROM DEPARTMENTS5 ORDER BY id ASC";
    private static final String SQL_DELETE_DEPARTMENT = "DELETE FROM DEPARTMENTS5 WHERE id=?";



    @Override
    public void create(Department department) {

        PreparedStatement pstmt = null;
        try {
//            pstmt = connection.prepareStatement("INSERT INTO departments (id,name) VALUES (" + department.getId() + "," + department.getName() + ")");
            pstmt = connection.prepareStatement(SQL_CREATE_DEPARTMENT);
            pstmt.setInt(1, department.getId());
            pstmt.setString(2, department.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Department read(int departmentId) {

        return getDepartmentWithPrepareStatement(departmentId);
    }

    private Department getDepartmentWithPrepareStatement(int departmentId) {
        Department department = new Department();

        try (PreparedStatement pstmt = connection.prepareStatement(SQL_GET_DEPARTMENT_BY_ID)) {
            pstmt.setInt(1, departmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    department.setId(rs.getInt(1));
                    department.setName(rs.getString(2));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return department;
    }


    public Department read(String departmentName) {

        return getDepartmentWithPrepareStatementByName(departmentName);
    }

    private Department getDepartmentWithPrepareStatementByName(String departmentName) {
        Department department = new Department();

        try (PreparedStatement pstmt = connection.prepareStatement(SQL_GET_DEPARTMENT_BY_NAME)) {
            pstmt.setString(1, departmentName);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    department.setId(rs.getInt(1));
                    department.setName(rs.getString(2));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return department;
    }


    @Override
    public void update(int departmentId, Department updatedDepartment) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE departments SET name = ? WHERE ID = " + departmentId);
            preparedStatement.setString(1, updatedDepartment.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int departmentId) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(SQL_DELETE_DEPARTMENT);
            pstmt.setInt(1, departmentId);
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
    public List<Department> getAllDepartments() {

        List<Department> allDepartments = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_GET_ALL_DEPARTMENTS)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                allDepartments.add(
                        read(rs.getInt(1)));
//                        new Department(rs.getString(2)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allDepartments;
    }
}
