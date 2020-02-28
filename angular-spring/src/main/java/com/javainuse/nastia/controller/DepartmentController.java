package com.javainuse.nastia.controller;

import com.javainuse.nastia.dao.DepartmentDAO;
import com.javainuse.nastia.model.Department;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("employee")
public class DepartmentController {
    private final DepartmentDAO departmentDao;

    public DepartmentController(DepartmentDAO departmentDao) {
        this.departmentDao = departmentDao;
    }

    @GetMapping("/departments")
    @ResponseBody
    public List<Department> getAllDepartments() {
        return departmentDao.findAll();
    }
}
