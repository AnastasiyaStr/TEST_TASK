package com.javainuse.nastia.controller;

import com.javainuse.nastia.dao.EmployeeDAO;
import com.javainuse.nastia.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin()
@RestController
@RequestMapping("employee")
public class EmployeeController {
     private static final Integer limit = 10;

    private final EmployeeDAO employeeDao;

    public EmployeeController(EmployeeDAO employeeDao) {
        this.employeeDao = employeeDao;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        System.out.println(employeeDao.findAll());
        return employeeDao.findAll();

    }

    @GetMapping(path = {"/{id}"})
    @ResponseBody
    public Employee findEmployeeById(@PathVariable("id") Integer id) {

        return employeeDao.findByEmployeeId(id);
    }


    @PostMapping
    @ResponseBody
    public List<Employee> create(@RequestBody Employee employee) {
        employeeDao.save(employee);
        return employeeDao.findAll();

    }

    @DeleteMapping(path = {"/{id}"})
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        employeeDao.deleteEmployeeById(id);

    }

    @PutMapping
    @ResponseBody
    public List<Employee> update(@RequestBody Employee employee) {
        employeeDao.update(employee);
        return employeeDao.findAll();

    }

    @GetMapping("/page")
    @ResponseBody
    public List<Employee> getEmployeesByPage(@RequestParam Integer page) {
        return employeeDao.getEmployeesByPage(page, limit);
    }

    @GetMapping(path = "/count")
    @ResponseBody
    public Integer getEmployeeCount() {
        return employeeDao.countEmployees();

    }

    @GetMapping("/search")
    @ResponseBody
    public List<Employee> getEmployeeByPage(@RequestParam String name) {
        return employeeDao.findEmployeeByName(name);
    }


}
