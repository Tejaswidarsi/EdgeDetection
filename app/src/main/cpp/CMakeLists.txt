cmake_minimum_required(VERSION 3.10.2)
project("edgedetection")

# Include OpenCV headers
include_directories(${CMAKE_SOURCE_DIR}/include)
include_directories(C:/OpenCV-android-sdk/sdk/native/jni/include)

# Create native library
add_library(native-lib SHARED native-lib.cpp)

# Import prebuilt OpenCV shared library (.so)
add_library(opencv_java4 SHARED IMPORTED)

set_target_properties(opencv_java4 PROPERTIES
        IMPORTED_LOCATION C:/OpenCV-android-sdk/sdk/native/libs/${ANDROID_ABI}/libopencv_java4.so
)

# Link required libraries
find_library(log-lib log)
find_library(android-lib android)

target_link_libraries(
        native-lib
        opencv_java4
        ${log-lib}
        ${android-lib}
)
