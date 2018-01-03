package com.example.zhengdong.base.Macro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;

/**
 * Created by star on 2017/12/11.
 */

public class FlashTextView extends AppCompatTextView {

    private int mViewWidth;
    private int mTranslate;
    private boolean mFlashing;

    private TextPaint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;

    public FlashTextView(Context context) {
        this(context, null);
    }

    public FlashTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = getMeasuredWidth();
        mPaint = getPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mFlashing) {
            flashText();
        }
    }

    private void flashText() {
        if (mGradientMatrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
    }

    public void setFlashing(int[] flashColors) {
        mFlashing = true;
        if (mViewWidth > 0) {
            mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0, flashColors, null, Shader.TileMode.CLAMP);
            mPaint.setShader(mLinearGradient);
            mGradientMatrix = new Matrix();
        }
        invalidate();
    }

    public void removeFlashing() {
        mFlashing = false;
        mPaint.setShader(null);
    }
}
