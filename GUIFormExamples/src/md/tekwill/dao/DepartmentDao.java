package md.tekwill.dao;

import md.tekwill.model.Department;

import java.util.List;

public interface DepartmentDao {

    void create(Department department);

    Department read(int departmentId);

    void update(int departmentId, Department updatedDepartment);

    void delete(int departmentId);

    List<Department> getAllDepartments();
}
