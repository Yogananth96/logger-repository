<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%@ page import="com.Project.Dailymock.Entity.BusinnessUserDetails" %>
<!DOCTYPE html>
<html>
<head>
    <title>Business Users</title>
    <style>
        /* Add CSS styling directly in the page or link an external stylesheet */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        header {
            background-color:  #007bff; 
            color: white; /* White text */
            padding: 10px 20px; /* Space around the content */
            text-align: left; /* Centered text */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Add a shadow for depth */
        }

        .header-title {
            font-size: 24px;
            font-weight: bold;
        }

        /* Styling for the table and page content */
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
    <header>
        <div class="header-title">DailyMark</div>
    </header>

    <!-- Existing content of the JSP page -->
    <h1>Business Users</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Mobile</th>
                <th>Role</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<BusinnessUserDetails> users = (List<BusinnessUserDetails>) request.getAttribute("users");
                for (BusinnessUserDetails user : users) {
            %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getName() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getMobile() %></td>
                <td><%= user.getRole() %></td>
            </tr>
            <% 
                }
            %>
        </tbody>
    </table>
</body>
</html>
