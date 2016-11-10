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
		
		//获取天气数据
		WeatherConcrete weather=new WeatherConcrete(cityName);
		//更新天气文件
		WeatherFileManager.writeToFile(weather.getCityName(), weather.getJsonStrResult());
	}

}
