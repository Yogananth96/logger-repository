<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register Attendance</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        color: #333;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100vh;
    }

    h2 {
        color: #444;
        margin-bottom: 20px;
    }

    div {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-bottom: 20px;
    }

    video {
        border: 2px solid #ddd;
        border-radius: 8px;
        width: 100%;
        max-width: 400px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    canvas {
        display: none;
    }

    button {
        background-color: #007BFF;
        color: #fff;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
        margin: 10px 0;
        transition: background-color 0.3s ease;
    }

    button:hover {
        background-color: #0056b3;
    }

    form {
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 100%;
    }

    input[type="hidden"] {
        display: none;
    }

    p {
        font-size: 14px;
        color: #28a745;
        margin-top: 10px;
    }
</style>
    
</head>
<body>
<h2>Register Attendance</h2>
<div>
    <video id="webcam" autoplay playsinline></video>
    <canvas id="canvas" style="display:none;"></canvas>
</div>
<button id="capture">Capture Image</button>
<form id="attendanceForm" action="/employees/attendance" method="post" enctype="multipart/form-data">
    <input type="hidden" name="imageFile" id="imageFile">
    <button type="submit">Register Attendance</button>
</form>
<c:if test="${message != null}">
    <p>${message}</p>
</c:if>

<script>
    const video = document.getElementById('webcam');
    const canvas = document.getElementById('canvas');
    const captureButton = document.getElementById('capture');
    const imageInput = document.getElementById('imageFile');

    // Access webcam
    navigator.mediaDevices.getUserMedia({ video: true })
        .then(stream => {
            video.srcObject = stream;
        })
        .catch(error => {
            console.error('Error accessing the webcam:', error);
        });

    // Capture image from webcam
    captureButton.addEventListener('click', () => {
        const context = canvas.getContext('2d');
        canvas.width = video.videoWidth;
        canvas.height = video.videoHeight;
        context.drawImage(video, 0, 0, canvas.width, canvas.height);

        // Convert the captured image to a Base64 string
        const imageData = canvas.toDataURL('image/png');

        // Set the hidden input field with the image data
        imageInput.value = imageData;
        alert("Image captured! Click 'Register Attendance' to save.");
    });
</script>
</body>
</html>
    