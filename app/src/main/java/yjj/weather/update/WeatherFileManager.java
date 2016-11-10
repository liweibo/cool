package yjj.weather.update;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;

/**
 * 用于管理天气文件
 * 天气文件管理，一律用"中文地区名.txt"保存
 * @author Administrator
 *
 */
public class WeatherFileManager {
	
	/*
	 * 各个城市天气文件的位置
	 */
	public static final String WEATHER_FILE_PATH=Environment.getExternalStorageDirectory()+"/myweather";
	
	//WeatherFileManager类被加载时就主动执行这个静态代码块，且只执行一次。
	static{
		mkDir();
	}
	/*
	 * 创建保存天气文件的路径
	 */
	private synchronized static void mkDir(){
		File path=new File(WEATHER_FILE_PATH);
		if(!path.exists()){
			path.mkdirs();
		}
	}
	
	/**
	 * 把指定城市的json文件保存
	 * @param cityName 城市名称
	 * @param jsonInfo 具体的json内容
	 */
	public static void writeToFile(String cityName,String jsonInfo){
		if(jsonInfo==null||jsonInfo.isEmpty()){
			return;
		}
		File file=new File(WEATHER_FILE_PATH+"/"+cityName+".txt");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e("debug", cityName+".weather文件创建时出现异常.位置:yjj.weather.update.WeatherFileManager");
				e.printStackTrace();
			}
		}
		try {
			FileWriter fr=new FileWriter(file);
			BufferedWriter bw=new BufferedWriter(fr,1024);
			bw.write(jsonInfo);
			bw.flush();
			bw.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("debug", cityName+".weather写入文件时出现异常.位置:yjj.weather.update.WeatherFileManager");
			e.printStackTrace();
		}
	}
	
	/**
	 * 从保存的.weather文件中读取出json串
	 * @param cityName
	 * @return
	 */
	public static String ReadFromFile(String cityName){
		File file=new File(WEATHER_FILE_PATH+"/"+cityName+".txt");
		String jsonResult=null;
		try {
			FileReader fr= new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			StringBuilder sb=new StringBuilder();
			String tmp;
			while((tmp=br.readLine())!=null){
				sb.append(tmp);
			}
			jsonResult=sb.toString();			
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e("debug", cityName+".weather读取文件时出现异常.位置:yjj.weather.update.WeatherFileManager");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("debug", cityName+".weather读取文件时出现异常.位置:yjj.weather.update.WeatherFileManager");
			e.printStackTrace();
		}
		
		return jsonResult;
	}
}
