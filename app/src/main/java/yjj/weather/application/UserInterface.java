package yjj.weather.application;

import java.io.File;

import com.google.gson.Gson;

import yjj.weather.net.data.Result;
import yjj.weather.net.data.WeatherInfo;
import yjj.weather.update.UpdateWeatherManager;
import yjj.weather.update.WeatherFileManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * 用户接口，用来给表示层提供数据服务
 * 主要功能包括，设置默认城市，提供指定城市的数据信息
 * @author Administrator
 *
 */
public class UserInterface {
	
	public static final String DEFAULT_CITY_NAME1="湘潭";
	public static final String DEFAULT_CITY_NAME2="长沙";
	public static final String DEFAULT_CITY_NAME3="株洲";
	public static final String DEFAULT_CITY_NAME4="沈阳";
	private static UserInterface userInterface;
	
	/**
	 * 设置默认城市
	 * @param cityname
	 */
	public static void setDefaultCity(String whichCity,String cityname){
		SharedPreferences.Editor editor=MyApplication.getContext().getSharedPreferences(ApplicationInfo.DEFALUT_CITY, Context.MODE_WORLD_WRITEABLE).edit();
		editor.putString(whichCity, cityname);
		editor.commit();
	}
	
	/**
	 * 获得默认城市
	 * @param whichCity 备选输入值为DEFAULT_CITY_NAME*
	 * @return
	 */
	public static String getDefaultCity(String whichCity){
		SharedPreferences sf=MyApplication.getContext().getSharedPreferences(ApplicationInfo.DEFALUT_CITY, Context.MODE_WORLD_WRITEABLE);
		return sf.getString(whichCity, null);
	}
	
	public UserInterface() {
		// TODO Auto-generated constructor stub
	}
	
	public static UserInterface getInstance(){//懒汉式写法
		if(userInterface==null){
			synchronized (UserInterface.class) {
				if(userInterface==null){
					userInterface=new UserInterface();
				}
			}
		}
		return userInterface;
	}

	/**
	 * 根据指定城市获取天气数据
	 * @param cityname
	 * @return
	 */
	public Result getWeather(String cityname){
		File file=new File(WeatherFileManager.WEATHER_FILE_PATH+"/"+cityname+".txt");
		if(file.exists()){
			String jsonStr=WeatherFileManager.ReadFromFile(cityname);
			Gson gson=new Gson();
			WeatherInfo object = gson.fromJson(jsonStr, WeatherInfo.class);
	        if(object!=null){
	        	Result result=object.getResult();
	 	        return result;
	        }
		}
					
		return null;		
	}
	
	/**
	 * 更新城市天气信息
	 * @param cityname
	 */
	public void updateWeather(Context context,String cityname){
		UpdateWeatherManager.getInstance().update(UpdateWeatherManager.UPDATE_FROM_USER, cityname);
		context.sendBroadcast(new Intent(ApplicationInfo.BROADCAST_REFRESH_VIEW));//通知更新页面
	}	
}
