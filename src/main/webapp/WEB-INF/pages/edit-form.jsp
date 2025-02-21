<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dailymark - Edit User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }
        header {
            background-color: #4CAF50;
            color: white;
            padding: 10px 0;
            text-align: center;
            font-size: 24px;
            font-weight: bold;
        }
        .container {
            width: 40%;
            margin: 40px auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin: 10px 0 5px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"],
        input[type="email"],
        input[type="number"],
        input[type="password"] {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
        }
        button {
            padding: 10px;
            margin-top: 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .cancel {
            background-color: #dc3545;
            margin-top: 10px;
        }
        .cancel:hover {
            background-color: #b02a37;
        }
    </style>
</head>
<body>
<header>Dailymark</header>
<div class="container">
    <h2>Edit User Details</h2>
    <form action="/api/business/edit" method="post">
        <input type="hidden" name="id" value="${user.id}">
        
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${user.name}" required>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${user.email}" required>
        
        <label for="mobile">Mobile:</label>
        <input type="number" id="mobile" name="mobile" value="${user.mobile}" required>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="${user.password}" required>
        
        <label for="role">Role:</label>
        <input type="text" id="role" name="role" value="${user.role}" required>
        
        <button type="submit">Save Changes</button>
        <a href="/users">
            <button type="button" class="cancel">Cancel</button>
        </a>
    </form>
</div>
</body>
</html>
    