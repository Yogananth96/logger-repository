<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.Project.Dailymock.Entity.Employee" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Employee</title>
    <style>
        /* General Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fc;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            width: 100%;
            max-width: 900px;
            background: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            box-sizing: border-box;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
            color: #444;
        }

        .form-group input,
        .form-group select {
            width: 100%;
            padding: 12px;
            border-radius: 4px;
            border: 1px solid #ccc;
            font-size: 16px;
            margin-top: 6px;
        }

        .form-group input[type="date"] {
            padding: 8px;
        }

        .form-group select {
            background-color: #f9f9f9;
        }

        .form-buttons {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 20px;
        }

        .form-buttons button,
        .form-buttons a {
            padding: 12px 20px;
            font-size: 16px;
            border-radius: 4px;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .form-buttons button {
            background-color: #4CAF50;
            color: #fff;
            border: none;
        }

        .form-buttons a {
            background-color: #ccc;
            color: #333;
            border: none;
            display: inline-block;
            text-align: center;
        }

        .form-buttons a:hover,
        .form-buttons button:hover {
            opacity: 0.8;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .container {
                width: 100%;
                padding: 15px;
            }

            .form-group {
                margin-bottom: 15px;
            }

            .form-buttons button,
            .form-buttons a {
                width: 100%;
                font-size: 14px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit Employee</h1>
        <form action="/employees/edit" method="post">
            <% 
                Employee employee = (Employee) request.getAttribute("employee");
                if (employee != null) { 
            %>
            <input type="hidden" name="employeeid" value="<%= employee.getEmployeeid() %>" />

            <div class="form-group">
                <label for="employeeName">Name:</label>
                <input type="text" id="employeeName" name="employeeName" value="<%= employee.getEmployeeName() %>" required />
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="<%= employee.getEmail() %>" required />
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="<%= employee.getPassword() %>" required />
            </div>

            <div class="form-group">
                <label for="mobile">Mobile:</label>
                <input type="text" id="mobile" name="mobile" value="<%= employee.getMobile() %>" required />
            </div>

            <div class="form-group">
                <label for="gender">Gender:</label>
                <select id="gender" name="gender" required>
                    <option value="Male" <%= "Male".equals(employee.getGender()) ? "selected" : "" %>>Male</option>
                    <option value="Female" <%= "Female".equals(employee.getGender()) ? "selected" : "" %>>Female</option>
                </select>
            </div>

            <div class="form-group">
                <label for="designation">Designation:</label>
                <input type="text" id="designation" name="designation" value="<%= employee.getDesignation() %>" required />
            </div>

            <div class="form-group">
                <label for="department">Department:</label>
                <input type="text" id="department" name="department" value="<%= employee.getDepartment() %>" required />
            </div>

            <div class="form-group">
                <label for="joinDate">Join Date:</label>
                <input type="date" id="joinDate" name="joinDate" value="<%= employee.getJoinDate() %>" required />
            </div>

            <div class="form-buttons">
                <button type="submit">Update</button>
                <a href="/employees">Cancel</a>
            </div>

            <% 
                } else { 
            %>
            <p>Employee data is not available.</p>
            <a href="/employees">Go back to the list</a>
            <% 
                } 
            %>
        </form>
    </div>
</body>
</html>
