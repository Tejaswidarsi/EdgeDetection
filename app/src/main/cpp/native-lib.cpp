#include <jni.h>
#include <opencv2/core.hpp>

extern "C"
JNIEXPORT void JNICALL
Java_com_example_edgedetection_MainActivity_processFrame(JNIEnv *env, jobject /* this */, jlong matAddrInput, jlong matAddrOutput) {
    cv::Mat& input = *(cv::Mat*)matAddrInput;
    cv::Mat& output = *(cv::Mat*)matAddrOutput;
    // Example: Simple copy (replace with edge detection logic)
    input.copyTo(output);
}