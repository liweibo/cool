package yjj.weather.application;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{
	private static Context applicationContext;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		applicationContext=getApplicationContext();//ʹ��applicationcontext��contextҪ�õ㡣
	}

	public static Context getContext() {
		return applicationContext;
	}
	
	
}
