package com.example.weather;

import yjj.weather.application.ApplicationInfo;
import yjj.weather.update.UpdateWeatherManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.sax.StartElementListener;
import android.util.Log;

/**
 * 监听wifi,当wifi开启时刷新数据
 * @author Administrator
 *
 */
public class NetworkConnectChangedReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		String action=intent.getAction();
		if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)) {//在此监听wifi有无 
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0); 
             
            switch (wifiState) { 
            case WifiManager.WIFI_STATE_DISABLED: 
                break; 
            case WifiManager.WIFI_STATE_DISABLING: 
                break; 
            case WifiManager.WIFI_STATE_ENABLED: 
            	//这里进行更新天气信息
            	Log.d("debug", "开启了wifi");
            	Intent newIntent=new Intent(ApplicationInfo.SERVICE_UPDATE_WEATHER_SERVICE);
            	context.startService(newIntent);
                break; 
            case WifiManager.WIFI_STATE_ENABLING: 
                break; 
            case WifiManager.WIFI_STATE_UNKNOWN: 
                break; 
            } 
        }  
		
	}

}
