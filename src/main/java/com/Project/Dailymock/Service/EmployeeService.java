package com.Project.Dailymock.Service;



import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.Project.Dailymock.Entity.Attendance;
import com.Project.Dailymock.Entity.Employee;
import com.Project.Dailymock.Repository.AttendanceRepository;
import com.Project.Dailymock.Repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	private AttendanceRepository attendanceRepository;

	// Employee Registration
	public void registerEmployee(String employeeName, String email, String password, String mobile, String gender,
			String designation, String department, String joinDate, byte[] imageBytes) throws Exception {

		Employee employee = new Employee();
		employee.setEmployeeName(employeeName);
		employee.setEmail(email);
		employee.setPassword(password);
		employee.setMobile(mobile);
		employee.setGender(gender);
		employee.setDesignation(designation);
		employee.setDepartment(department);
		employee.setJoinDate(LocalDate.parse(joinDate));
		employee.setImage(imageBytes);

// Save employee to the database
		employeeRepository.save(employee);
	}

	// Attendance Registration
//	@Transactional
//	public String registerAttendance(byte[] capturedImage) {
//		// Find a matching employee based on the captured image
//		Optional<Employee> matchedEmployee = employeeRepository.findAll().stream()
//				.filter(emp -> compareImages(emp.getImage(), capturedImage)) // Call image comparison logic
//				.findFirst();
//
//		if (matchedEmployee.isPresent()) {
//			Employee employee = matchedEmployee.get();
//
//			// Check for an existing attendance record for the current date
//			LocalDate currentDate = LocalDate.now();
//			Optional<Attendance> existingAttendance = attendanceRepository.findByEmployeeAndInTimeDate(employee,
//					currentDate);
//
//			if (existingAttendance.isPresent()) {
//				Attendance attendance = existingAttendance.get();
//
//				// Update out-time if already checked in and outTime is not yet set
//				if (attendance.getOutTime() == null) {
//					attendance.setOutTime(LocalDateTime.now());
//					attendanceRepository.save(attendance);
//					return "Out-time recorded successfully for " + employee.getEmployeeName();
//				} else {
//					return "Attendance already completed for the day.";
//				}
//			} else {
//				// Create a new attendance record with in-time only
//				Attendance attendance = new Attendance();
//				attendance.setEmployee(employee);
//				attendance.setInTime(LocalDateTime.now());
//				attendance.setOutTime(null); // Explicitly set outTime as null
//				attendance.setStatus("Present");
//				attendanceRepository.save(attendance);
//				return "In-time recorded successfully for " + employee.getEmployeeName();
//			}
//		} else {
//			return "No matching employee found. Attendance not registered.";
//		}
//	}

	@Transactional
	public String registerAttendance(byte[] capturedImage) {
		// Find a matching employee based on the captured image
		System.out.println("Starting attendance registration...");

		Optional<Employee> matchedEmployee = employeeRepository.findAll().stream()
				.filter(emp -> compareImages(emp.getImage(), capturedImage)) // Call image comparison logic
				.findFirst();

		if (matchedEmployee.isPresent()) {
			Employee employee = matchedEmployee.get();
			System.out.println("Matched employee: " + employee.getEmployeeName());

			// Check for an existing attendance record for the current date
			LocalDateTime currentDate = LocalDateTime.now();
			List<Attendance> employeeAttendances = attendanceRepository.findByEmployeeAndDate(employee, currentDate);

			if (!employeeAttendances.isEmpty()) {
				Attendance attendance = employeeAttendances.get(0);

				// Update out-time if already checked in and outTime is not yet set
				if (attendance.getOutTime() == null) {
					attendance.setOutTime(LocalDateTime.now());
					attendanceRepository.save(attendance);
					return "Out-time recorded successfully for " + employee.getEmployeeName();
				} else {
					return "Attendance already completed for the day for " + employee.getEmployeeName();
				}
			} else {
				// Create a new attendance record with in-time only
				Attendance attendance = new Attendance();
				attendance.setEmployee(employee);
				attendance.setInTime(LocalDateTime.now());
				attendance.setOutTime(null); // Explicitly set outTime as null
				attendance.setStatus("Present");
				attendanceRepository.save(attendance);
				return "In-time recorded successfully for " + employee.getEmployeeName();
			}
		} else {
			return "No matching employee found. Attendance not registered.";
		}
	}

	/**
	 * Image comparison logic using OpenCV.
	 *
	 * @param employeeImage The stored employee image as a byte array.
	 * @param capturedImage The captured image as a byte array.
	 * @return True if images are similar, otherwise false.
	 */
	private boolean compareImages(byte[] employeeImage, byte[] capturedImage) {
		// Convert byte arrays to Mat objects
		Mat employeeMat = Imgcodecs.imdecode(new MatOfByte(employeeImage), Imgcodecs.IMREAD_COLOR);
		Mat capturedMat = Imgcodecs.imdecode(new MatOfByte(capturedImage), Imgcodecs.IMREAD_COLOR);

		// Validate that the images were properly loaded
		if (employeeMat.empty() || capturedMat.empty()) {
			return false;
		}

		// Resize images to a fixed size for consistency
		Size resizeSize = new Size(300, 300); // Fixed size for comparison
		Imgproc.resize(employeeMat, employeeMat, resizeSize);
		Imgproc.resize(capturedMat, capturedMat, resizeSize);

		// Convert images to grayscale
		Mat grayEmployee = new Mat();
		Mat grayCaptured = new Mat();
		Imgproc.cvtColor(employeeMat, grayEmployee, Imgproc.COLOR_BGR2GRAY);
		Imgproc.cvtColor(capturedMat, grayCaptured, Imgproc.COLOR_BGR2GRAY);

		// Calculate histograms for both images
		Mat histEmployee = new Mat();
		Mat histCaptured = new Mat();
		Imgproc.calcHist(java.util.Collections.singletonList(grayEmployee), new MatOfInt(0), new Mat(), histEmployee,
				new MatOfInt(256), new MatOfFloat(0, 256));
		Imgproc.calcHist(java.util.Collections.singletonList(grayCaptured), new MatOfInt(0), new Mat(), histCaptured,
				new MatOfInt(256), new MatOfFloat(0, 256));

		// Normalize histograms
		Core.normalize(histEmployee, histEmployee, 0, 1, Core.NORM_MINMAX);
		Core.normalize(histCaptured, histCaptured, 0, 1, Core.NORM_MINMAX);

		// Compare histograms using correlation
		double similarity = Imgproc.compareHist(histEmployee, histCaptured, Imgproc.CV_COMP_CORREL);
		return similarity > 0.8; // Return true if similarity is above 70%
	}

	// Get all employees
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	// Get employee by ID
	public Employee getEmployeeById(Long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		return employee.orElse(null);
	}

	// Save or update employee
	public Employee saveOrUpdateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	// Delete employee by ID
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

