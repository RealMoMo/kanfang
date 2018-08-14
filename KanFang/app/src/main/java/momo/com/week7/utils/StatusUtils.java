package momo.com.week7.utils;

import android.content.Context;

/**
 *状态栏工具类
 */
public class StatusUtils {


    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }



//    public static void setHeadLayout(Context context,ViewGroup viewGroup){
//
//        int statusHeight = StatusUtils.getStatusHeight(context);
//        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) viewGroup.getLayoutParams();
//        params.height += statusHeight;
//        viewGroup.setLayoutParams(params);
//        viewGroup.setPadding(0, statusHeight, 0, statusHeight);
//    }
}
