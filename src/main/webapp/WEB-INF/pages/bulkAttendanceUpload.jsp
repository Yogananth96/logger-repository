<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Bulk Attendance Upload</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            padding: 20px;
            border-radius: 8px;
            width: 400px;
            text-align: center;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 10px;
        }

        input[type="file"] {
            margin-bottom: 20px;
            padding: 8px;
            font-size: 14px;
        }

        button {
            background-color: #007BFF;
            color: #ffffff;
            border: none;
            padding: 10px 15px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        #message {
            color: #d9534f;
            margin-top: 15px;
        }
    </style>
</head>
<body>
    <h2>Upload Attendance File</h2>
    <form action="${pageContext.request.contextPath}/employees/upload" method="post" enctype="multipart/form-data">
        <label for="file">Select Excel File (.xlsx):</label>
        <input type="file" name="file" id="file" accept=".xlsx" required>
        <button type="submit">Upload</button>
    </form>
    <p id="message">
        <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
    </p>
</body>
</html>
    