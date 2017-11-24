package com.tiangong.chargingPile.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/11/21.
 */

public class RotateTextView extends android.support.v7.widget.AppCompatTextView {

    public RotateTextView(Context context) {
        super(context);
    }

    public RotateTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RotateTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //倾斜度45,上下左右居中
        canvas.rotate(270, getMeasuredWidth()/2, getMeasuredHeight()/2);
        super.onDraw(canvas);
    }
}
