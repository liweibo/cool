package yjj.weather.application;

public interface ApplicationInfo {
	/*
	 * ���������洢��SharedPreferences��
	 */
	public static final String DISTRICT="district";//��������
	/*
	 * Ĭ�ϳ��У��洢��SharedPreferences��
	 */
	public static final String DEFALUT_CITY="default_city";//Ĭ�ϳ���
	/*
	 * Service����
	 */
	public static final String SERVICE_UPDATE_WEATHER_SERVICE="com.example.weather.intent.updateweather";
	/*
	 * Activity����
	 */
	public static final String ACTIVITY_DEFAULT_CITY="com.example.weather.intent.defalutcity";
	/*
	 * Broadcast���� 
	 */
	public static final String BROADCAST_REFRESH_VIEW="com.example.weather.intent.refreshview";
}
