package yjj.weather.net.data;

import java.util.List;

public class Data {
	private Realtime realtime;
	private Life life;	
	private List<WeatherInData> weather;
	private PM2_5 pm25;
	private String date;
	private int isForeign;
	public Realtime getRealtime() {
		return realtime;
	}
	public void setRealtime(Realtime realtime) {
		this.realtime = realtime;
	}
	public Life getLife() {
		return life;
	}
	public void setLife(Life life) {
		this.life = life;
	}
	public List<WeatherInData> getWeather() {
		return weather;
	}
	public void setWeather(List<WeatherInData> weather) {
		this.weather = weather;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIsForeign() {
		return isForeign;
	}
	public void setIsForeign(int isForeign) {
		this.isForeign = isForeign;
	}
	public PM2_5 getPm25() {
		return pm25;
	}
	public void setPm25(PM2_5 pm25) {
		this.pm25 = pm25;
	}
}
