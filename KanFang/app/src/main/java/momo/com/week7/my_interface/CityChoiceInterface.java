package momo.com.week7.my_interface;

import momo.com.week7.utils.ApiManager;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public interface CityChoiceInterface {

    @GET(ApiManager.CITY_CHOICE)
    Call<String> getCity();
}
