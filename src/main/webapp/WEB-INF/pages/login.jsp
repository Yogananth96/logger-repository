<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-start;
            min-height: 100vh;
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            background-color: #007bff; /* Header background color */
            color: white; /* Text color for the header */
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            width: 100%;
            position: fixed;
            top: 0;
            z-index: 1000;
        }

        .header-title {
            font-size: 1.5em;
            font-weight: bold;
        }

        .login-container {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            padding: 20px;
            width: 100%;
            max-width: 400px;
            margin-top: 100px; /* Ensure it is below the fixed header */
        }

        .login-container h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            color: #555;
            margin-bottom: 5px;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .form-group input:focus {
            border-color: #0056b3;
            outline: none;
        }

        .login-btn {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .login-btn:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
    <header>
        <div class="header-title">DailyMark</div>
    </header>

    <div class="login-container">
        <h2>Login</h2>

        <!-- Display error message if available -->
        <div id="error-message" class="error-message"></div>

        <form action="/api/business/businesshome" method="post" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="username">Email or Mobile:</label>
                <input type="text" id="username" name="username" required>
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>

            <button type="submit" class="login-btn">Login</button>
        </form>
    </div>

    <script>
        function validateForm() {
            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;
            const errorMessage = document.getElementById("error-message");

            // Reset error message
            errorMessage.textContent = "";

            // Validate username format
            if (!username.match(/^\S+$/)) {
                errorMessage.textContent = "Username cannot contain spaces.";
                return false;
            }

            // Validate password length
            if (password.length < 6) {
                errorMessage.textContent = "Password must be at least 6 characters long.";
                return false;
            }

            return true; // Allow form submission
        }
    </script>
</body>
</html>
