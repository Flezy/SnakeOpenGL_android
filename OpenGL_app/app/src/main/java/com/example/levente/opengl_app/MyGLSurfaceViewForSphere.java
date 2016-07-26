package com.example.levente.opengl_app;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Created by levente on 2016.06.05..
 */
public class MyGLSurfaceViewForSphere extends GLSurfaceView {

    private MyGLRendererForSphere mRenderer;

    private float mDownX = 0.0f;
    private float mDownY = 0.0f;

    public MyGLSurfaceViewForSphere(Context context) {
        super(context);

        mRenderer = new MyGLRendererForSphere();
        this.setRenderer(mRenderer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                return true;
            case MotionEvent.ACTION_UP:
                return true;
            case MotionEvent.ACTION_MOVE:
                float mX = event.getX();
                float mY = event.getY();
                mRenderer.mLightX += (mX-mDownX)/10;
                mRenderer.mLightY -= (mY-mDownY)/10;
                mDownX = mX;
                mDownY = mY;
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}