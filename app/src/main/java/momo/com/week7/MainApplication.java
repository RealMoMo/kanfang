package momo.com.week7;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by momo on 2016/11/21 0021.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化ZXinglib（二维码）
        ZXingLibrary.initDisplayOpinion(this);
    }
}
