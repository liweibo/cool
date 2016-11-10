package yjj.weather.net;

import com.google.gson.Gson;

import android.util.Log;
import yjj.weather.net.data.Result;
import yjj.weather.net.data.WeatherInfo;

/**
 * ��װָ�����е�������Ϣ,��Щ������Ϣ��WeatherNet�л��
 * ���������ṩString���ͺ�Result���͵����ָ�ʽ
 * @author Administrator
 *
 */
public class WeatherConcrete {
	private String cityName;
	private long updateTime;
	private String jsonStrResult;
	private Result objectResult;
	public WeatherConcrete(String cityName) {
		this.cityName=cityName;
		updateTime=System.currentTimeMillis();
		
		Thread thread=new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				net();
			}
			
		});
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getCityName() {
		return cityName;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	/**
	 * �������ȡ������Ϣ������װ
	 */
	private void net(){
		jsonStrResult=WeatherNet.getWeatherStrByNet(cityName);
		if(jsonStrResult==null){
			return;
		}
		Gson gson=new Gson();
		Log.d("debug2", jsonStrResult);
		WeatherInfo object = gson.fromJson(jsonStrResult, WeatherInfo.class);
        if(object!=null&&object.getError_code()==0){
        	objectResult=object.getResult();
        }else{
            //Log.d("debug", object.getError_code()+":"+object.getReason());
        }
	}
	public Result getObjectResult() {
		return objectResult;
	}
	public String getJsonStrResult() {
		return jsonStrResult;
	}
}
