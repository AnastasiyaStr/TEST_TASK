package com.javainuse.nastia.dao.Impl;

import com.javainuse.nastia.dao.DepartmentDAO;
import com.javainuse.nastia.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class DepartmentDaoJDBC implements DepartmentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Department department) {
        return jdbcTemplate.update(
                "insert into department (depName) values(?)",
                department.getDepName());
    }

    public List<Department> findAll() {
        String sql = "SELECT * FROM DEPARTMENT";
        List<Department> departments = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Department obj = new Department();
            obj.setDepId(((Integer) row.get("depId")).intValue());
            obj.setDepName((String) row.get("depName"));
            departments.add(obj);
        }

        return departments;
    }

    public Department findById(Integer id) {
        return jdbcTemplate.queryForObject(
                "select * from department where depId = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        new Department(
                                rs.getInt("depId"),
                                rs.getString("depName")

                        )
        );
    }

}
