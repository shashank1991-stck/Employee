package com.kodak.employee.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kodak.employee.entity.Employee;
import com.kodak.employee.repository.EmployeeRepository;

@Configuration
public class LoadDatabase {

	@Bean
	public CommandLineRunner loadData(EmployeeRepository repository) {
		final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	    return (args) -> {
	        repository.save(new Employee("Bill", "Gates","bill.gates@kotak.com"));
	        repository.save(new Employee("Mark", "Zuckerberg","mark.zuckerberg@kotak.com"));
	        repository.save(new Employee("Sundar", "Pichai","sundar.pichai@kotak.com"));
	        repository.save(new Employee("Jeff", "Bezos","jeff.bezos@kotak.com"));
	        
	        log.info("Employee Data Preloaded");
	    };
	}
}
//Inetialize table with Data 
