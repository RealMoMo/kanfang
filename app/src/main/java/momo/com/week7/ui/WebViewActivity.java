package momo.com.week7.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import momo.com.week7.R;
import momo.com.week7.utils.IntentUtils;
import momo.com.week7.utils.StatusUtils;

/**
 * Created by Administrator on 2016/11/24 0024.
 */
public class WebViewActivity extends BaseNoActionBarAndStatusActivity {

    WebView webView;
    ProgressBar pb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        setupView();
    }


    //设置沉浸模式后，给titlelayout的属性重新布局调整
    private boolean flag=true;//设置标记，避免重走该生命周期方法，重复设置。
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(flag) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                RelativeLayout rl = (RelativeLayout) findViewById(R.id.webView_titleLayout);
                int statusHeight = StatusUtils.getStatusHeight(this);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rl.getLayoutParams();
                params.height += statusHeight;
                rl.setLayoutParams(params);
                rl.setPadding(0, statusHeight, 0, statusHeight);
            }
            flag=false;
        }

    }

    private void setupView() {
        webView = (WebView) findViewById(R.id.webView);
        pb = (ProgressBar) findViewById(R.id.webview_progressbar);
        webView.getSettings().setJavaScriptEnabled(true);

//        Intent intent = getIntent();
//        String itemId = intent.getStringExtra(IntentUtils.KEY_ITEMID);
//        String url = ApiManager.HOME_ITEM_URL+itemId+"/";

        Intent intent =getIntent();
        String url = intent.getStringExtra(IntentUtils.KEY_DISCOVERURL);

        webView.loadUrl(url);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pb.setProgress(newProgress);
            }
        });


        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pb.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pb.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 按返回时，看网页是否能返回
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void back(View view){
        finish();
    }




}
