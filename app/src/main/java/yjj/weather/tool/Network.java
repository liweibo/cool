package yjj.weather.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 检测网络状态的工具类
 * @author Administrator
 *
 */
public class Network {
    //判断网络是否打开
    public static boolean isNetworkAvailable(Context context) {  
        ConnectivityManager connectivity = (ConnectivityManager) context  
                .getSystemService(Context.CONNECTIVITY_SERVICE);  
        if (connectivity != null) {  
            NetworkInfo info = connectivity.getActiveNetworkInfo();  
            if (info != null && info.isConnected())   
            {  
                // 当前网络是连接的  
                if (info.getState() == NetworkInfo.State.CONNECTED)   
                {  
                    // 当前所连接的网络可用  
                    return true;  
                }  
            }  
        }  
        return false;  
    }  
    
    //判断数据流量是否开启
    public boolean isMobileConnected(Context context){
    	ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);    
        NetworkInfo mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);   //获取移动网络信息  
        if (mMobileNetworkInfo != null) {    
            return mMobileNetworkInfo.isAvailable();    //getState()方法是查询是否连接了数据网络  
        }  
        return false;
    }
}
