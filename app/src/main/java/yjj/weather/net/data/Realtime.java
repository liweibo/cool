package yjj.weather.net.data;

public class Realtime {
	private Wind wind;
	private String time;
	private WeatherInRealtime weather;
	private long dataUptime;
	private String date;	
	private String city_code;
	private String city_name;	
	private int week;
	private String moon;
	
	
	
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public String getMoon() {
		return moon;
	}
	public void setMoon(String moon) {
		this.moon = moon;
	}
	public long getDataUptime() {
		return dataUptime;
	}
	public void setDataUptime(long dataUptime) {
		this.dataUptime = dataUptime;
	}
	public WeatherInRealtime getWeather() {
		return weather;
	}
	public void setWeather(WeatherInRealtime weather) {
		this.weather = weather;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
}
