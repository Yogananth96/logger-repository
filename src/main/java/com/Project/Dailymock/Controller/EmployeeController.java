package com.Project.Dailymock.Controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Project.Dailymock.Entity.Employee;
import com.Project.Dailymock.Service.EmployeeService;


@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	// add new employee details jsp
	@GetMapping("/register")
	public String showEmployeeRegistrationForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "employee-registeration";
	}

	// add new employee details
	@PostMapping("/register")
	public String registerEmployee(@RequestParam String employeeName, @RequestParam String email,
			@RequestParam String password, @RequestParam String mobile, @RequestParam String gender,
			@RequestParam String designation, @RequestParam String department, @RequestParam String joinDate,
			@RequestParam String imageData, Model model) {

		try {
			// Decode the Base64 image data
			byte[] imageBytes = Base64.getDecoder().decode(imageData.split(",")[1]);

			// Call the service to register the employee
			employeeService.registerEmployee(employeeName, email, password, mobile, gender, designation, department,
					joinDate, imageBytes);

			model.addAttribute("message", "Employee registered successfully!");
		} catch (Exception e) {
			model.addAttribute("error", "Error registering employee: " + e.getMessage());
		}

		return "employee-registeration";
	}
	
	
	// Show list of employees
		@GetMapping
		public String listEmployees(Model model) {
			model.addAttribute("employees", employeeService.getAllEmployees());
			return "employee-list";
		}

		// employee edit form
		@GetMapping("/edit/{id}")
		public String showEditForm(@PathVariable Long id, Model model) {
			Employee employee = employeeService.getEmployeeById(id);
			if (employee != null) {
				model.addAttribute("employee", employee);
				return "employee-edit";
			}
			return "redirect:/employees";
		}

		// Handle edit form submission
		@PostMapping("/edit")
		public String editEmployee(@ModelAttribute Employee employee) {
			employeeService.saveOrUpdateEmployee(employee);
			return "redirect:/employees";
		}

		// Delete employee
		@GetMapping("/delete/{id}")
		public String deleteEmployee(@PathVariable Long id) {
			employeeService.deleteEmployee(id);
			return "redirect:/employees";
		}


	// employee attendance jsp page
//	@GetMapping("/attendance")
//	public String showAttendanceForm() {
//		return "attendance";
//	}
//
//	// employee attendance page
//	@PostMapping("/attendance")
//	public String registerAttendance(@RequestParam("imageFile") String base64Image, Model model) {
//		if (base64Image == null || !base64Image.contains(",")) {
//			model.addAttribute("message", "Invalid image format. Please upload a valid image.");
//			return "attendance";
//		}
//		try {
//
//			// Decode Base64 image string to byte array
//			byte[] imageBytes = Base64.getDecoder().decode(base64Image.split(",")[1]);
//
//			// Call the service to register attendance
//			String status = employeeService.registerAttendance(imageBytes);
//			model.addAttribute("message", status);
//		} catch (Exception e) {
//			model.addAttribute("message", "Error processing the request. Please try again.");
//		}
//		return "attendance";
//	}

	
//	// view attendance list
//	@GetMapping("/attendance/list")
//	public String viewAttendanceList(Model model) {
//		List<Attendance> attendanceList = employeeService.getAllAttendance();
//		model.addAttribute("attendanceList", attendanceList);
//		return "attendance-list"; // JSP file name (attendance-list.jsp)
//	}

//	// upload attendance document jsp
//	@GetMapping("/upload")
//	public String showUploadPage() {
//		return "bulkAttendanceUpload";
//	}
//
//	// upload attendance method
//	@PostMapping("/upload")
//	public String uploadAttendanceFile(@RequestParam("file") MultipartFile file, Model model) {
//		try {
//			employeeService.processAttendanceExcelFile(file);
//			model.addAttribute("message", "File uploaded and data saved successfully.");
//		} catch (Exception e) {
//			model.addAttribute("message", "Error processing file: " + e.getMessage());
//		}
//		return "bulkAttendanceUpload";
//	}

//	// convert to excel jsp page
//	@GetMapping("/excelConvert")
//	public String showExcelPage() {
//		return "attendanceExport";
//	}

//	// excel conversion
//	@GetMapping("/attendance/export")
//	public ResponseEntity<byte[]> exportAttendanceToExcel(HttpServletResponse response) throws IOException {
//		List<Attendance> attendances = employeeService.getAllAttendance1();
//
//		try (Workbook workbook = new XSSFWorkbook()) {
//			Sheet sheet = workbook.createSheet("Attendance");
//
//			// Header Row
//			Row headerRow = sheet.createRow(0);
//			String[] headers = { "ID", "Employee Name", "Status", "In Time", "Out Time" };
//			for (int i = 0; i < headers.length; i++) {
//				Cell cell = headerRow.createCell(i);
//				cell.setCellValue(headers[i]);
//				cell.setCellStyle(createHeaderCellStyle(workbook));
//			}
//
//			// Data Rows
//			int rowIdx = 1;
//			for (Attendance attendance : attendances) {
//				Row row = sheet.createRow(rowIdx++);
//				row.createCell(0).setCellValue(attendance.getId());
//				row.createCell(1).setCellValue(attendance.getEmployee().getEmployeeName());
//				row.createCell(2).setCellValue(attendance.getStatus());
//				row.createCell(3).setCellValue(attendance.getInTime().toString());
//				row.createCell(4)
//						.setCellValue(attendance.getOutTime() != null ? attendance.getOutTime().toString() : "N/A");
//			}
//
//			// Write workbook to a byte array
//			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//			workbook.write(outputStream);
//
//			// Return Excel file as response
//			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=attendance.xlsx")
//					.contentType(MediaType.APPLICATION_OCTET_STREAM).body(outputStream.toByteArray());
//		}
//	}
//
//	private CellStyle createHeaderCellStyle(Workbook workbook) {
//		CellStyle style = workbook.createCellStyle();
//		Font font = workbook.createFont();
//		font.setBold(true);
//		style.setFont(font);
//		return style;
//	}

}
