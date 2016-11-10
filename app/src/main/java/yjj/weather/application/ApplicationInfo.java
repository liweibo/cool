package yjj.weather.application;

public interface ApplicationInfo {
	/*
	 * 地区管理，存储在SharedPreferences中
	 */
	public static final String DISTRICT="district";//地区管理
	/*
	 * 默认城市，存储在SharedPreferences中
	 */
	public static final String DEFALUT_CITY="default_city";//默认城市
	/*
	 * Service名称
	 */
	public static final String SERVICE_UPDATE_WEATHER_SERVICE="com.example.weather.intent.updateweather";
	/*
	 * Activity名称
	 */
	public static final String ACTIVITY_DEFAULT_CITY="com.example.weather.intent.defalutcity";
	/*
	 * Broadcast名称 
	 */
	public static final String BROADCAST_REFRESH_VIEW="com.example.weather.intent.refreshview";
}