//	public List<Attendance> getAllAttendance() {
//		return attendanceRepository.findAll();
//	}

//	public void processAttendanceExcelFile(MultipartFile file) throws Exception {
//		if (file.isEmpty()) {
//			throw new Exception("File is empty");
//		}
//
//		List<Attendance> attendances = new ArrayList<>();
//		try (InputStream inputStream = file.getInputStream(); Workbook workbook = new XSSFWorkbook(inputStream)) {
//
//			Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
//			for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip the header row
//			    Row row = sheet.getRow(i);
//
//			    Long employeeId = (long) row.getCell(0).getNumericCellValue(); // Employee ID
//			    String status = row.getCell(1).getStringCellValue(); // Attendance Status
//
//			    // Parse inTime
//			    LocalDateTime inTime = null;
//			    if (row.getCell(2).getCellType() == CellType.NUMERIC) {
//			        // Convert Excel numeric date/time to LocalDateTime
//			        inTime = row.getCell(2).getLocalDateTimeCellValue();
//			    } else if (row.getCell(2).getCellType() == CellType.STRING) {
//			        // Parse ISO-8601 formatted string to LocalDateTime
//			        inTime = LocalDateTime.parse(row.getCell(2).getStringCellValue());
//			    }
//
//			    // Parse outTime (if present)
//			    LocalDateTime outTime = null;
//			    if (row.getCell(3) != null) {
//			        if (row.getCell(3).getCellType() == CellType.NUMERIC) {
//			            outTime = row.getCell(3).getLocalDateTimeCellValue();
//			        } else if (row.getCell(3).getCellType() == CellType.STRING) {
//			            outTime = LocalDateTime.parse(row.getCell(3).getStringCellValue());
//			        }
//			    }
//
//			    Employee employee = employeeRepository.findById(employeeId)
//			            .orElseThrow(() -> new Exception("Employee not found: ID " + employeeId));
//
//			    Attendance attendance = new Attendance();
//			    attendance.setEmployee(employee);
//			    attendance.setStatus(status);
//			    attendance.setInTime(inTime);
//			    attendance.setOutTime(outTime);
//
//			    attendances.add(attendance);
//			}
//
//		}
//
//		attendanceRepository.saveAll(attendances);
//	}

	
//	public List<Attendance> getAllAttendance1() {
//        return attendanceRepository.findAll();
//    }
}
