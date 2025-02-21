package com.Project.Dailymock.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Project.Dailymock.Entity.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

	Optional<Employee> findById(Long employeeId);

	void deleteById(Long employeeid);

	 Optional<Employee> findByEmail(String email);

	


}
