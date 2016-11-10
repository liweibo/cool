package yjj.weather.update;

import java.util.ArrayList;
import java.util.Set;

import yjj.weather.application.UserInterface;
import yjj.weather.net.WeatherConcrete;

public class UpdateWeatherByWifi implements UpdateWeather{

	@Override
	public void updateWeatherFile() {
		// 获取全部默认城市
		ArrayList<String> citynames=new ArrayList<String>();
		String tmp=null;
		if((tmp=UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME1))!=null){
			citynames.add(tmp);
		}
		if((tmp=UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME2))!=null){
			citynames.add(tmp);
		}
		if((tmp=UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME3))!=null){
			citynames.add(tmp);
		}
		if((tmp=UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME4))!=null){
			citynames.add(tmp);
		}
		//下载默认城市的天气
		Thread[] downloadThreads=new Thread[citynames.size()];
		final WeatherConcrete[] weathers=new WeatherConcrete[citynames.size()];//保存下载的json字符串
		int pointer=0;//指向下面的循环进度
		for(final String cityname:citynames){
			final int currentPointer=pointer;
			Thread th=new Thread(new Runnable(){
				@Override
				public void run() {
					WeatherConcrete wc=new WeatherConcrete(cityname);
					weathers[currentPointer]=wc;
				}				
			});
			downloadThreads[currentPointer]=th;
			th.start();
			pointer++;
		}
		for(Thread t:downloadThreads){//全部下载线程结束
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//将全部天气信息保存至文件
		for(WeatherConcrete weather:weathers){
			String jsonResult=weather.getJsonStrResult();
			if(jsonResult!=null){
				WeatherFileManager.writeToFile(weather.getCityName(), jsonResult);
			}			
		}		
	}

}
