package com.example.speechtest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * --------------------------------------------------------
 * Date            Time    	    Author        		Version
 * --------------------------------------------------------
 * 2016/11/10     12:15     tangxin(Michael)        1.0
 * --------------------------------------------------------
 */
public class NetWorkUtils {
    //连接wifi 能访问外网
    public static final int  WIFI = 1;
    //连接wifi 不能访问外网
    public static final int  WIFI_NONE = -1;
    //连接数据流量 能访问外网
    public static final int  MOBILE = 2;
    //连接数据流量 不能访问外网
    public static final int  MOBILE_NONE = -2;
    public static final int  NONE = -3;


    /**
     * 判断网落是否可用     (常用)
     */
    public static boolean isNetworkAvailable(Context context){
        int status = getNetWorkStatus(context);
        return (status == WIFI || status == MOBILE) ? true : false;
    }

    /**
     * 获取当前网络状态
     * @return
     */
    public static int getNetWorkStatus(Context context){
        int status = NONE;
        if(!isWifiEnabled(context) && !isMoblieEnable(context)){
            status = NONE;
        }else if(isWifiEnabled(context)&&isWifiDataEnable(context)){
            status = WIFI;
        }else if(isWifiEnabled(context)&&!isWifiDataEnable(context)){
            status = WIFI_NONE;
        }else if(isMobileDataEnable(context)&&isMobileDataEnable(context)){
            status = MOBILE;
        }else if(isMobileDataEnable(context)&&!isMobileDataEnable(context)){
            status = MOBILE_NONE;
        }
        return status;
    }

    /**
     * wifi是否打开
     */
    private static boolean isWifiEnabled(Context context) {
        ConnectivityManager mgrConn = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager mgrTel = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
                .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
                .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

    /**
     * 数据流量是否打开
     * @param context
     * @return
     */
    private static boolean isMoblieEnable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * wifi是否有网
     * @param context
     * @return
     */
     private  static boolean  isWifiDataEnable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiDataEnable = false;
        isWifiDataEnable = connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        return isWifiDataEnable;
    }

    /**
     * 数据流量是否有网
     * @param context
     * @return
     */
    private static boolean isMobileDataEnable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiDataEnable = false;
        isWifiDataEnable = connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        return isWifiDataEnable;
    }
}
