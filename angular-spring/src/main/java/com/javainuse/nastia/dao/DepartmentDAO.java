package com.javainuse.nastia.dao;

import com.javainuse.nastia.model.Department;


import java.util.List;
import java.util.Optional;

public interface DepartmentDAO {
    int save(Department department);
    List<Department> findAll();
    Department findById(Integer id);
}
