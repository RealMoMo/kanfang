package momo.com.week7.my_interface;

import momo.com.week7.bean.BannerBean;
import momo.com.week7.bean.HouseDetailBean;
import momo.com.week7.utils.ApiManager;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public interface HomePageInterface {

    @GET(ApiManager.HOME_PAGE)
    Call<String> getListContent(@Query("pageflag")String pageflag,
                                @Query("buttonmore")String buttonmore,
                                @Query("cityid")String cityid);

    @GET(ApiManager.HOME_BANNER)
    Call<BannerBean> getBannerBean(@Query("cityid")String cityid);


    @GET(ApiManager.HOUSE_DETAIL)
    Call<HouseDetailBean> getHouseDetailBean(@Query("newsid")String newsid);

//    @GET(ApiManager.TEST)
//    Call<String> getTest(@Query("pageflag")String pageflag,
//                         @Query("buttonmore")String buttonmore,
//                         @Query("cityid")String cityid);
//
//    @GET(ApiManager.TEST)
//    Call<String> getTestMore(@Query("pageflag")String pageflag,
//                         @Query("buttonmore")String buttonmore,
//                         @Query("cityid")String cityid,
//                             @Query("lastid")String lastid);
}
