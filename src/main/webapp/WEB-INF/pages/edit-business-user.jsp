<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Business User</title>
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
    <h1>Edit Business User</h1>
    <form action="/api/business/update" method="post">
        <input type="hidden" name="id" value="${user.id}"><br>
        <label>Name:</label>
        <input type="text" name="name" value="${user.name}"><br>
        <label>Email:</label>
        <input type="email" name="email" value="${user.email}"><br>
        <label>Mobile:</label>
        <input type="text" name="mobile" value="${user.mobile}"><br>
        <label>Password:</label>
        <input type="password" name="password" value="${user.password}"><br>
        <label>Role:</label>
        <input type="text" name="role" value="${user.role}"><br>
        <button type="submit">Update</button>
    </form>
</body>
</html>
    