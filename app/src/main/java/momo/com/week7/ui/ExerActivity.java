package momo.com.week7.ui;

import momo.com.week7.my_interface.CityChoiceInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2016-11-22.
 */
public class ExerActivity {

    //练习retrofit联网请求
    public void getData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        CityChoiceInterface city = retrofit.create(CityChoiceInterface.class);
        Call<String> call = city.getCity();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
