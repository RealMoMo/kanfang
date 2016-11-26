package momo.com.week7.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import momo.com.week7.R;

/**
 * 引导界面加载的Fragment
 */
public class SplashFragment extends Fragment {

    //获取当前界面的索引的key
    public  static final String INDEX_KEY="index";


    ImageView iv1,iv2,iv3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.splash_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
    }

    private void setupView(View view) {
        iv1 = (ImageView) view.findViewById(R.id.iv1);
        iv2 = (ImageView) view.findViewById(R.id.iv2);
        iv3 = (ImageView) view.findViewById(R.id.iv3);

        //设置图片
        //得到当前是第几个界面
        Bundle bunlde =getArguments();
        int index = bunlde.getInt(INDEX_KEY);
        switch (index){
            case 0:{
                iv1.setImageResource(R.drawable.guide_content_new_0);
                iv2.setImageResource(R.drawable.guide_content_0);
                iv3.setImageResource(R.drawable.ic_guide_picture_new_0);

            }break;
            case 1:{
                iv1.setImageResource(R.drawable.guide_content_new_1);
                iv2.setImageResource(R.drawable.guide_content_1);
                iv3.setImageResource(R.drawable.ic_guide_picture_new_1);
            }break;
            case 2:{
                iv1.setImageResource(R.drawable.guide_content_new_2);
                iv2.setImageResource(R.drawable.guide_content_2);
                iv3.setImageResource(R.drawable.ic_guide_picture_new_2);
            }break;
            case 3:{
                iv1.setImageResource(R.drawable.guide_content_new_3);
                iv2.setImageResource(R.drawable.guide_content_3);
                iv3.setImageResource(R.drawable.ic_guide_picture_new_3);
            }break;
        }


    }
}
