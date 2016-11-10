package yjj.weather.district;

import java.util.LinkedHashSet;
import java.util.Set;

import yjj.weather.application.MyApplication;
import android.content.Context;
import android.content.SharedPreferences;

public class DistrictManager {
	
	private static Context context;
	public static final String DISTRICT="district";//��������
	public static final String CITYS="citys";//���м���
	
	static{
		context=MyApplication.getContext();
	}
	
	/**
	 * Ŀǰ�ĵ����б������Ƿ����������� 
	 * @param city
	 * @return
	 */
	public static boolean isContains(String cityName){
		SharedPreferences sp=context.getSharedPreferences(DISTRICT, Context.MODE_WORLD_READABLE);
		long updateTime=sp.getLong(cityName, -1);
		return !(updateTime==-1);
	}
	
	/**
	 * �ѳ��в������������嵥
	 * @param cityName
	 * @param updateTime
	 */
	public static void insertCity(String cityName,long updateTime){
		//���³��е���������ʱ��
		SharedPreferences.Editor editor=context.getSharedPreferences(DISTRICT, context.MODE_WORLD_WRITEABLE).edit();
		editor.putLong(cityName, updateTime);
		editor.commit();
		//���¹�����е��б�
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
	 * ������г��еļ���
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
