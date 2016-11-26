package momo.com.week7.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 共享参数工具类
 */
public class SharedUtils {

    /**
     * 共享参数文件名
     */
    static final String SHARED_NAME ="kanfang";
    static final String SHARED_CITY="city";

    /**
     * 第一次启动保存的key
     */
    static final String FIRST_RUN="isFirstRun";

    /**
     * cityName、cityId的key
     */
    static final String CITY_NAME="cityname";
    static final String CITY_ID="cityid";


    /**
     * 判断是否是第一次启动
     *
     * @param context
     * @return
     */
    public static boolean isFirstRun(Context context){
        SharedPreferences shared = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);

        return shared.getBoolean(FIRST_RUN,true);
    }


    /**
     * 进入主界面时，调用
     *
     * @param context
     */
    public static void saveFirstRun(Context context){
        SharedPreferences shared = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        shared.edit().putBoolean(FIRST_RUN,false).commit();

    }

    /**
     * 获取城市信息
     *
     */
    public static String[] getCityInfo(Context context){
        SharedPreferences shared = context.getSharedPreferences(SHARED_CITY, Context.MODE_PRIVATE);
        String[] str = {shared.getString(CITY_NAME,"北京"),shared.getString(CITY_ID,"1")};
        return str;
    }

    /**
     * 存储城市信息
     *
     */
    public static void saveCityInfo(Context context,String cityName ,String cityId){
        SharedPreferences shared = context.getSharedPreferences(SHARED_CITY, Context.MODE_PRIVATE);
        shared.edit().putString(CITY_NAME,cityName)
                .putString(CITY_ID,cityId)
                .commit();
    }
}
