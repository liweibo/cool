package yjj.weather.update;

import java.util.ArrayList;
import java.util.Set;

import yjj.weather.application.UserInterface;
import yjj.weather.net.WeatherConcrete;

public class UpdateWeatherByWifi implements UpdateWeather{

	@Override
	public void updateWeatherFile() {
		// ��ȡȫ��Ĭ�ϳ���
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
		//����Ĭ�ϳ��е�����
		Thread[] downloadThreads=new Thread[citynames.size()];
		final WeatherConcrete[] weathers=new WeatherConcrete[citynames.size()];//�������ص�json�ַ���
		int pointer=0;//ָ�������ѭ������
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
		for(Thread t:downloadThreads){//ȫ�������߳̽���
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//��ȫ��������Ϣ�������ļ�
		for(WeatherConcrete weather:weathers){
			String jsonResult=weather.getJsonStrResult();
			if(jsonResult!=null){
				WeatherFileManager.writeToFile(weather.getCityName(), jsonResult);
			}			
		}		
	}

}
