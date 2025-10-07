package com.example.edgedetection;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.opengl.GLSurfaceView;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import com.example.edgedetection.gl.GLRenderer;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private CameraBridgeViewBase cameraView;
    private GLSurfaceView glView;
    private GLRenderer glRenderer;
    private Mat matInput, matOutput;

    static {
        System.loadLibrary("opencv_java4");
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // OpenCV initialization
        if (!OpenCVLoader.initDebug()) {
            throw new RuntimeException("Unable to load OpenCV!");
        }

        // Camera setup
        cameraView = findViewById(R.id.cameraView);
        cameraView.setVisibility(SurfaceView.VISIBLE);
        cameraView.setCvCameraViewListener(this);

        // OpenGL setup
        glView = findViewById(R.id.glView);
        glView.setEGLContextClientVersion(2); // OpenGL ES 2.0
        glRenderer = new GLRenderer();
        glView.setRenderer(glRenderer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.enableView();
        glView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.disableView();
        glView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraView.disableView();
    }

    // OpenCV camera callbacks
    @Override
    public void onCameraViewStarted(int width, int height) {}

    @Override
    public void onCameraViewStopped() {}

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        matInput = inputFrame.rgba();
        matOutput = new Mat();

        // Process frame in native code
        processFrame(matInput.getNativeObjAddr(), matOutput.getNativeObjAddr());

        // TODO: Pass matOutput to GLRenderer for OpenGL rendering
        glRenderer.updateFrame(matOutput);

        return matOutput; // for preview (optional)
    }

    // JNI native function
    public native void processFrame(long matAddrInput, long matAddrOutput);
}