package yjj.weather.net.data;

public class PM2_5 {
	private String key;
	private int show_desc;
	private PM2_5InPM2_5 pm25;
	private String dateTime;
	private String cityName;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getShow_desc() {
		return show_desc;
	}
	public void setShow_desc(int show_desc) {
		this.show_desc = show_desc;
	}
	public PM2_5InPM2_5 getPm25() {
		return pm25;
	}
	public void setPm25(PM2_5InPM2_5 pm25) {
		this.pm25 = pm25;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
