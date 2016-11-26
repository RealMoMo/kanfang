package momo.com.week7.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import momo.com.week7.R;
import momo.com.week7.fragment.DiscoverFragment;
import momo.com.week7.fragment.HomeFragment;
import momo.com.week7.fragment.MessageFragment;
import momo.com.week7.fragment.MineFragment;
import momo.com.week7.utils.SharedUtils;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class MainActivity extends NoActionBarActivity {

    //声明底部导航的文字数组
    private String[] tabTexts = {
            "首页",
            "发现",
            "消息",
            "我的"
    };

    //声明底部导航的图片id数组
    private int[] imgId = {
            R.drawable.tab_home_selector,
            R.drawable.tab_discover_selector,
            R.drawable.tab_message_selector,
            R.drawable.tab_mine_selector,
    };

    //TabHost使用的Fragment类数组
    private Class[] fragments = {
            HomeFragment.class, DiscoverFragment.class, MessageFragment.class, MineFragment.class
    };

    //控件的声明
    private FragmentTabHost tabHost;

    LayoutInflater inflater;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //保存第一次启动数据
        SharedUtils.saveFirstRun(this);
        //初始化控件
        setupView();
    }

    private void setupView() {
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        //设置tabHost
        /*
        * 参数1：context
        *
        * 参数2：FragmentManager
        *
        * 参数3：要显示的Fragment的容器id
        *
        * */
        tabHost.setup(this,getSupportFragmentManager(),R.id.fragmentLayout);

        //初始化inflater
        inflater = LayoutInflater.from(this);

        //给tabHost添加Tab
        for(int i=0;i<tabTexts.length;i++){
            //创建新的Tab  参数作用：可通过该参数找到tabItem
            TabHost.TabSpec tabItem = tabHost.newTabSpec(i + "");
            //给tabItem设置内容view
            tabItem.setIndicator(getTabItemView(i));
            //tabItem添加到tabHost中
            /*
            * 参数1：tab标签
            *
            * 参数2：tab内容的Fragment类
            *
            * 参数3：Bundle    可以传值到Fragment
            *
            * */
            tabHost.addTab(tabItem,fragments[i],null);
            //tabHost去边线
            tabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
        }
    }



    /**
     * 加载底部导航的四个Tab
     *
     * @param index
     * @return
     */
    private View getTabItemView(int index){


        View view = inflater.inflate(R.layout.tab_item_layout,null);
        //找到控件
        ImageView iv = (ImageView) view.findViewById(R.id.tab_img);
        TextView tv = (TextView) view.findViewById(R.id.tab_tv);
        //给控件设置相应内容
        iv.setImageResource(imgId[index]);
        tv.setText(tabTexts[index]);

        return  view;
    }


    //按两次backspace退出应用

    private long exitTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
                if(System.currentTimeMillis()-exitTime>2000){
                    Toast.makeText(this,"再点击一次退出",Toast.LENGTH_SHORT).show();
                    exitTime=System.currentTimeMillis();
                }else{
                    finish();
                    //正常退出--0
                    System.exit(0);
                }
            //return true 该事件自己处理，不向外分发该事件
        return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
