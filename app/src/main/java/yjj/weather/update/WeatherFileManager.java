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
 * ���ڹ��������ļ�
 * �����ļ�����һ����"���ĵ�����.txt"����
 * @author Administrator
 *
 */
public class WeatherFileManager {
	
	/*
	 * �������������ļ���λ��
	 */
	public static final String WEATHER_FILE_PATH=Environment.getExternalStorageDirectory()+"/myweather";
	
	//WeatherFileManager�౻����ʱ������ִ�������̬����飬��ִֻ��һ�Ρ�
	static{
		mkDir();
	}
	/*
	 * �������������ļ���·��
	 */
	private synchronized static void mkDir(){
		File path=new File(WEATHER_FILE_PATH);
		if(!path.exists()){
			path.mkdirs();
		}
	}
	
	/**
	 * ��ָ�����е�json�ļ�����
	 * @param cityName ��������
	 * @param jsonInfo �����json����
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
				Log.e("debug", cityName+".weather�ļ�����ʱ�����쳣.λ��:yjj.weather.update.WeatherFileManager");
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
			Log.e("debug", cityName+".weatherд���ļ�ʱ�����쳣.λ��:yjj.weather.update.WeatherFileManager");
			e.printStackTrace();
		}
	}
	
	/**
	 * �ӱ����.weather�ļ��ж�ȡ��json��
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
			Log.e("debug", cityName+".weather��ȡ�ļ�ʱ�����쳣.λ��:yjj.weather.update.WeatherFileManager");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("debug", cityName+".weather��ȡ�ļ�ʱ�����쳣.λ��:yjj.weather.update.WeatherFileManager");
			e.printStackTrace();
		}
		
		return jsonResult;
	}
}
