package yjj.weather.district;

import java.util.LinkedHashSet;
import java.util.Set;

import yjj.weather.application.MyApplication;
import android.content.Context;
import android.content.SharedPreferences;

public class DistrictManager {
	
	private static Context context;
	public static final String DISTRICT="district";//地区管理
	public static final String CITYS="citys";//城市集合
	
	static{
		context=MyApplication.getContext();
	}
	
	/**
	 * 目前的地区列表中中是否包含这个城市 
	 * @param city
	 * @return
	 */
	public static boolean isContains(String cityName){
		SharedPreferences sp=context.getSharedPreferences(DISTRICT, Context.MODE_WORLD_READABLE);
		long updateTime=sp.getLong(cityName, -1);
		return !(updateTime==-1);
	}
	
	/**
	 * 把城市插入地区管理的清单
	 * @param cityName
	 * @param updateTime
	 */
	public static void insertCity(String cityName,long updateTime){
		//更新城市的天气更新时间
		SharedPreferences.Editor editor=context.getSharedPreferences(DISTRICT, context.MODE_WORLD_WRITEABLE).edit();
		editor.putLong(cityName, updateTime);
		editor.commit();
		//更新管理城市的列表
		SharedPreferences sp=context.getSharedPreferences(DISTRICT, Context.MODE_WORLD_WRITEABLE);
		Set<String> cites=sp.getStringSet(CITYS, null);
		if(cites==null){
			cites=new LinkedHashSet<String>();			
		}
		cites.add(cityName);
		editor.putStringSet(CITYS, cites);
		editor.commit();
	}
	
	/**
	 * 获得所有城市的集合
	 * @return
	 */
	public static Set<String> getAllCites(){
		SharedPreferences sp=context.getSharedPreferences(DISTRICT, Context.MODE_WORLD_WRITEABLE);
		Set<String> cites=sp.getStringSet(CITYS, new LinkedHashSet<String>());
		return cites;
	}
	
	public static long getCityUpdateTime(String cityName){
		SharedPreferences sp=context.getSharedPreferences(DISTRICT, Context.MODE_WORLD_READABLE);
		long updateTime=sp.getLong(cityName, -1);
		return updateTime;
	}
}
