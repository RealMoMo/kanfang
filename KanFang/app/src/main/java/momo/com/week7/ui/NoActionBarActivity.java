package momo.com.week7.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 隐藏action并设置沉浸模式的Activity
 */
public class NoActionBarActivity extends AppCompatActivity{



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉actionbar
        getSupportActionBar().hide();


    }
}
