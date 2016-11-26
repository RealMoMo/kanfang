package momo.com.week7.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import momo.com.week7.R;
import momo.com.week7.ui.WebViewActivity;
import momo.com.week7.utils.ApiManager;
import momo.com.week7.utils.IntentUtils;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class DiscoverFragment extends Fragment {


    ImageView img;
    WebView webView;


    int startId=R.drawable.waiting_001;

    int endId =R.drawable.waiting_065;

    int imgCount = endId-startId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.discover_fragment,container,false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
    }

    private void setupView(View view) {
        webView = (WebView) view.findViewById(R.id.discover_webView);
        img = (ImageView) view.findViewById(R.id.discover_img);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                int per =0;

                if(newProgress<100){
                    per = (int) (newProgress/(100f/imgCount));
                }else{
                    per =imgCount;
                }

                img.setImageResource(startId+per);

            }
        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                webView.setVisibility(View.GONE);
                img.setVisibility(View.VISIBLE);


            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                img.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);

            }


            /**
             * 当webview中的超链接(重定向)被点击时，回调该方法
             *
             * @param view
             * @param url
             * @return 返回值：表示这个点击事件是否继续给webview,true表示完全自己处理，false表示交给webview处理
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //启动一个WebViewActivity,并传入url
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra(IntentUtils.KEY_DISCOVERURL,url);
                startActivity(intent);
                //返回true 自己处理
                return true;
            }
        });

        //加载数据
        webView.loadUrl(ApiManager.DISCOVER_URL);
    }
}
