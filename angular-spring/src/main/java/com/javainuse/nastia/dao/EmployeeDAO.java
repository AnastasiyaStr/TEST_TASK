package com.javainuse.nastia.dao;


import com.javainuse.nastia.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    boolean save(Employee employee);
    Employee findByEmployeeId(Integer id);
     boolean update(Employee employee);
     List<Employee> findAll();
    boolean deleteEmployeeById(Long id);
    List<Employee> getEmployeesByPage(int offset, int limit);
    int countEmployees();
    List<Employee> findEmployeeByName(String name);
   // boolean existsById(Long id);


}
