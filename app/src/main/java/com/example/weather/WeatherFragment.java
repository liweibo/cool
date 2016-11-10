package com.example.weather;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import progressbar.LoadingView;
import yjj.weather.application.UserInterface;
import yjj.weather.net.data.Result;

public class WeatherFragment extends Fragment {


    private RelativeLayout layout;
    private TextView cityNameView;
    private TextView temperature;
    private TextView wind_power;
    private TextView wind_direction;
    private TextView chuanyi_detail;
    private TextView updatetime;
    private Result weatherObject;
    public LoadingView progressbar;
    private String cityName;
    public int tag;
    private TextView future1;
    private TextView future2;
    private TextView future3;
    private TextView future4;
    private TextView futureWea1;
    private TextView futureWea2;
    private TextView futureWea3;
    private TextView futureWea4;
    private TextView tvTem1;
    private TextView tvTem2;
    private TextView tvTem3;
    private TextView tvTem4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        Log.d("debug", "fragment oncreate");
        super.onCreate(savedInstanceState);
        getDatas();
    }

    /**
     * 在Activity中显示Fragment时会被调用
     */
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        Log.d("debug", "fragment onAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        Log.d("debug", "fragment onCreateView" + ":" + tag);
        layout = (RelativeLayout) inflater.inflate(R.layout.fragment_weather, null);
        initWidgets();
        showDatas();
        return layout;
    }

    //初始化组件
    private void initWidgets() {
        cityNameView = (TextView) layout.findViewById(R.id.cityname);
        temperature = (TextView) layout.findViewById(R.id.temperature);
     //   weather = (TextView) layout.findViewById(R.id.weather);
        wind_power = (TextView) layout.findViewById(R.id.wind_power);
        wind_direction = (TextView) layout.findViewById(R.id.wind_direction);
        chuanyi_detail = (TextView) layout.findViewById(R.id.chuanyi_detail);
        updatetime = (TextView) layout.findViewById(R.id.updatetime);
        progressbar = (LoadingView) layout.findViewById(R.id.progressBar);

        //我新添加的
        future1 = (TextView) layout.findViewById(R.id.tv_future1);
        future2 = (TextView) layout.findViewById(R.id.tv_future2);
        future3 = (TextView) layout.findViewById(R.id.tv_future3);
        future4 = (TextView) layout.findViewById(R.id.tv_future4);

        futureWea1 = (TextView) layout.findViewById(R.id.tv_future_wea1);
        futureWea2 = (TextView) layout.findViewById(R.id.tv_future_wea2);
        futureWea3 = (TextView) layout.findViewById(R.id.tv_future_wea3);
        futureWea4 = (TextView) layout.findViewById(R.id.tv_future_wea4);

        tvTem1 = (TextView) layout.findViewById(R.id.tv_future_tem1);
        tvTem2 = (TextView) layout.findViewById(R.id.tv_future_tem2);
        tvTem3 = (TextView) layout.findViewById(R.id.tv_future_tem3);
        tvTem4 = (TextView) layout.findViewById(R.id.tv_future_tem4);

    }

    //获取数据
    private void getDatas() {
        switch (tag) {
            case 1:
                cityName = UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME1);
                break;
            case 2:
                cityName = UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME2);
                break;
            case 3:
                cityName = UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME3);
                break;
            case 4:
                cityName = UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME4);
                break;
        }
        if (cityName != null) {
            weatherObject = UserInterface.getInstance().getWeather(cityName);
        }
    }

    //显示数据
    private void showDatas() {
        if (weatherObject == null) {
            cityNameView.setText("请刷新天气");
            temperature.setText("");
            //weather.setText("");
            wind_direction.setText("");
            wind_power.setText("");
            chuanyi_detail.setText("");
            updatetime.setText("");

            future1.setText("");
            future2.setText("");
            future3.setText("");
            future4.setText("");

            futureWea1.setText("");
            futureWea2.setText("");
            futureWea3.setText("");
            futureWea4.setText("");

            tvTem1.setText("");
            tvTem2.setText("");
            tvTem3.setText("");
            tvTem4.setText("");

        } else {
            cityNameView.setText(cityName+" "+weatherObject.getData().getRealtime().getWeather().getInfo());
            temperature.setText(weatherObject.getData().getRealtime().getWeather().getTemperature() + "°C");
           // weather.setText(weatherObject.getData().getRealtime().getWeather().getInfo());
            wind_direction.setText(weatherObject.getData().getRealtime().getWind().getDirect());
            wind_power.setText(weatherObject.getData().getRealtime().getWind().getPower());
            chuanyi_detail.setText(weatherObject.getData().getLife().getInfo().getChuanyi().get(1));
            updatetime.setText(weatherObject.getData().getRealtime().getDate() + "  " + weatherObject.getData().getRealtime().getTime()
                    + " 星期" + weatherObject.getData().getWeather().get(0).getWeek()

            );


            future1.setText("星期" + weatherObject.getData().getWeather().get(1).getWeek());
            future2.setText("星期" + weatherObject.getData().getWeather().get(2).getWeek());
            future3.setText("星期" + weatherObject.getData().getWeather().get(3).getWeek());
            future4.setText("星期" + weatherObject.getData().getWeather().get(4).getWeek());

            futureWea1.setText(weatherObject.getData().getWeather().get(1).getInfo().getDay().get(1));
            futureWea2.setText(weatherObject.getData().getWeather().get(2).getInfo().getDay().get(1));
            futureWea3.setText(weatherObject.getData().getWeather().get(3).getInfo().getDay().get(1));
            futureWea4.setText(weatherObject.getData().getWeather().get(4).getInfo().getDay().get(1));

            tvTem1.setText(weatherObject.getData().getWeather().get(1).getInfo().getDay().get(0)
                    + " / " + weatherObject.getData().getWeather().get(1).getInfo().getDay().get(2) + "℃");
            tvTem2.setText(weatherObject.getData().getWeather().get(2).getInfo().getDay().get(0)
                    + " / " + weatherObject.getData().getWeather().get(2).getInfo().getDay().get(2) + "℃");
            tvTem3.setText(weatherObject.getData().getWeather().get(3).getInfo().getDay().get(0)
                    + " / " + weatherObject.getData().getWeather().get(3).getInfo().getDay().get(2) + "℃");
            tvTem4.setText(weatherObject.getData().getWeather().get(4).getInfo().getDay().get(0)
                    + " / " + weatherObject.getData().getWeather().get(4).getInfo().getDay().get(2) + "℃");
            if (weatherObject.getData().getWeather().get(0).getInfo().getDay().get(1).contains("晴")) {
                layout.setBackgroundResource(R.drawable.fraback);
            } else if (weatherObject.getData().getWeather().get(0).getInfo().getDay().get(1).contains("云")) {
                layout.setBackgroundResource(R.drawable.cloud);
            } else if(weatherObject.getData().getWeather().get(0).getInfo().getDay().get(1).contains("雨")) {
                layout.setBackgroundResource(R.drawable.rain);

            }else if(weatherObject.getData().getWeather().get(0).getInfo().getDay().get(1).contains("雪")) {
                layout.setBackgroundResource(R.drawable.snow);

            }

        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        // TODO Auto-generated method stub
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.d("debug", "fragement setUserVisibleHint");
            WeatherFragmentCallback callback = (WeatherFragmentCallback) getActivity();
            callback.refreshGifView();
        }
    }

    /**
     * 主要用于Activity获取fragment中的天气数据
     *
     * @return
     */
    public Result getWeatherObject() {
        return weatherObject;
    }

    /**
     * 更新界面
     */
    public void updateView() {
        getDatas();
        showDatas();
    }

    public static interface WeatherFragmentCallback {
        public void refreshGifView();
    }
}
