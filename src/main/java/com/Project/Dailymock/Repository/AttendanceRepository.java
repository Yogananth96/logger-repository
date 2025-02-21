package com.Project.Dailymock.Repository;



import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Project.Dailymock.Entity.Attendance;
import com.Project.Dailymock.Entity.Employee;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	//@Query("SELECT a FROM Attendance a WHERE a.employee = :employee AND DATE(a.inTime) = :date")
//	Optional<Attendance> findByEmployeeAndInTimeDate(@Param("employee") Employee employee,
//			@Param("date") LocalDate date);

	@Query("SELECT a FROM Attendance a WHERE a.employee = :employee AND DATE(a.inTime) = DATE(:date)")
	List<Attendance> findByEmployeeAndDate(@Param("employee") Employee employee, @Param("date") LocalDateTime date);

}
