package com.example.levente.opengl_app;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Square {

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";




    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;

    private final int mProgram;

    static final int COORDS_PER_VERTEX = 3;
    private static float squareCoords[] = {
            -0.5f,  0.5f, 0.0f,   // top left
            -0.5f, -0.5f, 0.0f,   // bottom left
            0.5f, -0.5f, 0.0f,   // bottom right
            0.5f,  0.5f, 0.0f  // top right
    };
    private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices

    private int mPositionHandle;
    private int mColorHandle;


    float color[] = { 1, 0, 0, 1.0f };


    public Square(){
        ByteBuffer bb = ByteBuffer.allocate(squareCoords.length*4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);

        ByteBuffer bb2 = ByteBuffer.allocate(drawOrder.length*2);
        bb2.order(ByteOrder.nativeOrder());
        drawListBuffer = bb2.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);



        int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);


        // create empty OpenGL ES Program
        mProgram = GLES20.glCreateProgram();

        // add the vertex shader to program
        GLES20.glAttachShader(mProgram, vertexShader);

        // add the fragment shader to program
        GLES20.glAttachShader(mProgram, fragmentShader);

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(mProgram);


    }
    public void draw(){

        // Add program to OpenGL ES environment
        GLES20.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

            // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

            // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX*2,
               GLES20.GL_FLOAT, false,
               COORDS_PER_VERTEX*4, vertexBuffer);

            // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

            // Set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

            // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, squareCoords.length);

            // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }

}
