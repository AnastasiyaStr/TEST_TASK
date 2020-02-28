package com.javainuse.nastia.dao.util;

import com.javainuse.nastia.model.Employee;
import com.javainuse.nastia.model.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        Department department = new Department();
        department.setDepId(rs.getInt("depId"));
        department.setDepName(rs.getString("depName"));
        employee.setEmpId(rs.getInt("empId"));
        employee.setName(rs.getString("NAME"));
        employee.setActive(rs.getString("ACTIVE"));
        employee.setDepartment(department);
        return employee;
    }


}
