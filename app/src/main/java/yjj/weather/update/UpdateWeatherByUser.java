package yjj.weather.update;

import yjj.weather.net.WeatherConcrete;

public class UpdateWeatherByUser implements UpdateWeather{
	
	private String cityName;
	
	public UpdateWeatherByUser(String cityName) {
		// TODO Auto-generated constructor stub
		this.cityName=cityName;
	}

	@Override
	public void updateWeatherFile() {
		
		//��ȡ��������
		WeatherConcrete weather=new WeatherConcrete(cityName);
		//���������ļ�
		WeatherFileManager.writeToFile(weather.getCityName(), weather.getJsonStrResult());
	}

}
