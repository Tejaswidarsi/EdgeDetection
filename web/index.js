"use strict";
function onOpenCvReady() {
    console.log("OpenCV.js is ready");
    var video = document.getElementById("videoInput");
    var canvas = document.getElementById("canvasOutput");
    var ctx = canvas.getContext("2d");
    navigator.mediaDevices.getUserMedia({ video: true }).then(function (stream) {
        video.srcObject = stream;
        video.play();
        processVideo();
    }).catch(function (err) {
        console.error("Camera error:", err);
    });
    var cap, src, gray, edges;
    function processVideo() {
        if (!video.videoWidth) {
            requestAnimationFrame(processVideo);
            return;
        }
        if (!cap) {
            cap = new cv.VideoCapture(video);
            src = new cv.Mat(video.height, video.width, cv.CV_8UC4);
            gray = new cv.Mat(video.height, video.width, cv.CV_8UC1);
            edges = new cv.Mat(video.height, video.width, cv.CV_8UC1);
        }
        cap.read(src);
        cv.cvtColor(src, gray, cv.COLOR_RGBA2GRAY);
        cv.Canny(gray, edges, 50, 150);
        cv.imshow("canvasOutput", edges);
        requestAnimationFrame(processVideo);
    }
}
