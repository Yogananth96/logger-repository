<!DOCTYPE html>
<html>
<head>
    <title>Add Business Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 40%;
            margin: 50px auto;
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        form {
            margin: 0;
        }
        label {
            display: block;
            margin: 15px 0 5px;
            color: #333;
        }
        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            width: 100%;
            padding: 10px;
            background: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Add Business Details</h2>
        <form action="/api/business/savebusiness" method="post" modelAttribute="BusinessDetails">
            <label for="businessname">Business Name:</label>
            <input type="text" id="businessname" name="businessname" required="true">

            <label for="state">State:</label>
            <input type="text" id="state" name="state" required>

            <label for="city">City:</label>
            <input type="text" id="city" name="city" required>

            <label for="sector">Sector:</label>
            <input type="text" id="sector" name="sector" required>

            <label for="subsector">Sub-sector:</label>
            <input type="text" id="subsector" name="subsector" required>

            <label for="noofemployees">Number of Employees:</label>
            <input type="number" id="noofemployees" name="noofemployees" required>

            <!-- Hidden field to pass userId -->
            <input type="hidden" name="userId" value="${userId}">

            <button type="submit">Save Business Details</button>
        </form>
    </div>
</body>
</html>
