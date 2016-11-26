package momo.com.week7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 显示SideView内容的自定义view
 */
public class SlideLetterView extends View {

    //画笔
    Paint paint;
    //要绘制的文本
    String text;
    //文字宽、高
    int textW;
    int textH;
    //矩形
    RectF rf;

    public SlideLetterView(Context context) {
        super(context);
        init();
    }

    public SlideLetterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private  void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(200);

        textW = (int) paint.measureText("热门");
        textH = (int) (paint.descent()-paint.ascent());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasureWH(widthMeasureSpec,1),getMeasureWH(heightMeasureSpec,2));


        //设置矩形区域
        rf = new RectF(0,0,getMeasuredWidth(),getMeasuredHeight());
    }

    private int getMeasureWH(int wh,int type){
        int mode = MeasureSpec.getMode(wh);
        int size = MeasureSpec.getSize(wh);
        //根据模式，计算大小
        switch (mode){
            case MeasureSpec.EXACTLY:
            case MeasureSpec.UNSPECIFIED:
                return size;
            case MeasureSpec.AT_MOST:{
                if(type==1){
                    //如果是宽度，则是文本的测量宽度+padding
                    return textW+getPaddingLeft()+getPaddingRight();
                }else if(type==2){
                    //如果是高度 ，则是文本的测量高度+padding
                    return textH+getPaddingTop()+getPaddingBottom();
                }

            }break;

        }
        return size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制背景,圆角矩形RoundRect
        paint.setColor(Color.parseColor("#88000000"));
        /**
         * 参数2、3：边角的弧度
         */
        canvas.drawRoundRect(rf,20,20,paint);
        if(text!=null){
            //再绘制文本
            //设置文本颜色
            paint.setColor(Color.parseColor("#88ffffff"));
            //x
            float dx = (getMeasuredWidth() - paint.measureText(text))/2;
            //y
            float dy = (getMeasuredHeight() - textH) / 2 - paint.ascent();
            canvas.drawText(text, dx, dy, paint);
        }
    }

    /**
     * 绘制字符
     *
     * @param str
     */
    public void setText(String str){
        //从外部传入字符串，设置给text
        text = str;
        //重绘界面
        invalidate();

    }
}
