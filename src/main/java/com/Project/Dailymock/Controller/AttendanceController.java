package com.Project.Dailymock.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Project.Dailymock.Entity.Attendance;
import com.Project.Dailymock.Service.AttendanceService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
	
	@Autowired
	AttendanceService attendanceService;
	
	@GetMapping("/attendance")
	public String showAttendanceForm() {
		return "attendance";
	}

	// employee attendance page
	@PostMapping("/attendance")
	public String registerAttendance(@RequestParam("imageFile") String base64Image, Model model) {
		if (base64Image == null || !base64Image.contains(",")) {
			model.addAttribute("message", "Invalid image format. Please upload a valid image.");
			return "attendance";
		}
		try {

			// Decode Base64 image string to byte array
			byte[] imageBytes = Base64.getDecoder().decode(base64Image.split(",")[1]);

			// Call the service to register attendance
			String status = attendanceService.registerAttendance(imageBytes);
			model.addAttribute("message", status);
		} catch (Exception e) {
			model.addAttribute("message", "Error processing the request. Please try again.");
		}
		return "attendance";
	}


	// view attendance list
		@GetMapping("/attendance/list")
		public String viewAttendanceList(Model model) {
			List<Attendance> attendanceList = attendanceService.getAllAttendance();
			model.addAttribute("attendanceList", attendanceList);
			return "attendance-list"; // JSP file name (attendance-list.jsp)
		}
		
		// upload attendance document jsp
		@GetMapping("/upload")
		public String showUploadPage() {
			return "bulkAttendanceUpload";
		}

		// upload attendance method
		@PostMapping("/upload")
		public String uploadAttendanceFile(@RequestParam("file") MultipartFile file, Model model) {
			try {
				attendanceService.processAttendanceExcelFile(file);
				model.addAttribute("message", "File uploaded and data saved successfully.");
			} catch (Exception e) {
				model.addAttribute("message", "Error processing file: " + e.getMessage());
			}
			return "bulkAttendanceUpload";
		}
		
		// convert to excel jsp page
		@GetMapping("/excelConvert")
		public String showExcelPage() {
			return "attendanceExport";
		}
		// excel conversion
		@GetMapping("/attendance/export")
		public ResponseEntity<byte[]> exportAttendanceToExcel(HttpServletResponse response) throws IOException {
			List<Attendance> attendances = attendanceService.getAllAttendance1();

			try (Workbook workbook = new XSSFWorkbook()) {
				Sheet sheet = workbook.createSheet("Attendance");

				// Header Row
				Row headerRow = sheet.createRow(0);
				String[] headers = { "ID", "Employee Name", "Status", "In Time", "Out Time" };
				for (int i = 0; i < headers.length; i++) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(headers[i]);
					cell.setCellStyle(createHeaderCellStyle(workbook));
				}

				// Data Rows
				int rowIdx = 1;
				for (Attendance attendance : attendances) {
					Row row = sheet.createRow(rowIdx++);
					row.createCell(0).setCellValue(attendance.getId());
					row.createCell(1).setCellValue(attendance.getEmployee().getEmployeeName());
					row.createCell(2).setCellValue(attendance.getStatus());
					row.createCell(3).setCellValue(attendance.getInTime().toString());
					row.createCell(4)
							.setCellValue(attendance.getOutTime() != null ? attendance.getOutTime().toString() : "N/A");
				}

				// Write workbook to a byte array
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				workbook.write(outputStream);

				// Return Excel file as response
				return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=attendance.xlsx")
						.contentType(MediaType.APPLICATION_OCTET_STREAM).body(outputStream.toByteArray());
			}
		}

		private CellStyle createHeaderCellStyle(Workbook workbook) {
			CellStyle style = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setBold(true);
			style.setFont(font);
			return style;
		}
}
