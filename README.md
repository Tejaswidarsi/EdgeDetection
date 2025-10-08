# EdgeDetection (Android + Web + OpenCV + OpenGL)

A real-time Edge Detection Viewer using Android (JNI + C++ + OpenCV + OpenGL ES) and Web (TypeScript + OpenCV.js).  
Captures camera frames, processes them with edge detection, and displays results via native and web UIs.

---

## ✅ Features Implemented

### 📱 Android
- Camera feed via OpenCV’s `JavaCameraView`  
- JNI bridge to native C++ for image processing  
- Canny edge detection implemented in C++  
- Rendering processed frames via OpenGL ES 2.0  

### 🌐 Web
- Web camera capture (MediaDevices API)  
- OpenCV.js integration for edge detection in browser  
- Output rendered on HTML `<canvas>`  
- Basic FPS / resolution overlay

---

## 📷 Screenshots / GIFs

Below are preview images of the application in action:

| Android Camera Preview | Edge Detection View | Web Viewer |
|------------------------|---------------------|-------------|
| ![Camera Preview](screenshots/android_camera.png) | ![Edge Detection](screenshots/android_edge.png) | ![Web View](screenshots/web_view.png) |



---

## ⚙️ Setup Instructions

### Android

1. Install **Android Studio** (latest) with **NDK**, **CMake**, **LLDB**.  
2. Download and extract **OpenCV Android SDK**.  
3. Place OpenCV headers and `.so` libs in `app/src/main/cpp/include` and `app/src/main/jniLibs/<ABI>/`.  
4. Use `CMakeLists.txt` to import `opencv_java4` and link libraries.  
5. Sync & build the project.  
6. Grant camera permissions at runtime and run on a physical device.

### Web

1. In your `web/` folder, ensure files:
   - `index.html`  
   - `index.js` (compiled TS)  
   - `opencv.js`  
   - `index.ts`, `tsconfig.json`  
2. Launch a simple HTTP server:
   ```bash
   cd web
   npx http-server
  3. Open
     ```bash
     http://localhost:8080
  in a browser

  4. Allow camera access when prompted, and you should see live edge detection.
##  Architecture Overview

### Android Flow
```bash
Camera → Java layer (JavaCameraView) ▶ JNI (native-lib.cpp) ▶ OpenCV C++ ▶ Processed Mat ▶ GLRenderer ▶ Display via GLSurfaceView
```
- Java → C++ (JNI): Data passes as native object addresses (long).
- OpenCV C++: Performs grayscale + Canny edge detection.
- GLRenderer: Converts Mat to Bitmap, binds texture, and draws a quad using shaders.
## WebFlow 
```bash
Browser Camera → JavaScript → `cv.Mat` in OpenCV.js → `cv.Canny()` → `cv.imshow()` → Canvas Display
```
- Uses cv['onRuntimeInitialized'] to wait for the OpenCV WebAssembly runtime.
- Enables real-time edge detection directly in the browser.
