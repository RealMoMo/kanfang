package momo.com.week7.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import momo.com.week7.R;
import momo.com.week7.bean.HouseDetailBean;

/**
 * 自定义View,用来显示详情界面
 *
 * 界面格式：
 *
 * 标题
 *
 * 来源+时间
 *
 * --------------------------
 *
 * 内容
 */
public class HouseDetailView extends LinearLayout{
    public HouseDetailView(Context context) {
        super(context);
        init();
    }

    public HouseDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private  void init(){
        //固定垂直方向
        setOrientation(VERTICAL);
    }


    public void setData(HouseDetailBean bean){

        if(bean==null){
            return;
        }

        //===========绘制标题=============
        LayoutParams textParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.setMargins(0,20,0,20);
        TextView tv_title = new TextView(getContext());
        //textsize 默认sp为单位
        tv_title.setTextSize(TypedValue.COMPLEX_UNIT_PX,getSize(R.dimen.house_title_textsize));
        tv_title.setText(bean.getTitle());
        tv_title.setTextColor(Color.BLACK);
        this.addView(tv_title,textParams);
        //==========来源 时间=============
        TextView tv_sourcetime = new TextView(getContext());
        tv_sourcetime.setTextSize(TypedValue.COMPLEX_UNIT_PX,getSize(R.dimen.house_time_textsize));
        tv_sourcetime.setText(bean.getSource()+bean.getTime());
        this.addView(tv_sourcetime,textParams);
        //===========分割线==============
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,2);
        View view = new View(getContext());
        view.setBackgroundColor(Color.GRAY);
        this.addView(view,params);
        //============内容===============
        List<HouseDetailBean.ContentEntity> content = bean.getContent();
        for(HouseDetailBean.ContentEntity entity:content){
            int type = entity.getType();
            String value =entity.getValue();
            switch (type){
                //文本内容
                case 1:{
                    TextView tmp = new TextView(getContext());
                    tmp.setTextSize(TypedValue.COMPLEX_UNIT_PX,getSize(R.dimen.house_content_textsize));
                    tmp.setText(value);
                    //设置文本行间距
                    tmp.setLineSpacing(1.8f,1.8f);
                    //段落之间多空一行
//                    value=value.replaceAll("\\s{3,}","\\n\\n");
                    addView(tmp,textParams);
                }break;
                //图片内容
                case 2:{
                    ImageView img = new ImageView(getContext());
                    Glide.with(getContext()).load(value).into(img);
                    addView(img,textParams);
                }break;

            }
        }


    }

    private float getSize(int id){

        return getResources().getDimensionPixelSize(id);
    }
}
