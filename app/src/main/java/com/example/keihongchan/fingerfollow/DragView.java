package com.example.keihongchan.fingerfollow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * DragView
 * 自定义ImageView
 *
 * @author keihong.chan
 * @time 2017/10/25 下午8:08
 * @desc
 * @others
 */

public class DragView extends android.support.v7.widget.AppCompatImageView {

    //记录前一个坐标
    private int lastX;
    private int lastY;

    //Java构造函数
    public DragView(Context context) {
        super(context);
    }

    //Java构造函数
    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //Java构造函数
    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 触摸事件
     *
     * @param event 代表当前触摸的参数
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //匹配当前触摸事件类型
        switch (event.getAction()) {
            //按下
            case MotionEvent.ACTION_DOWN:
                //记录初始坐标
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            //移动
            case MotionEvent.ACTION_MOVE:
                //记录新坐标
                int newX = (int) event.getRawX();
                int newY = (int) event.getRawY();
                //偏移量
                int dx = newX - lastX;
                int dy = newY - lastY;

                //计算View控件的新布局位置（上下左右）
                int left = getLeft() + dx;
                int top = getTop() + dy;
                int right = left + getWidth();
                int bottom = top + getHeight();
                //设置布局位置（该函数为父类定义的函数，子类继承后，可以直接使用，这是面向对象的一种用途）
                layout(left, top, right, bottom);
                //将新的坐标作为上一个坐标，用于下一次计算偏移
                lastX = newX;
                lastY = newY;
                break;
            //离开屏幕
            case MotionEvent.ACTION_UP:
                //不作操作
                break;
            default:
                break;
        }
        //返回值为true，告诉系统已经处理了该触摸事件
        return true;
    }


}
