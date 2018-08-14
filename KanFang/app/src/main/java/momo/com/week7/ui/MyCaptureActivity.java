package momo.com.week7.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import momo.com.week7.R;

/**
 * Created by Administrator on 2016/11/24 0024.
 */
public class MyCaptureActivity extends NoActionBarActivity implements CodeUtils.AnalyzeCallback {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        //创建CaptureFragment(框架自带的Fragment类)
        CaptureFragment cf = new CaptureFragment();
        //设置CaptureFragment的布局
        CodeUtils.setFragmentArgs(cf,R.layout.capture_fragment);
        //设置解析回调接口
        cf.setAnalyzeCallback(this);
        //把captrueFragmetn添加到Activity中
        getSupportFragmentManager().beginTransaction().replace(R.id.captureLayout, cf).commit();

    }

    //扫码成功，回调
    @Override
    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
        //先获取Intent
        Intent intent = getIntent();
        //设置结果值
        Bundle bundle = new Bundle();
        bundle.putString(CodeUtils.RESULT_STRING,result);
        intent.putExtras(bundle);
        //setResult
        setResult(1,intent);
        //finish
        finish();


    }

    //扫码失败回调
    @Override
    public void onAnalyzeFailed() {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString(CodeUtils.RESULT_STRING,"扫码失败");
        intent.putExtras(bundle);
        setResult(0,intent);
        finish();
    }

    //点击取消按钮，结束该Activity
    public void cancelScan(View view){
        finish();
    }
}
