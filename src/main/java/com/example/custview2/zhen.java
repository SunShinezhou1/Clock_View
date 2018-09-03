package com.example.custview2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class zhen extends View {
    Paint paint = new Paint();
    public zhen(Context context) {
        super(context);
    }

    public zhen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);//设置是否填充

//        //画圆
//        drawCircle(canvas);
            //饼状图

            drawL(canvas);
        }

        private void drawL(Canvas canvas) {
            canvas.drawLine(getWidth()/2+17,getHeight()/2,getWidth()/2+17,300,paint);
            canvas.rotate(180);
            canvas.restore();
        }

    }
