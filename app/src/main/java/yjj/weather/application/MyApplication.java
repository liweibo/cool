package yjj.weather.application;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application{
	private static Context applicationContext;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		applicationContext=getApplicationContext();//使用applicationcontext比context要好点。
	}

	public static Context getContext() {
		return applicationContext;
	}
	
	
}
