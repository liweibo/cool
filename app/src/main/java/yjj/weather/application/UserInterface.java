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
 * �û��ӿڣ���������ʾ���ṩ���ݷ���
 * ��Ҫ���ܰ���������Ĭ�ϳ��У��ṩָ�����е�������Ϣ
 * @author Administrator
 *
 */
public class UserInterface {
	
	public static final String DEFAULT_CITY_NAME1="��̶";
	public static final String DEFAULT_CITY_NAME2="��ɳ";
	public static final String DEFAULT_CITY_NAME3="����";
	public static final String DEFAULT_CITY_NAME4="����";
	private static UserInterface userInterface;
	
	/**
	 * ����Ĭ�ϳ���
	 * @param cityname
	 */
	public static void setDefaultCity(String whichCity,String cityname){
		SharedPreferences.Editor editor=MyApplication.getContext().getSharedPreferences(ApplicationInfo.DEFALUT_CITY, Context.MODE_WORLD_WRITEABLE).edit();
		editor.putString(whichCity, cityname);
		editor.commit();
	}
	
	/**
	 * ���Ĭ�ϳ���
	 * @param whichCity ��ѡ����ֵΪDEFAULT_CITY_NAME*
	 * @return
	 */
	public static String getDefaultCity(String whichCity){
		SharedPreferences sf=MyApplication.getContext().getSharedPreferences(ApplicationInfo.DEFALUT_CITY, Context.MODE_WORLD_WRITEABLE);
		return sf.getString(whichCity, null);
	}
	
	public UserInterface() {
		// TODO Auto-generated constructor stub
	}
	
	public static UserInterface getInstance(){//����ʽд��
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
	 * ����ָ�����л�ȡ��������
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
	 * ���³���������Ϣ
	 * @param cityname
	 */
	public void updateWeather(Context context,String cityname){
		UpdateWeatherManager.getInstance().update(UpdateWeatherManager.UPDATE_FROM_USER, cityname);
		context.sendBroadcast(new Intent(ApplicationInfo.BROADCAST_REFRESH_VIEW));//֪ͨ����ҳ��
	}	
}
