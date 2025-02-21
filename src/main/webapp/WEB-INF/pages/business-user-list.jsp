<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%@ page import="com.Project.Dailymock.Entity.BusinnessUserDetails" %>
    
<!DOCTYPE html>
<html>
<head>
    <title>Business User List</title>
   <style type="text/css">
   body {
    font-family: Arial, sans-serif;
}

.header-title {
    background-color: #4CAF50;
    color: white;
    padding: 10px;
    text-align: center;
    font-size: 24px;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

table, th, td {
    border: 1px solid black;
}

th, td {
    padding: 10px;
    text-align: center;
}

h1 {
    text-align: center;
    margin-top: 20px;
}

form {
    margin: 20px auto;
    width: 300px;
}

label {
    display: block;
    margin: 10px 0 5px;
}

input, button {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
}

button {
    background-color: #4CAF50;
    color: white;
    border: none;
    cursor: pointer;
}

button:hover {
    background-color: #45a049;
}
   </style>
</head>
<body>
    <header>
        <div class="header-title">DailyMark</div>
    </header>
    <h1>Business Users</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        <% for (BusinnessUserDetails user : (List<BusinnessUserDetails>) request.getAttribute("users")) { %>
        <tr>
            <td><%= user.getId() %></td>
            <td><%= user.getName() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getMobile() %></td>
            <td><%= user.getRole() %></td>
            <td>
                <a href="/api/business/edit/<%= user.getId() %>">Edit</a> |
                <a href="/api/business/delete/<%= user.getId() %>" onclick="return confirm('Are you sure?');">Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>
    