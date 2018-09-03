package com.example.custview2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class Clock_View extends View {
    private Paint paint;
    private int hours,minute,seconds;//时,分,秒
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //1.得到系统时间
            getTime();

            //3.发送请求  延时1秒
            handler.sendEmptyMessageDelayed(1,1000);
        }
    };

    public Clock_View(Context context) {
        super(context);
    }

    public Clock_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getTime();//获取系统当前时间

        handler.sendEmptyMessageDelayed(1,1000);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画笔
        paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setTextSize(30);
        paint.setStrokeWidth(5);
        //绘制 钟表圆心
        canvas.drawCircle(getWidth()/2,getHeight()/2,5,paint);
        //绘制表盘
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2-10,paint);
        //绘制刻度
        paint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < 12; i++) {
            //保存画布的状态
            canvas.save();
            //旋转画布---- 旋转的角度，中心点X，中心点Y
            canvas.rotate(360/12*(i+1),getWidth()/2,getHeight()/2);
            //绘制刻度
            canvas.drawLine(getWidth()/2,getHeight()/2-getWidth()/2+30 ,getWidth()/2,(getHeight()-getWidth())/2+50,paint);

            //绘制数字
            canvas.drawText((i+1)+"",getWidth()/2-10,getHeight()/2-getWidth()/2+80,paint);


            //恢复画布的状态
            canvas.restore();
            //绘制
        }
        //绘制分钟的刻度 -- 6度绘制一个线
        for (int i = 0; i < 60; i++) {
            //保存画布的状态
            canvas.save();
            //旋转画布  ---旋转的角度，中心点X，中心点Y
            canvas.rotate(360/60*(i+1),getWidth()/2,getHeight()/2);
            //绘制刻度
            canvas.drawLine(getWidth()/2,getHeight()/2-getWidth()/2+10,
                    getWidth()/2,getHeight()/2-getWidth()/2+30,paint);
            //绘制数字
//            paint.setTextSize(20);
//            canvas.drawText((i+1)+"",getWidth()/2-60,getHeight()/2-getWidth()/2+80,paint);
            //恢复画布的状态
            canvas.restore();
        }
        //绘制时针
        canvas.save();
        canvas.rotate(360/12*hours+minute*0.5f,getWidth()/2,getHeight()/2);
        paint.setStrokeWidth(8);
        canvas.drawLine(getWidth()/2,getHeight()/2,
                getWidth()/2,getHeight()/2-getHeight()/10,paint);
        canvas.restore();
        //绘制分针
        canvas.save();
        canvas.rotate(360/60*minute,getWidth()/2,getHeight()/2);
        paint.setStrokeWidth(5);
        canvas.drawLine(getWidth()/2,getHeight()/2,
                getWidth()/2,getHeight()/2-getWidth()/5,paint);
        canvas.restore();
        //绘制秒针
        canvas.save();
        canvas.rotate(360/60*seconds,getWidth()/2,getHeight()/2);
        paint.setStrokeWidth(3);
        canvas.drawLine(getWidth()/2,getHeight()/2,
                getWidth()/2,getHeight()/2-getHeight()/4,paint);
        canvas.restore();
    }

    public void getTime(){
        Calendar calendar=Calendar.getInstance();//日历类
        hours=calendar.get(Calendar.HOUR);
        minute=calendar.get(Calendar.MINUTE);
        seconds=calendar.get(Calendar.SECOND);
    }
}
