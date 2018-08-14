package momo.com.week7.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import momo.com.week7.R;
import momo.com.week7.utils.SharedUtils;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏。（该Activity已改主题noActionBar）
        //去掉状态栏(在setContextView之前设置)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        // 延迟2s跳转
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //判断是否第一次启动，如果是第一次，跳转到引导界面
                if (SharedUtils.isFirstRun(WelcomeActivity.this)) {
                    //跳转到引导界面
                    Intent intent = new Intent(WelcomeActivity.this,SplashActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    //如果不是第一次启动，跳转到主界面
                    Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }



            }
        }, 2000);
    }
}
