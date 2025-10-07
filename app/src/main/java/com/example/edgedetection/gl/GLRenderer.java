package com.example.edgedetection.gl;

import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.graphics.Bitmap;
import org.opencv.core.Mat;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView;
public class GLRenderer implements GLSurfaceView.Renderer {

    private Mat currentFrame;
    private Bitmap bitmap;
    private int[] textureId = new int[1];
    private boolean frameUpdated = false;

    public synchronized void updateFrame(Mat frame) {
        if (frame != null) {
            currentFrame = frame.clone();
            frameUpdated = true;
        }
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glGenTextures(1, textureId, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId[0]);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        if (frameUpdated && currentFrame != null) {
            if (bitmap == null || bitmap.getWidth() != currentFrame.cols() || bitmap.getHeight() != currentFrame.rows()) {
                bitmap = Bitmap.createBitmap(currentFrame.cols(), currentFrame.rows(), Bitmap.Config.ARGB_8888);
            }
            org.opencv.android.Utils.matToBitmap(currentFrame, bitmap);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId[0]);
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
            frameUpdated = false;
        }
        // TODO: Add code to render a quad with the texture
    }
}