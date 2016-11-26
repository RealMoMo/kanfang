package momo.com.week7.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import momo.com.week7.R;
import momo.com.week7.adapter.CityChoiceAdapter;
import momo.com.week7.bean.CityBean;
import momo.com.week7.my_interface.CityChoiceInterface;
import momo.com.week7.utils.ApiManager;
import momo.com.week7.utils.CityJsonUtils;
import momo.com.week7.utils.IntentUtils;
import momo.com.week7.utils.SharedUtils;
import momo.com.week7.utils.StatusUtils;
import momo.com.week7.widget.SlidView;
import momo.com.week7.widget.SlideLetterView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class CityChoiceActivity extends BaseNoActionBarAndStatusActivity implements SlidView.SlideClick, AdapterView.OnItemClickListener {

    //控件声明
    StickyListHeadersListView lv;
    SlidView slideView;
    SlideLetterView letterView;
    EditText et_search;
    Button btn_cancel;
    //数据源
    List<CityBean> data;
    //适配器
    CityChoiceAdapter adapter;
    //搜索的字符串
    String str_search;
    //延迟搜索的Handler
    Handler handler = new Handler();
    //搜索的Runnable
    Runnable searchRunable = new Runnable() {
        @Override
        public void run() {
            //搜索的代码
            adapter.search(str_search);
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载界面
        setContentView(R.layout.activity_citychoice);
        //初始化控件
        setupView();
        //初始化数据
        initData();
        //初始化adpater
        adapter = new CityChoiceAdapter(this, data);
        //设置adapter
        lv.setAdapter(adapter);
        //联网获取数据
        getCityData();

        //listview的item点击监听
        lv.setOnItemClickListener(this);


    }


    //设置沉浸模式后，给titlelayout的属性重新布局调整
    private boolean flag=true;//设置标记，避免重走该生命周期方法，重复设置。
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(flag) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                RelativeLayout rl = (RelativeLayout) findViewById(R.id.title_layout);
                int statusHeight = StatusUtils.getStatusHeight(this);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rl.getLayoutParams();
                params.height += statusHeight;
                rl.setLayoutParams(params);
                rl.setPadding(0, statusHeight, 0, statusHeight);
            }
            flag=false;
        }

    }

    private void getCityData() {
        //retrofit联网加载数据
        //1.定义Retrofit对象，构建者模式
        Retrofit retrofit = new Retrofit.Builder()
                //添加主机路径
                .baseUrl(ApiManager.BASE_URL)
                //添加转换工厂---String类型（原封不动获取数据）
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        //2.创建CityChoiceInterface接口实例
        CityChoiceInterface city = retrofit.create(CityChoiceInterface.class);
        //3.调用接口实例方法
        //获取call
        Call<String> call = city.getCity();
        //4.发异步请求，解析数据
        call.enqueue(new Callback<String>() {

            /**请求成功回调
             *
             * 服务器正常返回
             *
             *
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //得到下载数据
                String value = response.body();
                //通过CityJsonUtils解析，得到数据集合
                try {
                    data.clear();
                    //把解析结果，添加到总集合中
                    data.addAll(CityJsonUtils.getCityByJson(value));
                    //设置adapter的所有数据
                    adapter.setAllData(data);
                    //更新界面
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            /**请求失败回调
             *
             * 网络不通，服务挂 了
             *
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void initData() {
        data = new ArrayList<>();
    }

    private void setupView() {
        lv = (StickyListHeadersListView) findViewById(R.id.lv);
        slideView = (SlidView) findViewById(R.id.slideView);
        letterView = (SlideLetterView) findViewById(R.id.letterView);
        et_search = (EditText) findViewById(R.id.et_search);
        btn_cancel = (Button) findViewById(R.id.city_choice_cancel);

        //btn_cancel点击监听
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一次进入必须选择城市
                if(SharedUtils.isFirstRun(CityChoiceActivity.this)){
                    Toast.makeText(CityChoiceActivity.this,"请选择城市",Toast.LENGTH_SHORT).show();
                }else{
                    finish();
                }

            }
        });

        //设置侧滑控件slideview的监听--实现自定义SlideView的接口
        slideView.setOnSlideClick(this);
        //设置edittext文本变化监听
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                LogPrint.print("beforeTextChanged:"+s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                LogPrint.print("onTextChanged:"+s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
//                LogPrint.print("afterTextChanged:"+s.toString());
                //通过handler调用搜索方法,延迟搜索，减少误输入搜索
                //设置搜索字符串
                str_search = s.toString();
                //清掉前面的搜索
                handler.removeCallbacks(searchRunable);
                //延迟搜索
                handler.postDelayed(searchRunable,500);

            }
        });
    }

    /**
     * slideview点击、滑动的回调方法
     *
     * @param postion 指的是字母在数组中的下标
     * @param str      对应的内容
     */
    @Override
    public void slideOnClick(int postion, String str) {
        //设置文本
        letterView.setText(str);
        //letterview显示出来
        letterView.setVisibility(View.VISIBLE);

        //StickyListHeadersListView滑动到指定位置
        //原则：data集合中，找到第一条数据，以str开头的数据的位置("热门"特殊处理postion=0)
        for(int i=0;i<data.size();i++){

            if(data.get(i).getLetter().equals(str)){
                //把listview滑动到i的位置
                lv.setSelection(i);
                //返回
                return;
            }
            if(postion==0){
                //把listview滑动到最上面
                lv.setSelection(0);
                //返回
                return;
            }
        }


    }

    //侧滑控件手指抬起时
    @Override
    public void slideUP() {
        //隐藏该控件
        letterView.setVisibility(View.GONE);
    }

    //StickyListHeadersListView点击item的监听
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       if(SharedUtils.isFirstRun(this)){
           Intent intent = new Intent(this,MainActivity.class);
           SharedUtils.saveCityInfo(this,data.get(position).getCityname(),data.get(position).getCityid());
           startActivity(intent);
           finish();
       }else {
           Intent intent = getIntent();
           intent.putExtra(IntentUtils.KEY_CITYNAME, data.get(position).getCityname());
           intent.putExtra(IntentUtils.KEY_CITYID, data.get(position).getCityid());
           setResult(1, intent);

           finish();
       }
    }





}
