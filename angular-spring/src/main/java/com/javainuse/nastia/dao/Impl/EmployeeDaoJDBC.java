package com.javainuse.nastia.dao.Impl;

import com.javainuse.nastia.dao.EmployeeDAO;
import com.javainuse.nastia.dao.util.CustomerRowMapper;
import com.javainuse.nastia.model.Department;
import com.javainuse.nastia.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDaoJDBC implements EmployeeDAO {

    private final DepartmentDaoJDBC departmentDaoJDBC;
    private final JdbcTemplate jdbcTemplate;

    public EmployeeDaoJDBC(DepartmentDaoJDBC departmentDaoJDBC, JdbcTemplate jdbcTemplate) {
        this.departmentDaoJDBC = departmentDaoJDBC;
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean save(Employee employee) {
        jdbcTemplate.update(
                "insert into employee (name, active,depId) values(?,?,?)",
                employee.getName(), employee.getActive(), employee.getDepartment().getDepId());
        return true;
    }

    public Employee findByEmployeeId(Integer id) {

        String sql = "SELECT * FROM employee c join department d on c.depId=d.depId WHERE c.empId = ? ";
        Employee employee = null;

        employee = jdbcTemplate.queryForObject(sql, new Object[]{id}, new CustomerRowMapper());
        return employee;
    }


    public boolean update(Employee employee) {
        Integer id = employee.getEmpId();
        jdbcTemplate.update(
                "update employee set name = ?, active = ? where empId = ?",
                employee.getName(), employee.getActive(), employee.getEmpId());
        return true;
    }

    public List<Employee> findAll() {

        String sql = "SELECT * FROM employee c join department d on c.depId=d.depId";

        List<Employee> employees = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        for (Map row : rows) {
            Employee obj = new Employee();
            Department department = new Department();
            obj.setEmpId((Integer) row.get("empId"));
            obj.setName((String) row.get("NAME"));
            obj.setActive(((String) row.get("ACTIVE")));
            department.setDepId((Integer) row.get("depId"));
            department.setDepName((String) row.get("depName"));
            obj.setDepartment(department);
            employees.add(obj);
        }
        return employees;
    }

    public boolean deleteEmployeeById(Long id) {

        jdbcTemplate.update(
                "delete from employee where empId = ?",
                id);
        return true;
    }

    public List<Employee> getEmployeesByPage(int offset, int limit) {
        String sql = "SELECT * FROM employee c join department d  on c.depId=d.depId limit  ? offset ?";

        List<Employee> employees = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, limit, offset);

        for (Map row : rows) {
            Employee obj = new Employee();
            Department department = new Department();
            obj.setEmpId((Integer) row.get("empId"));
            obj.setName((String) row.get("NAME"));
            obj.setActive(((String) row.get("ACTIVE"))); // Spring returns BigDecimal, need convert
            department.setDepId((Integer) row.get("depId"));
            department.setDepName((String) row.get("depName"));
            obj.setDepartment(department);
            employees.add(obj);
        }
        return employees;
    }

    public int countEmployees() {
        String sql = "SELECT COUNT(*) FROM employee";
        return jdbcTemplate.queryForObject(sql, Integer.class);

    }


    public List<Employee> findEmployeeByName(String name) {
        return jdbcTemplate.query(
                "select * from employee where name like ?",
                new Object[]{name + "%"},
                (rs, rowNum) ->
                        new Employee(
                                rs.getString("name"),
                                rs.getString("active"),
                                departmentDaoJDBC.findById(rs.getInt("depId"))
                        )
        );
    }

}
