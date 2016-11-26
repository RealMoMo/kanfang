package momo.com.week7.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import momo.com.week7.R;
import momo.com.week7.bean.BannerBean;
import momo.com.week7.my_interface.HomePageInterface;
import momo.com.week7.utils.ApiManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 架包：https://github.com/youth5201314/banner
 *
 * 自定义Banner控件(该自定义控件若要绑定view，需要继承ViewGroup或其子类)
 * <p/>
 * 实现自动轮播效果
 * <p/>
 * 提供设置城市id的方法，只要设置城市id，就能自动获取数据，得到图片，进行轮播
 */
public class BannerView extends FrameLayout{

    Banner banner;

    public BannerView(Context context) {
        super(context);
        init();
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        //加载布局  参数3：true xml定义banner与本自定义view绑定在一起
        LayoutInflater.from(getContext()).inflate(R.layout.home_banner_layout, this,true);
        //初始化控件
        banner = (Banner)findViewById(R.id.home_banner);
        //设置banner风格
        //设置banner样式
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
//        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.FlipHorizontal);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);


    }


    class GlideImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //把path转换成ImageView
            Glide.with(getContext()).load(path).into(imageView);
        }
    }


    private String currentCityId;

    //提供设置城市id的方法（通过该id联网下载）
    public void setCityId(String cityId){
        //cityId一样不需再更新

//        if(cityId.equals(currentCityId)){
//            return;
//        }

        currentCityId=cityId;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HomePageInterface homePage = retrofit.create(HomePageInterface.class);
        Call<BannerBean> call = homePage.getBannerBean(cityId);
        call.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                //下载成功
                //得到BannerBean，得到List<DataEntity>
                //解析List，分别得到图片的url集合,标题的集合
                BannerBean bean = response.body();
                List<BannerBean.DataEntity> data = bean.getData();

                List<String> imgUrls = new ArrayList<String>();
                List<String> titles = new ArrayList<String>();

                for(BannerBean.DataEntity entity :data){
                    imgUrls.add(entity.getPicurl());
                    titles.add(entity.getTitle());
                }

                //设置banner的图片集合及标题集合
                banner.setImages(imgUrls);
                banner.setBannerTitles(titles);
                //banner配置完成，开始轮播
                banner.start();

            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {

            }
        });

    }
}
