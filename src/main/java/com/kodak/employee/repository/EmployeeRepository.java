package com.kodak.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodak.employee.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	List<Employee> findEmployeeByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String firstName,String lastName);
}
