<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.Project.Dailymock.Entity.Employee" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee List</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-top: 20px;
            font-size: 36px;
        }

        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 30px;
            background-color: white;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #007bff;
            color: white;
            font-size: 18px;
        }

        td {
            font-size: 16px;
            color: #555;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .actions a {
            text-decoration: none;
            color: #007bff;
            margin: 0 10px;
            font-size: 14px;
        }

        .actions a:hover {
            color: #0056b3;
        }

        .actions a.delete {
            color: #d9534f;
        }

        .actions a.delete:hover {
            color: #c9302c;
        }

        .no-employees {
            text-align: center;
            font-size: 18px;
            color: #999;
            padding: 20px;
        }
    </style>
</head>
<body>
    <h1>Employee List</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Mobile</th>
                <th>Gender</th>
                <th>Designation</th>
                <th>Department</th>
                <th>Join Date</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Employee> employees = (List<Employee>) request.getAttribute("employees");
                if (employees != null) {
                    for (Employee employee : employees) { 
            %>
            <tr>
                <td><%= employee.getEmployeeid() %></td>
                <td><%= employee.getEmployeeName() %></td>
                <td><%= employee.getEmail() %></td>
                <td><%= employee.getMobile() %></td>
                <td><%= employee.getGender() %></td>
                <td><%= employee.getDesignation() %></td>
                <td><%= employee.getDepartment() %></td>
                <td><%= employee.getJoinDate() %></td>
                <td>
                    <a href="employees/edit/<%= employee.getEmployeeid() %>">Edit</a> | 
                    <a href="employees/delete/<%= employee.getEmployeeid() %>" onclick="return confirm('Are you sure you want to delete this employee?');">Delete</a>
                </td>
            </tr>
            <% 
                    }
                } else { 
            %>
            <tr>
                <td colspan="9">No employees found.</td>
            </tr>
            <% 
                } 
            %>
        </tbody>
    </table>
</body>
</html>
    