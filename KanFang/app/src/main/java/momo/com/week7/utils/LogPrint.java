package momo.com.week7.utils;

import android.util.Log;

/**
 * 发布时，可关掉Log
 */
public class LogPrint {

    /**
     * 统一管理Log
     */
    private  static final boolean DEBUG=true;

    private static final String TAG="Tag";

    public static void print(String msg){
        if(DEBUG){
            Log.d(TAG,msg);
        }

    }

    public static void print(String tag,String msg){
        if(DEBUG){
            Log.d(tag,msg);
        }
    }
}
