package com.javainuse;

import com.javainuse.nastia.model.Employee;
import com.javainuse.nastia.dao.Impl.EmployeeDaoJDBC;
import com.javainuse.nastia.model.Department;
import com.javainuse.nastia.dao.Impl.DepartmentDaoJDBC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class SpringBootHelloWorldApplication implements CommandLineRunner {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	EmployeeDaoJDBC customerDaoJDBC;
	@Autowired
	DepartmentDaoJDBC departmentDaoJDBC;
	private static final Logger log = LoggerFactory.getLogger(SpringBootHelloWorldApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHelloWorldApplication.class, args);
	}
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.javainuse.nastia")).build();
	}
	@Override
	public void run(String... args) { startCustomerApp();}

	void startCustomerApp() {
		jdbcTemplate.execute("DROP TABLE IF EXISTS employee ");
		jdbcTemplate.execute("DROP TABLE  IF EXISTS department");
		jdbcTemplate.execute("CREATE TABLE department(" +
				"depId  int NOT NULL auto_increment  PRIMARY KEY, depName VARCHAR(255))");

		jdbcTemplate.execute("CREATE TABLE employee(" +
				"empId  int NOT NULL auto_increment  PRIMARY KEY, name VARCHAR(255), active varchar(255), depId int,FOREIGN KEY (depId) REFERENCES department(depId))");


		List<Department> list1 = Arrays.asList(
				new Department("Department A"),
				new Department("Department B"),
				new Department("Department C")
		);

		list1.forEach(x -> {
			departmentDaoJDBC.save(x);
		});
		list1 = departmentDaoJDBC.findAll();
		List<Employee> list = Arrays.asList(
				new Employee("Customer A", "YES",list1.get(0)),
				new Employee("Customer B", "NO",list1.get(2)),
				new Employee("Customer C", "YES",list1.get(1)),
				new Employee("Customer D", "NO",list1.get(1)),
				new Employee("Customer A", "YES",list1.get(0)),
				new Employee("Customer B", "NO",list1.get(2)),
				new Employee("Customer C", "YES",list1.get(1)),
				new Employee("Customer D", "NO",list1.get(1)),
				new Employee("Customer A", "YES",list1.get(0)),
				new Employee("Customer B", "NO",list1.get(2)),
				new Employee("Customer C", "YES",list1.get(1)),
				new Employee("Customer D", "NO",list1.get(1)),
				new Employee("Customer A", "YES",list1.get(0)),
				new Employee("Customer B", "NO",list1.get(2)),
				new Employee("Customer C", "YES",list1.get(1)),
				new Employee("Customer D", "NO",list1.get(1)),
				new Employee("Customer A", "YES",list1.get(0)),
				new Employee("Customer B", "NO",list1.get(2)),
				new Employee("Customer C", "YES",list1.get(1)),
				new Employee("Customer D", "NO",list1.get(1))
		);
		list.forEach(x -> {
			customerDaoJDBC.save(x);
		});

	}
}
