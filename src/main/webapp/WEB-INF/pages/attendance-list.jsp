<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Attendance List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            color: #333;
        }
        h1 {
            text-align: center;
            margin-top: 20px;
            color: #444;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }
        table thead {
            background-color: #007bff;
            color: white;
        }
        table th, table td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        table th {
            font-weight: bold;
        }
        table tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        table tbody tr:hover {
            background-color: #e6f7ff;
        }
        .no-data {
            text-align: center;
            font-size: 18px;
            color: #777;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1>Attendance List</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Employee</th>
                <th>Status</th>
                <th>In Time</th>
                <th>Out Time</th>
            </tr>
        </thead>
        <tbody>
            <% 
                java.util.List<com.Project.Dailymock.Entity.Attendance> attendanceList = 
                    (java.util.List<com.Project.Dailymock.Entity.Attendance>) request.getAttribute("attendanceList");
                if (attendanceList != null) {
                    for (com.Project.Dailymock.Entity.Attendance attendance : attendanceList) {
            %>
            <tr>
                <td><%= attendance.getId() %></td>
                <td><%= attendance.getEmployee().getEmployeeName() %></td> <!-- Replace 'getName()' with the appropriate method -->
                <td><%= attendance.getStatus() %></td>
                <td><%= attendance.getInTime() %></td>
                <td><%= attendance.getOutTime() %></td>
            </tr>
            <% 
                    }
                } else { 
            %>
            <tr>
                <td colspan="5">No attendance records found.</td>
            </tr>
            <% 
                } 
            %>
        </tbody>
    </table>
</body>
</html>
