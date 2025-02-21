<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register Attendance</title>
    <script src="https://cdn.jsdelivr.net/npm/@tensorflow/tfjs"></script>
    <script src="https://cdn.jsdelivr.net/npm/face-api.js"></script>
</head>
<body>
    <h1>Register Attendance</h1>

    <div>
        <video id="webcam" autoplay></video>
        <canvas id="canvas" style="display:none;"></canvas>
        <button id="captureBtn" onclick="captureAndSubmit()">Capture & Submit</button>
    </div>

    <div id="message"></div>

    <script>
        const video = document.getElementById('webcam');
        const canvas = document.getElementById('canvas');
        const messageDiv = document.getElementById('message');

        async function setupCamera() {
            const stream = await navigator.mediaDevices.getUserMedia({ video: true });
            video.srcObject = stream;
        }

        async function captureAndSubmit() {
            const context = canvas.getContext('2d');
            canvas.width = video.videoWidth;
            canvas.height = video.videoHeight;
            context.drawImage(video, 0, 0, canvas.width, canvas.height);

            const imageBase64 = canvas.toDataURL('image/png');
            submitImage(imageBase64);
        }

        async function submitImage(imageBase64) {
            try {
                const response = await fetch('/attendance/register', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ capturedImage: imageBase64 })
                });

                const result = await response.json();
                messageDiv.innerText = result.message;
            } catch (error) {
                console.error('Error:', error);
                messageDiv.innerText = 'An error occurred!';
            }
        }

        setupCamera();
    </script>
</body>
</html>
