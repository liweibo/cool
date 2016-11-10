package yjj.weather.net;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import yjj.weather.net.data.Result;
import yjj.weather.net.data.WeatherInfo;

import android.util.Log;

import com.google.gson.Gson;

public class WeatherNet {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
  
    //配置您申请的KEY
    public static final String APPKEY ="04987c9a065edc60b7e4bea1f81a5a3f";
    //接口URL
    public static String url="http://op.juhe.cn/onebox/weather/query";//请求接口地址
    
    /**
     * 传递参数，返回查询结果的Json对象
     * @param params 查询的各个参数
     * @return 查询结果
     */
    public static Result getRequestByParams(Map<String,String> params){
        String result =null;
        Gson gson=new Gson();
        try {
            result =net(url, params, "GET");
            WeatherInfo object = gson.fromJson(result, WeatherInfo.class);
            if(object.getError_code()==0){
                return object.getResult();
            }else{
                Log.d("debug", object.getError_code()+":"+object.getReason());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }    	
        return null;
    }
    
    /**
     * 根据ip地址或者域名查询实际地址，地址信息封装在WeatherInfo中
     * @param ip_domain 
     * @return
     */
    public static Result getWeatherInformation(String cityname){
        Map<String,String> params = new HashMap<String,String>();//请求参数
        params.put("cityname",cityname);
        params.put("dtype","json");//返回数据的格式,xml或json，默认json
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        Result request=getRequestByParams(params);
        return request;
    }
    
    /**
     * 从网络获取指定城市的json字符串
     */
    public static String getWeatherStrByNet(String cityname){
        Map<String,String> params = new HashMap<String,String>();//请求参数
        params.put("cityname",cityname);
        params.put("dtype","json");//返回数据的格式,xml或json，默认json
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        String result=null;
        try {
			result=netInOtherThread(url, params, "GET");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("debug", "位置:yjj.weather.net.WeatherNet   getWeatherStrByNet方法");
			e.printStackTrace();
		}
    	return result;
    }
    
    /**
    *用另一个线程来对指定url进行下载
    * @param strUrl 请求地址
    * @param params 请求参数
    * @param method 请求方法
    * @return  网络请求字符串
    * @throws Exception
    */
    public synchronized static String netInOtherThread(final String strUrl, final Map<String,String> params,final String method){
    	    	
    	Thread thread=new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					jsonStr=net(strUrl,params,method);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    		
    	});
    	thread.start();
    	try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String str=jsonStr;
    	jsonStr=null;
    	return str;
    	
    }
  
    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
   public static String net(String strUrl, Map<String,String> params,String method) throws Exception {
	   
	   	   String rs = null;
	       HttpURLConnection conn = null;
	       BufferedReader reader = null;
	       
	       try {
	           StringBuffer sb = new StringBuffer();
	           if(method==null || method.equals("GET")){
	               strUrl = strUrl+"?"+urlencode(params);
	           }
	           URL url = new URL(strUrl);
	           conn = (HttpURLConnection) url.openConnection();
	           if(method==null || method.equals("GET")){
	               conn.setRequestMethod("GET");
	           }else{
	               conn.setRequestMethod("POST");
	               conn.setDoOutput(true);
	           }
	           //conn.setRequestProperty("User-agent", userAgent);
	           conn.setUseCaches(false);
	           conn.setConnectTimeout(DEF_CONN_TIMEOUT);
	           conn.setReadTimeout(DEF_READ_TIMEOUT);
	           conn.setInstanceFollowRedirects(false);     
	           conn.connect();
	           if (params!= null && method.equals("POST")) {
	               try {
	                   DataOutputStream out = new DataOutputStream(conn.getOutputStream());
	                   out.writeBytes(urlencode(params));
	               } catch (Exception e) {
	                   e.printStackTrace();
	               }
	           }
	           InputStream is = conn.getInputStream();
	           reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
	           String strRead = null;
	           while ((strRead = reader.readLine()) != null) {
	               sb.append(strRead);
	           }
	           rs = sb.toString();
	       } catch (IOException e) {
	           e.printStackTrace();
	       } finally {
	           if (reader != null) {
	               reader.close();
	           }
	           if (conn != null) {
	               conn.disconnect();
	           }
	       }
	       return rs;
    }
  
    //将map型转为请求参数型
    public static String urlencode(Map<String,String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,String> i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    
    volatile private static String jsonStr;
}
