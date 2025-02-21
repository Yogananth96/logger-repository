<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Registration</title>
    <style>
        /* General reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fa;
            display: flex;
            justify-content: center;
            align-items: flex-start; /* Align items to the top */
            height: 100vh;
            padding: 20px;
            overflow-y: auto; /* Enable vertical scrolling */
        }

        .container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px; /* Reduced max width */
            box-sizing: border-box;
            margin: 0 auto;
            overflow-y: auto; /* Enable scrolling inside container if needed */
            max-height: 95vh; /* Limit the container height to 95% of the viewport height */
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px; /* Reduced margin */
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
            color: #444;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="date"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
            box-sizing: border-box; /* Ensure input fields take full width */
        }

        input[type="text"]:focus,
        input[type="email"]:focus,
        input[type="password"]:focus,
        input[type="date"]:focus {
            border-color: #007bff;
            outline: none;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: #fff;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #0056b3;
        }

        .capture-container {
            text-align: center;
            margin-bottom: 20px;
        }

        #videoElement {
            width: 100%;
            max-width: 300px;
            height: auto;
            border-radius: 8px;
        }

        canvas {
            display: none;
        }

        .message {
            text-align: center;
            color: green;
            margin-top: 20px;
        }

        .error {
            text-align: center;
            color: red;
            margin-top: 20px;
        }

        /* Responsive design */
        @media (max-width: 600px) {
            .container {
                width: 100%;
                padding: 20px;
            }

            button {
                font-size: 14px;
            }

            /* Ensure no horizontal scroll on small devices */
            body {
                padding: 10px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Employee Registration</h2>
        <form action="/employees/register" method="post">
            <label for="employeeName">Employee Name:</label>
            <input type="text" name="employeeName" id="employeeName" required><br>

            <label for="email">Email:</label>
            <input type="email" name="email" id="email" required><br>

            <label for="password">Password:</label>
            <input type="password" name="password" id="password" required><br>

            <label for="mobile">Mobile:</label>
            <input type="text" name="mobile" id="mobile" required><br>

            <label for="gender">Gender:</label>
            <input type="text" name="gender" id="gender" required><br>
            
            <label for="designation">Designation:</label>
            <input type="text" name="designation" id="designation" required><br>

            <label for="department">Department:</label>
            <input type="text" name="department" id="department" required><br>

            <label for="joinDate">Join Date:</label>
            <input type="date" name="joinDate" id="joinDate" required><br>

            <div class="capture-container">
                <label>Capture Photo:</label><br>
                <video id="videoElement" autoplay></video><br>
                <button type="button" onclick="captureImage()">Capture</button><br>
                <canvas id="canvasElement" width="300" height="300"></canvas>
                <input type="hidden" id="imageDataInput" name="imageData">
            </div>

            <button type="submit">Register</button>
        </form>

        <c:if test="${message != null}">
            <div class="message">${message}</div>
        </c:if>

        <c:if test="${error != null}">
            <div class="error">${error}</div>
        </c:if>
    </div>

    <script>
        // JavaScript for webcam integration
        let webcamStream;

        function startWebcam() {
            const video = document.querySelector('#videoElement');
            navigator.mediaDevices.getUserMedia({ video: true })
                .then((stream) => {
                    webcamStream = stream;
                    video.srcObject = stream;
                })
                .catch((err) => {
                    console.error("Error accessing webcam:", err);
                });
        }

        function captureImage() {
            const video = document.querySelector('#videoElement');
            const canvas = document.querySelector('#canvasElement');
            const context = canvas.getContext('2d');
            
            // Draw video frame onto canvas
            context.drawImage(video, 0, 0, canvas.width, canvas.height);

            // Get the Base64 image data
            const imageData = canvas.toDataURL('image/png');
            document.querySelector('#imageDataInput').value = imageData;

            // Stop webcam stream after capturing
            webcamStream.getTracks().forEach(track => track.stop());
        }

        // Start webcam when the page loads
        window.onload = startWebcam;
    </script>
</body>
</html>
