<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DailyMark</title>
   <style type="text/css">
  /* General Styling */
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f8f8f8;
    color: #333;
}

/* Header Styling */
header ul {
    display: flex;
    gap: 10px;
}

header ul li {
    margin: 0;
}

header a {
    font-size: 1em;
    font-weight: bold;
    color: #007bff;
    text-decoration: none;
}

header a:hover {
    text-decoration: underline;
}

a {
    transition: background-color 0.3s, color 0.3s;
}

a:hover {
    background-color: #0056b3;
}

header a:nth-child(1) {
    background-color: #007bff; /* Login button */
    color: white;
    padding: 8px 15px;
    border-radius: 5px;
}

header a:nth-child(1):hover {
    background-color: #0056b3;
}

header a:nth-child(2) {
    background-color: #28a745; /* Sign-Up button */
    color: white;
    padding: 8px 15px;
    border-radius: 5px;
}

header a:nth-child(2):hover {
    background-color: #218838;
}

/* Footer Styling */
footer {
    border-top: 1px solid #ddd;
    background-color: #f1f1f1;
    color: #666;
    padding: 20px 0;
}

footer p {
    margin: 5px 0;
}
  
   
   </style>
</head>
<body>
    <!-- Header Section -->
    <header style="display: flex; justify-content: space-between; align-items: center; padding: 10px 20px; background-color: #fff; box-shadow: 0 2px 5px rgba(0,0,0,0.1);">
        <div style="font-size: 1.5em; font-weight: bold;">DailyMark</div>
        <div>
            <!-- Separate Login and Signup Buttons -->
            <a href="/api/business/login" style="padding: 8px 15px; background-color: #007bff; color: white; text-decoration: none; border-radius: 5px; margin-right: 10px;">Login</a>
            <!-- <a href="signup.jsp" style="padding: 8px 15px; background-color: #28a745; color: white; text-decoration: none; border-radius: 5px;">Sign Up</a> -->
        </div>
    </header>

    <!-- Main Content Section -->
    <main style="text-align: center; margin: 50px auto; width: 80%;">
        <h1 style="font-size: 2.5em; font-weight: bold;">DailyMark Lagao, Business Badhao</h1>
        <p style="margin: 20px 0; font-size: 1.2em; color: #666;">
            Manage your staff attendance, salary, payments and compliances in few clicks
        </p>
        <a href="/api/business/register" style="display: inline-block; padding: 10px 20px; font-size: 1em; background-color: #007bff; color: white; border-radius: 5px; text-decoration: none;">
            Sign Up
        </a>
       
    </main>
    <!-- Footer Section -->
    <footer style="text-align: center; padding: 20px; background-color: #f1f1f1; margin-top: 50px; font-size: 0.9em; color: #666;">
        <p>&copy; 2024 DailyMark. All rights reserved.</p>
        <p>Terms & Conditions | Privacy Policy</p>
    </footer>
</body>
</html>
