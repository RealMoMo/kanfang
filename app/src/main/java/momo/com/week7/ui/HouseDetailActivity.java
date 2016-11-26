package momo.com.week7.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import momo.com.week7.R;
import momo.com.week7.bean.HouseDetailBean;
import momo.com.week7.my_interface.HomePageInterface;
import momo.com.week7.utils.ApiManager;
import momo.com.week7.utils.IntentUtils;
import momo.com.week7.utils.StatusUtils;
import momo.com.week7.widget.HouseDetailView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 首页点击跳转详情界面
 * <p/>
 * 需要传入newsid
 * <p/>
 * 根据newsid，从服务下载数据，得到JSONObject
 * <p/>
 * 再把JSONObject数据显示出来
 * <p/>
 * JSONObject 的content是个动态数据，可能有多条，可能是文本，也可能是图片     type
 */
public class HouseDetailActivity  extends BaseNoActionBarAndStatusActivity{


    HouseDetailView detailView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housedetail);
        setupView();
        //从intent中取出newid
        String newsId = getIntent().getStringExtra(IntentUtils.KEY_NEWSID);
        //下载数据
        getData(newsId);
    }

    private void setupView() {
        detailView = (HouseDetailView) findViewById(R.id.houseView);
    }


    //设置沉浸模式后，给titlelayout的属性重新布局调整
    private boolean flag=true;//设置标记，避免重走该生命周期方法，重复设置。
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(flag) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                RelativeLayout rl = (RelativeLayout) findViewById(R.id.housedetail_titleLayout);
                int statusHeight = StatusUtils.getStatusHeight(this);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rl.getLayoutParams();
                params.height += statusHeight;
                rl.setLayoutParams(params);
                rl.setPadding(0, statusHeight, 0, statusHeight);
            }
            flag=false;
        }

    }

    //联网加载数据
    private void getData(String newsId) {

        Retrofit retroif = new Retrofit.Builder()
                .baseUrl(ApiManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        HomePageInterface home = retroif.create(HomePageInterface.class);
        Call<HouseDetailBean> call = home.getHouseDetailBean(newsId);
        call.enqueue(new Callback<HouseDetailBean>() {
            @Override
            public void onResponse(Call<HouseDetailBean> call, Response<HouseDetailBean> response) {
               HouseDetailBean bean = response.body();
                HouseDetailView view = new HouseDetailView(HouseDetailActivity.this);
                detailView.setData(bean);
            }

            @Override
            public void onFailure(Call<HouseDetailBean> call, Throwable t) {

            }
        });
    }

    //返回的点击事件
    public void back(View view){
        finish();
    }
}
