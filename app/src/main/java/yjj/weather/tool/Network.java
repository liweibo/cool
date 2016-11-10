package yjj.weather.tool;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * �������״̬�Ĺ�����
 * @author Administrator
 *
 */
public class Network {
    //�ж������Ƿ��
    public static boolean isNetworkAvailable(Context context) {  
        ConnectivityManager connectivity = (ConnectivityManager) context  
                .getSystemService(Context.CONNECTIVITY_SERVICE);  
        if (connectivity != null) {  
            NetworkInfo info = connectivity.getActiveNetworkInfo();  
            if (info != null && info.isConnected())   
            {  
                // ��ǰ���������ӵ�  
                if (info.getState() == NetworkInfo.State.CONNECTED)   
                {  
                    // ��ǰ�����ӵ��������  
                    return true;  
                }  
            }  
        }  
        return false;  
    }  
    
    //�ж����������Ƿ���
    public boolean isMobileConnected(Context context){
    	ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);    
        NetworkInfo mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);   //��ȡ�ƶ�������Ϣ  
        if (mMobileNetworkInfo != null) {    
            return mMobileNetworkInfo.isAvailable();    //getState()�����ǲ�ѯ�Ƿ���������������  
        }  
        return false;
    }
}
