package momo.com.week7.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import momo.com.week7.R;
import momo.com.week7.fragment.SplashFragment;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class SplashActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    ViewPager viewPager;
    List<Fragment> fragments;

    LinearLayout dot_layout;
    List<ImageView> dotViews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        setupView();
        //初始化点的布局
        initDot();
        //viewpager的切换监听
        viewPager.addOnPageChangeListener(this);

    }

    //点击跳转，跳转到城市选择
    public void startMain(View view){
        Intent intent = new Intent(this,CityChoiceActivity.class);
        startActivity(intent);
        finish();
    }



    private void setupView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager_splash);
        dot_layout = (LinearLayout) findViewById(R.id.dot_layout);

        //初始化数据源
        fragments = new ArrayList<>();
        for (int i=0; i<4;i++){
            SplashFragment sf = new SplashFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(SplashFragment.INDEX_KEY,i);
            sf.setArguments(bundle);
            //添加到集合
            fragments.add(sf);
        }

        //适配器
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };

        viewPager.setAdapter(adapter);


        //设置viewpager切换动画
        viewPager.setPageTransformer(true,new MyTrans());


    }

    private void initDot() {
        dotViews = new ArrayList<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
        params.setMargins(10,10,10,10);
        for(int i=0;i<fragments.size();i++){
            ImageView view = new ImageView(this);
            if(i==0){
                view.setImageResource(R.drawable.dot_shape_select);
            }else{
                view.setImageResource(R.drawable.dot_shape_normal);
            }
            //添加到dot_layout的线性布局中
            dot_layout.addView(view,params);
            //添加到集合
            dotViews.add(view);

        }
    }


    //动画的ImageView控件的id数组
    int[] viewId ={R.id.iv1,R.id.iv2,R.id.iv3};

    //实现viewPager的切换动画
    class MyTrans implements  ViewPager.PageTransformer{

        /**
         * 根据偏移百分比，计算每个View的偏移比
         * <p/>
         * 每个View的偏移量依次递增
         *
         * @param page     当前Fragment的View
         * @param position view的偏移百分比
         */
        @Override
        public void transformPage(View page, float position) {
            //page-->ViewGroup
            //遍历page，拿到三个做动画的ImageView
            //根据position设置View的属性

            float transX = page.getWidth()*position;
            //遍历page
            for(int i=0;i<viewId.length;i++){
                //找到动画view
                View view = page.findViewById(viewId[i]);
                if(view!=null){
                    //设置x的偏移量
                    view.setTranslationX(transX);
                }
                //下一个动画view的x偏移量
                transX*=20f;
            }

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for(int i=0;i<dotViews.size();i++){
            if(i==position){
                dotViews.get(i).setImageResource(R.drawable.dot_shape_select);
            }else{
                dotViews.get(i).setImageResource(R.drawable.dot_shape_normal);
            }
        }

    }

    //判断引导界面是否能继续左滑(即有无惯性滑动)
    boolean isLeftScroll;

    @Override
    public void onPageScrollStateChanged(int state) {
        //根据state进行判断
        switch (state){
            //手指滑动
            case ViewPager.SCROLL_STATE_DRAGGING:{

            }break;
            //停止状态
            case ViewPager.SCROLL_STATE_IDLE:{
                //如果是最后一页(继续左滑是没有惯性滑动的)，且没有惯性滑动时，跳转城市选择
                if(!isLeftScroll &&viewPager.getCurrentItem()==(viewPager.getAdapter().getCount()-1)){
                    Intent intent = new Intent(this,CityChoiceActivity.class);
                    startActivity(intent);
                    finish();
                }

                isLeftScroll =false;

            }break;
            //惯性滑动
            case ViewPager.SCROLL_STATE_SETTLING:{
                    isLeftScroll=true;
            }break;
        }
    }





}
