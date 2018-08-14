package momo.com.week7.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import momo.com.week7.R;
import momo.com.week7.utils.LogPrint;

/**
 * 自定义刷新头
 */
public class PullToRefreshHeadView extends FrameLayout implements PtrUIHandler{

    //用来显示刷新动画及图片的ImageView
    ImageView img;

    /**
     * 下拉图片开始id
     */
    int startId=R.drawable.refresh_001;
    /**
     * 下拉图片的最后一张id
     */
    int endId = R.drawable.refresh_048;
    /**
     * 图片的总数
     */
    int imgCount = endId-startId;

    public PullToRefreshHeadView(Context context) {
        super(context);
        init();
    }

    public PullToRefreshHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init(){
        //初始化ImageView
        img = new ImageView(getContext());
        //img拉伸属性
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        //创建ImageView的布局属性
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //居中
        params.gravity = Gravity.CENTER;
        //margin属性
        params.setMargins(50,50,50,50);
        //把ImageView添加到布局中
        addView(img,params);
        //设置默认图片
        img.setImageResource(endId);
    }


    /**
     * Content 重新回到顶部， Header 消失，整个下拉刷新过程完全结束以后，重置 View
     *
     * @param frame
     */
    @Override
    public void onUIReset(PtrFrameLayout frame) {
//        LogPrint.print("onUIReset");

    }

    /**
     * 准备刷新，Header 将要出现时调用。
     *
     * @param frame
     */
    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
//        LogPrint.print("onUIRefreshPrepare");
    }


    /**
     * 开始刷新，Header 进入刷新状态之前调用。
     */
    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
//        LogPrint.print("onUIRefreshBegin");
        //改变图片
        img.setImageResource(R.drawable.icon_black_progressbar);
        //旋转补间动画
        RotateAnimation animation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(1000);
        //设置重复次数
        animation.setRepeatCount(Animation.INFINITE);
        //设置插值器--线性匀速
        animation.setInterpolator(new LinearInterpolator());
        //设置动画
        img.startAnimation(animation);
    }

    /**
     * 刷新结束，Header 开始向上移动之前调用。
     */
    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        LogPrint.print("onUIRefreshComplete");
        //清掉动画
        img.clearAnimation();
        //设置图片
//        img.setImageResource(endId);
    }

    /**
     * 刷新头部位置变化回调
     * ptrIndicator.getCurrentPercent()	可获取当前UI位置改变的百分比
     *
     * @param frame
     * @param isUnderTouch 手指是否按下
     * @param status       状态
     * @param ptrIndicator UI变化指示器
     */
    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        LogPrint.print("onUIPositionChange");

        //如果当前状态是刷新状态，就不执行当前代码，执行onUIRefreshBegin方法
        if(status ==PtrFrameLayout.PTR_STATUS_LOADING){
            //当前是刷新状态，不改图片（加载onUIRefreshBegin方法里的图片）
            return;
        }


        //根据百分比，计算加载图片的索引
        int per=0;
        //
        if(ptrIndicator.getCurrentPercent()<=1) {
             per = (int) (ptrIndicator.getCurrentPercent() * imgCount);

        }else{
            //如果到达100%或100%以上，图片就固定在最后一张
            per =imgCount;
        }
        //刷新完成，就显示最后一个。
        if(status==PtrFrameLayout.PTR_STATUS_COMPLETE){
                per=imgCount;
        }

        img.setImageResource(startId+per);
    }
}
