package com.example.custview2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
public class MyView extends View {
    Paint paint = new Paint();


    public MyView(Context context) {
        //手动创建View
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //XML文件中创建View
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);//设置是否填充

//        //画圆
//        drawCircle(canvas);
         //饼状图
        drawbing(canvas);

    }

    private void drawL(Canvas canvas) {
    }

    private void drawbing(Canvas canvas) {
        paint.setColor(Color.BLACK);
        RectF rectF = new RectF();
        paint.setStrokeWidth(13);
        rectF.top =(getHeight()-getWidth())/2;
        rectF.left= 0;

        rectF.right = getWidth();
        rectF.bottom =getWidth()+(getHeight()-getWidth())/2;

        int sweep = 0;

        for (int i = 0; i <12 ; i++) {
            canvas.drawArc(rectF,sweep,30,false,paint);
            sweep =sweep+30;
        }

//
//        canvas.drawArc(rectF,(float)(360*0.3),(float)(360*0.4),true,paint);
//
//        canvas.drawArc(rectF,(float)(360*0.7),(float)(360*0.3),true,paint);


//        canvas.drawCircle(getWidth()/2,getHeight()/2,200,paint);
//        Path path = new Path();
        paint.setStrokeWidth(2);


        paint.setTextSize(30);
        int num = 12;
        int time = -90;
        String str = ".";
        int z =300;
        int h = getWidth()/2+23;
        for (int i = 0; i < 12; i++) {
            Path path = new Path();
            path.addArc(rectF,time,30);
            if(num==12){
                str ="  .";
            }
            canvas.drawTextOnPath(String.valueOf(num),path,0,50,paint);
            canvas.drawTextOnPath(str,path,0,70,paint);


            if(num==9||num==10||num==11){
                str ="  .";
            }
            num= num %12+1;

            time += 30;

            Log.i("zzzzzz", String.valueOf(time));
        }



    }

    private void drawCircle(Canvas canvas) {
        paint.setColor(Color.BLUE);//设置画笔的颜色
        canvas.drawCircle(getWidth()/2,getHeight()/2,200,paint);
    }


}
