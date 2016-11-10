package com.example.weather;

import yjj.weather.update.UpdateWeatherManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UpdateWeatherService extends Service{

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d("debug", "开始更新天气数据，UpdateWeatherService");
		UpdateWeatherManager.getInstance().update(UpdateWeatherManager.UPDATE_FROM_WIFI_STATE, null); 
		return super.onStartCommand(intent, flags, startId);
	}

}
