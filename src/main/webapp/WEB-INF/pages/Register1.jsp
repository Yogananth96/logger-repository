<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>
 <h2>Register</h2>
    <form action="/register" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required /><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required /><br>
        <label for="role">Role:</label>
        <select id="role" name="role" required>
            <option value="USER">User</option>
            <option value="ADMIN">Admin</option>
            <option value="OWNER">Owner</option>
        </select><br>
        <button type="submit">Register</button>
    </form>
    <p><a href="/login">Login</a></p>
</body>
</html>