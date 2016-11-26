package momo.com.week7.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import momo.com.week7.R;
import momo.com.week7.adapter.AbstractBaseAdapter;
import momo.com.week7.bean.HomePageBean;
import momo.com.week7.my_interface.HomePageInterface;
import momo.com.week7.ui.CityChoiceActivity;
import momo.com.week7.ui.HouseDetailActivity;
import momo.com.week7.ui.MyCaptureActivity;
import momo.com.week7.utils.ApiManager;
import momo.com.week7.utils.IntentUtils;
import momo.com.week7.utils.LogPrint;
import momo.com.week7.utils.SharedUtils;
import momo.com.week7.widget.BannerView;
import momo.com.week7.widget.HomeSecondView;
import momo.com.week7.widget.PullToRefreshHeadView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class HomeFragment extends Fragment implements View.OnClickListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {

    /**
     * 跳转到城市选择界面的requestCode
     */
    static final int INTENT_REQUEST_CITY = 1;
    /**
     * 跳转到扫二维码界面的requestCode
     */
    static final int INTENT_REQUEST_SCAN=2;


    TextView tv_cityName;
    ListView lv;
    //扫码控件
    ImageButton btn_scan;

    //刷新控件
    PtrFrameLayout refresh;

    String cityName;
    String cityId;

    String buttonmore = "0";
    String pageflag = "0";

    //广告轮播对象
    BannerView banner;
    //listview中的8个按钮
    HomeSecondView buttonView;


    //数据源
    List<HomePageBean.DataEntity> data;
    //Adapter
    AbstractBaseAdapter<HomePageBean.DataEntity> adapter;

    //listview是否加载更多数据的标识
    boolean isAddMore;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
//        LogPrint.print("首页 onCreate");
        super.onCreate(savedInstanceState);
        //获取cityName和cityId
        getCityInfo();
        //初始化数据源及adapter
        initData();

        //联网获取数据
        getData();

        //初始化BannerView HomeSecondView的对象
        banner = new BannerView(getActivity());
        buttonView = new HomeSecondView(getActivity());
    }

    private void getCityInfo() {
        String[] cityInfo = SharedUtils.getCityInfo(getContext());
        cityName = cityInfo[0];
        cityId = cityInfo[1];
    }

    private void initData() {
        data = new ArrayList<>();
        adapter = new AbstractBaseAdapter<HomePageBean.DataEntity>(getActivity(), data, R.layout.home_listview_content, R.layout.home_listview_content2) {
            //绑定数据
            @Override
            public void bindData(int position, ViewHolder holder) {
                HomePageBean.DataEntity entity = data.get(position);
                //得到接口返回的数据类型，就可确认布局类型，根据不同布局，加载不同数据
                int type = Integer.valueOf(data.get(position).getType());
                if (type == 0) {
                    //图片
                    ImageView iv = (ImageView) holder.findViewById(R.id.home_listview_img);
                    Glide.with(HomeFragment.this).load(entity.getGroupthumbnail()).into(iv);
                    //标题
                    TextView tv_title = (TextView) holder.findViewById(R.id.home_listview_title);
                    tv_title.setText(entity.getTitle());
                    //内容
                    TextView tv_content = (TextView) holder.findViewById(R.id.home_listview_summary);
                    tv_content.setText(entity.getSummary());
                } else {
                    //图片
                    ImageView iv = (ImageView) holder.findViewById(R.id.home_listview_img);
                    Glide.with(HomeFragment.this).load(entity.getGroupthumbnail()).into(iv);
                    //标题
                    TextView tv_title = (TextView) holder.findViewById(R.id.home_listview_title);
                    tv_title.setText(entity.getTitle());
                }
            }

            //重写itemViewType方法
            @Override
            public int getItemViewType(int position) {
                //得到数据
                HomePageBean.DataEntity entity = data.get(position);
                //得到接口数据里的类型参数（0或1）
                String type = entity.getType();
                //把字符串转换成int，再返回
                return Integer.valueOf(type);
            }


        };


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        LogPrint.print("首页 onCreateView");
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        LogPrint.print("首页 onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        //初始化控件
        setupView(view);

    }

    @Override
    public void onDestroyView() {
//        LogPrint.print("首页 onDestroyView");
        super.onDestroyView();
    }

    private void setupView(View view) {
        //找到控件===============================
        tv_cityName = (TextView) view.findViewById(R.id.tv_cityname);
        lv = (ListView) view.findViewById(R.id.home_lv);
        btn_scan = (ImageButton) view.findViewById(R.id.btn_scan);

        TextView btn1 = (TextView) buttonView.findViewById(R.id.tv_xinfang);
        TextView btn2 = (TextView) buttonView.findViewById(R.id.tv_ershoufang);
        TextView btn3 = (TextView) buttonView.findViewById(R.id.tv_zufang);
        TextView btn4 = (TextView) buttonView.findViewById(R.id.tv_zixun);
        TextView btn5 = (TextView) buttonView.findViewById(R.id.tv_dazhe);
        TextView btn6 = (TextView) buttonView.findViewById(R.id.tv_kaipan);
        TextView btn7 = (TextView) buttonView.findViewById(R.id.tv_fangdai);
        TextView btn8 = (TextView) buttonView.findViewById(R.id.tv_gengduo);

        //初始化Refresh控件
        setupRefreshView(view);

        //控件设置监听============================
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn_scan.setOnClickListener(this);

        //
        tv_cityName.setText(cityName);
        //设置tv_cityname的点击监听事件
        tv_cityName.setOnClickListener(this);

        lv.addHeaderView(banner);
        lv.addHeaderView(buttonView);


        //lv关联Adapter
        lv.setAdapter(adapter);

        //lv设置监听
        lv.setOnScrollListener(this);
        lv.setOnItemClickListener(this);

        //设置城市
        banner.setCityId(cityId);

        /**
         * 恢复homeFragment界面的数据
         *（因为fragment切换时，重走onDestroyView oncreateView onViewCreate
         * 控件记录的内容会重置原来样式，所以要进行恢复）
         *
         */
        restoreView();
    }

    private void setupRefreshView(View view) {
        refresh = (PtrFrameLayout) view.findViewById(R.id.refresh);

        //UI=================
        //创建刷新头控件
        PullToRefreshHeadView pullHead = new PullToRefreshHeadView(getContext());
        //添加刷新头
        refresh.setHeaderView(pullHead);
        //添加刷新头控制
        refresh.addPtrUIHandler(pullHead);
        //功能===============
        //设置刷新事件
        refresh.setPtrHandler(new PtrDefaultHandler() {
            //刷新方法
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //联网获取数据--listview
                getData();
                //广告获取数据
                banner.setCityId(cityId);
            }

            //解决Listview与下拉刷新的冲突
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, lv, header);
            }
        });
    }


    /**
     * 恢复界面
     */
    private void restoreView() {
        if (cityName != null) {
            tv_cityName.setText(cityName);

        }
    }

    //处理homeFragment的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击城市名控件
            case R.id.tv_cityname: {
                //跳转到城市选择界面
                Intent intent = new Intent(getActivity(), CityChoiceActivity.class);
                //启动(需要城市界面选择返回相应值)
                startActivityForResult(intent, INTENT_REQUEST_CITY);
            }
            break;
            case R.id.tv_xinfang: {
//                Toast.makeText(getContext(),"xinfang",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.tv_ershoufang: {
//                Toast.makeText(getContext(),"shoufang",Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.tv_zufang: {
            }
            break;
            case R.id.tv_zixun: {
            }
            break;
            case R.id.tv_dazhe: {
            }
            break;
            case R.id.tv_kaipan: {
            }
            break;
            case R.id.tv_fangdai: {
            }
            break;
            case R.id.tv_gengduo: {
            }
            break;
            case R.id.btn_scan: {
                Intent intent = new Intent(getActivity(), MyCaptureActivity.class);
                startActivityForResult(intent,INTENT_REQUEST_SCAN);
            }
            break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //根据requestCode判断是哪个界面返回的
        switch (requestCode) {
            //从城市选择界面返回
            case INTENT_REQUEST_CITY: {
                if (data != null) {
                    //回到顶部
                    //or  lv.smoothScrollToPosition(0);(效果没下面好)
                    lv.setSelection(0);

                    cityName = data.getStringExtra(IntentUtils.KEY_CITYNAME);
                    cityId = data.getStringExtra(IntentUtils.KEY_CITYID);
                    //选择城市不一样，才重新设置并下载数据
                    if (!cityName.equals(tv_cityName.getText().toString())) {
                        SharedUtils.saveCityInfo(getContext(), cityName, cityId);
                        //把城市名设置给TExtVeiw
                        tv_cityName.setText(cityName);
                        //联网获取数据
                        getData();
                        //设置Banner
                        banner.setCityId(cityId);
                    }
                }
            }
            break;
            case INTENT_REQUEST_SCAN:{
                //扫码返回结果
                if(data !=null){
                    //取出data中bundle
                    Bundle bundle = data.getExtras();
                    if(bundle!=null){
                        //取出结果
                        String value = bundle.getString(CodeUtils.RESULT_STRING);
//                        LogPrint.print("扫码结果："+value);
                        Toast.makeText(getActivity(), "扫码结果："+value, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    //联网获取该城市数据
    private void getData() {
        LogPrint.print("getData momo");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        HomePageInterface homePage = retrofit.create(HomePageInterface.class);
        /**
         * 参数：pageflag;buttonmore;cityid
         * 1)进入时：reqnum=10,pageflag=0,buttonmore=0;
         * 2)点击查看更多时：reqnum=20,pageflag=0,buttonmore=1;
         * 3)刷新时：reqnum=20,pageflag=1,buttonmore=1;
         */
        Call<String> call = homePage.getListContent("0", buttonmore, cityId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                LogPrint.print("buttonmore:" + buttonmore.toString());
                String value = response.body();
                //把String解析成HomePageBean
                Gson gson = new Gson();
                HomePageBean bean = gson.fromJson(value, new TypeToken<HomePageBean>() {
                }.getType());
                //刚进入清空data数据
                if (buttonmore.equals("0")) {
                    data.clear();
                }
                //得到bean中的集合，再添加到data中
                data.addAll(bean.getData());
                //更新界面
                adapter.notifyDataSetChanged();

                //结束刷新
                refresh.refreshComplete();

                buttonmore = "0";
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //请求失败也要结束刷新
                //结束刷新
                refresh.refreshComplete();
            }
        });
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //加载更多数据(getData要改)
        if (scrollState == 0 && isAddMore) {
            getData();
            buttonmore = "1";
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            isAddMore = true;
        } else {
            isAddMore = false;
        }
    }

    //listview点击item的监听事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position>=lv.getHeaderViewsCount()) {
            String webId = data.get(position - lv.getHeaderViewsCount()).getId();
            //用WebView显示信息界面
//        Intent intent = new Intent(getActivity(), WebViewActivity.class);
//        intent.putExtra(IntentUtils.KEY_ITEMID,webId);
//        startActivity(intent);
            //用自定义布局显示信息界面
            Intent intent = new Intent(getActivity(), HouseDetailActivity.class);
            intent.putExtra(IntentUtils.KEY_NEWSID, webId);
            startActivity(intent);
        }

    }
}
