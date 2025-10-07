declare var cv: any;

cv['onRuntimeInitialized'] = () => {
  console.log("✅ OpenCV.js is ready!");

  const video = document.getElementById("videoInput") as HTMLVideoElement;
  const canvas = document.getElementById("canvasOutput") as HTMLCanvasElement;

  navigator.mediaDevices.getUserMedia({ video: true })
    .then(stream => {
      video.srcObject = stream;
      video.play();
      processVideo();
    })
    .catch(err => {
      console.error("Camera error:", err);
    });

  let cap: any, src: any, gray: any, edges: any;

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
};
