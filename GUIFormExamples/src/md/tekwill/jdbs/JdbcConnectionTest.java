package md.tekwill.jdbs;

import md.tekwill.dao.DepartmentDaoTableImpl;
import md.tekwill.dao.EmployeeDaoTableImpl;
import md.tekwill.model.Department;
import md.tekwill.model.Employee;

public class JdbcConnectionTest {

    public static void main(String[] args) {
        JdbcConnection.createConnection();

        DepartmentDaoTableImpl departmentDaoTable = new DepartmentDaoTableImpl();

//        EmployeeDaoTableImpl employeeDaoTable = new EmployeeDaoTableImpl();
//
        Department dep1 = new Department("IT");
//        Department dep2 = new Department("Sales");
//        Department dep3 = new Department("Manegement");
//        Department dep4 = new Department("Logistic");
//
        departmentDaoTable.create(dep1);
//        departmentDaoTable.create(dep2);
//        departmentDaoTable.create(dep3);
//        departmentDaoTable.create(dep4);

//        System.out.println(departmentDaoTable.getAllDepartments());

//        System.out.println(departmentDaoTable.read(1));
//
//        System.out.println(departmentDaoTable.getAllDepartments());
//        System.out.println(departmentDaoTable.read(1));
//
//
//        departmentDaoTable.create(dep2);

//        Employee emp1 = new Employee("Alex", "Belii");
//        emp1.setDepartment(dep4);
//        employeeDaoTable.create(emp1);
//
//        Employee emp2 = new Employee("Tom", "Cruise");
//        emp2.setDepartment(dep2);
//        employeeDaoTable.create(emp2);
//
//        Employee emp3 = new Employee("Roma", "Cernii");
//        emp3.setDepartment(dep3);
//        employeeDaoTable.create(emp3);

    }
}
