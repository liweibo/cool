package yjj.weather.update;

public class UpdateWeatherManager {
	
	public static final int UPDATE_FROM_USER=0X01;//用户更新
	public static final int UPDATE_FROM_WIFI_STATE=0x02;//wifi连接更新
	
	private static UpdateWeatherManager manager;
	
	private  UpdateWeatherManager() {
		// TODO Auto-generated constructor stub
	}



	public static UpdateWeatherManager getInstance(){
		if(manager==null){
			synchronized (UpdateWeatherManager.class) {
				if(manager==null){
					manager=new UpdateWeatherManager();
				}
			}
		}
		return manager;
	}
	
	/**
	 * 更新天气数据，mode是更新的模式
	 * @param mode
	 * @param cityname
	 */
	public void update(int mode,String cityname){
		UpdateWeather update = null;
		switch (mode) {
		case UPDATE_FROM_USER:
			update=new UpdateWeatherByUser(cityname);
			break;
		case UPDATE_FROM_WIFI_STATE:
			update=new UpdateWeatherByWifi();
			break;
		default:
			break;
		}
		update.updateWeatherFile();
	}
	
}
