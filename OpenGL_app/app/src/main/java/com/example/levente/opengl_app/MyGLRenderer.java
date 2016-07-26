package com.example.levente.opengl_app;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.example.levente.opengl_app.Utils.Matrix4;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class MyGLRenderer implements GLSurfaceView.Renderer {

    private Triangle myTriangle;
    private Triangle myTriangle2;

    private Sphere mySphere;

    float radius =1;
    short a = 12;
    short sector = 24;

    float mWidth = 1;
    float mHeight = 1;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        float triangleCoords[] = {
                0.0f,  0.5f, 0.0f, // top
                -0.5f, -0.2f, 0.0f, // bottom left
                0.5f, -0.3f, 0.0f  // bottom right

        };
        float triangleCoords2[] = {
                -0.6f,  0.0f, 0.0f, // top
                -0.1f, 0.8f, 0.0f, // bottom left
                0.3f, -0.3f, 0.0f  // bottom right

        };
        myTriangle = new Triangle(triangleCoords);
        myTriangle2 = new Triangle(triangleCoords2);
        mySphere = new Sphere(radius, a, sector);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        mWidth = width;
        mHeight = height;
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
          //myTriangle.draw();
          //myTriangle2.draw();
        Matrix4 m4 = new Matrix4();
        m4.scale(1f, mWidth/mHeight, 1f);
        mySphere.draw(m4);
    }

    public static int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
}
