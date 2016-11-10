package yjj.weather.application;

/**
 * 观察者模式，通知UserInterface去更新天气信息
 * @author Administrator
 *
 */
public interface UpdateWeatherNotifier {
	public void notifyUpdateWeather(String cityName);
}
