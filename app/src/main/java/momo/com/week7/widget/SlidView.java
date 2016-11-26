package momo.com.week7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 自定义侧滑控件
 */
public class SlidView extends View{

    //定义slideview触摸事件回调接口
    public interface SlideClick{

        /**
         * 手指滑动或点击字母时回调
         *
         */
        public void slideOnClick(int postion,String str);

        /**
         * 手指离开时回调
         */
        public void slideUP();
    }

    SlideClick listener;

    /**
     * 设置监听
     *
     * @param click
     */
    public void setOnSlideClick(SlideClick click){
        this.listener =click;
    }

    //画笔
    Paint paint;
    //文字宽
    float textW;
    //文字高
    float textH;

    //当前被点击的位置
    int currentPostion= -1;


    static final String[] letters ={
            "热门","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
    };

    public SlidView(Context context) {
        super(context);
        init();
    }

    public SlidView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();

//        paint.setTextSize(40);
        paint.setColor(Color.parseColor("#19ACCA"));
        paint.setAntiAlias(true);
        //文字的宽高，由onMeasure方法根据布局定义的宽高进行设定(下面注释代码均直接定义了文字的宽高)
        //计算文本的高度
//        textH=paint.descent()-paint.ascent();
        //测量第一个字母的宽度
//        textW = paint.measureText(letters[0]);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasureWH(widthMeasureSpec,1),getMeasureWH(heightMeasureSpec,2));
        //获取绘制每个letter高度
        textH =(getMeasuredHeight()-getPaddingBottom()-getPaddingTop())/letters.length;
        //根据绘制每个letter高度，来设置文字大小(减5只为字体间的间隙大一丁点)
        paint.setTextSize(textH);
        //获取绘制每个letter宽度(拿最宽的字体即letters[0])(已测试：不需要重新调用setMeasuredDimension)
        textW = paint.measureText(letters[0]);
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
                    //如果是宽度，则是文本的测量宽度
                    return (int) textW+getPaddingLeft()+getPaddingRight();
                }else if(type==2){

                    return size;
                    //如果是高度 ，则是所有文本的高度之和
//                    return (int) (letters.length*textH+getPaddingBottom()+getPaddingTop());
                }

            }break;

        }
        return size;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int len = letters.length;

        //从数组中，取出每个letters的内容，并绘制出来
        for(int i=0;i<len;i++) {
            //计算绘制的位置dx,dy
            float dx = (getMeasuredWidth()-paint.measureText(letters[i]))/2;
            float dy =i*textH-paint.ascent()+getPaddingTop();
            //如果是被点中的，变颜色,加粗
            if(i==currentPostion) {
                paint.setFakeBoldText(true);
                paint.setColor(Color.parseColor("#FF1468D6"));
            }else{
                paint.setFakeBoldText(false);
                paint.setColor(Color.parseColor("#19ACCA"));
            }

            canvas.drawText(letters[i],dx,dy,paint);
        }
    }

    /*解决点击事件*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                    eventLable(event.getY());
            }break;
            case MotionEvent.ACTION_MOVE:{
                eventLable(event.getY());

            }break;
            case MotionEvent.ACTION_UP:{
                //设置点击位置为-1
                currentPostion=-1;
                //重绘界面
                invalidate();
                //调用sildeUP方法
                if(listener!=null){
                    listener.slideUP();
                }

            }break;
        }

        //不继续向外分发事件
        return true;
    }


    /**
     * 处理点击事件
     * @param y
     */
    private void eventLable(float y) {
        int index = (int) ((y-getPaddingTop())/textH);
        if(index <0){
            index=0;
        }
        if(index>letters.length-1){
            index=letters.length-1;
        }

        //若只是在同一个letters上移动，就没必要重绘
        if(index!=currentPostion) {
            //设置点击位置
            currentPostion = index;
            //重绘界面
            invalidate();
            //调用接口实例方法
            if(listener!=null){
                listener.slideOnClick(index,letters[index]);
            }

        }
    }


}
