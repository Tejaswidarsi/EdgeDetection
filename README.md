Edge Detection App

Welcome to the Edge Detection App! This project demonstrates real-time edge detection using OpenCV on an Android device, with processed frames rendered using OpenGL ES 2.0. The app leverages native code via JNI for performance optimization and includes potential support for a web interface using TypeScript (to be expanded in future iterations).

## Features Implemented (Android + Web)

### Android Features
- **Real-Time Camera Feed**: Utilizes OpenCV's `JavaCameraView` to capture video frames from the device camera.
- **Edge Detection**: Implements edge detection processing via a native C++ function called through JNI, applied to each camera frame.
- **OpenGL Rendering**: Displays processed frames using OpenGL ES 2.0, with texture mapping for efficient rendering.
- **Lifecycle Management**: Properly handles `onResume`, `onPause`, and `onDestroy` to manage camera and OpenGL resources.
- **Cross-Platform Potential**: Designed with a structure that can be extended to web platforms.

### Web Features 
- **Web Interface**: Planned integration of a TypeScript-based web application to view or control the edge detection process remotely.
- **Real-Time Streaming**: Potential feature to stream processed frames to a web browser using a canvas element.
- **Responsive Design**: Aims to provide a responsive UI using modern web technologies.
