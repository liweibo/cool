package com.example.weather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.weather.WeatherFragment.WeatherFragmentCallback;
import com.viewpagerswitch.JazzyViewPager;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;
import yjj.weather.application.ApplicationInfo;
import yjj.weather.application.UserInterface;
import yjj.weather.tool.Network;
import yjj.weather.tool.NoStatus;

public class MainActivity extends FragmentActivity implements OnClickListener, WeatherFragmentCallback {

    public static final int REFRESH_VIEW = 0x01;
    public static final int REFRESH_GIF_VIEW_BY_FRAGMENT = 0x02;

    public static final int CITY_NUM = 4;
    String currentCityName = null;
    private JazzyViewPager mJazzy;
    private ArrayList<Fragment> fragments;
    private Button more;
    private Button update;
    private ImageView[] dots = new ImageView[CITY_NUM];
    private GifImageView weatherBackground;
    private int currentPosition = 0;
    WeatherFragment fragment = null;

    private RefreshviewReceiver receiver = new RefreshviewReceiver();

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case REFRESH_VIEW:
                    //Fragment的数据
                    fragment = (WeatherFragment) fragments.get(currentPosition);
                    fragment.updateView();
                    break;
            }
        }

        ;
    };
    private String currentCityNames = null;

    private class DownloadTask extends AsyncTask<String, String, String> {

        private String cityName;
        private int currentPosition;
        private WeatherFragment fragment;

        public DownloadTask(String cityName, int currentPosition) {
            this.cityName = cityName;
            this.currentPosition = currentPosition;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            fragment = (WeatherFragment) MainActivity.this.fragments.get(currentPosition);
            fragment.progressbar.setVisibility(View.VISIBLE);


        }

        @Override
        protected String doInBackground(String... arg0) {
            // TODO Auto-generated method stub
            UserInterface.getInstance().updateWeather(MainActivity.this, cityName);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            fragment.progressbar.setVisibility(View.INVISIBLE);
        }
    }




    private class RefreshDownLoad extends AsyncTask<String, String, String> {

        private String cityName;
        private int currentPosition;
        private WeatherFragment fragment;

        public RefreshDownLoad(String cityName, int currentPosition) {
            this.cityName = cityName;
            this.currentPosition = currentPosition;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            fragment = (WeatherFragment) MainActivity.this.fragments.get(currentPosition);
            fragment.progressbar.setVisibility(View.VISIBLE);


        }

        @Override
        protected String doInBackground(String... arg0) {
            // TODO Auto-generated method stub
            UserInterface.getInstance().updateWeather(MainActivity.this, cityName);
            SystemClock.sleep(2500);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            fragment.progressbar.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("debug", "activity oncreate");

        setContentView(R.layout.main);

        setupJazziness(JazzyViewPager.TransitionEffect.ZoomIn);
        View mainStub = findViewById(R.id.ll_main_stub);
        NoStatus.initAfterSetContentView(MainActivity.this, mainStub);

        initWidget();
        initMainViewPager();
        initWidgetInfo();
        registReceiver();



        UserInterface.getInstance().setDefaultCity(UserInterface.DEFAULT_CITY_NAME1, "湘潭");
        UserInterface.getInstance().setDefaultCity(UserInterface.DEFAULT_CITY_NAME2, "株洲");
        UserInterface.getInstance().setDefaultCity(UserInterface.DEFAULT_CITY_NAME3, "长沙");
        UserInterface.getInstance().setDefaultCity(UserInterface.DEFAULT_CITY_NAME4, "沈阳");


        switch (currentPosition) {
            case 0:
                currentCityNames = UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME1);
                break;
            case 1:
                currentCityNames = UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME2);
                break;
            case 2:
                currentCityNames = UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME3);
                break;
            case 3:
                currentCityNames = UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME4);
                break;

        }


        UserInterface.getInstance().updateWeather(MainActivity.this, currentCityNames);


    }


    /**
     * 初始化组件,获得各个组件引用
     */
    private void initWidget() {
        mJazzy = (JazzyViewPager) findViewById(R.id.jazzy_pager);
        dots[0] = (ImageView) findViewById(R.id.dot1);
        dots[1] = (ImageView) findViewById(R.id.dot2);
        dots[2] = (ImageView) findViewById(R.id.dot3);
        dots[3] = (ImageView) findViewById(R.id.dot4);
        more = (Button) findViewById(R.id.more);
        more.setOnClickListener(this);
        update = (Button) findViewById(R.id.update_weather);
        update.setOnClickListener(this);
    }


    /**
     * 初始化主菜单中的ViewPager
     */
    private void initMainViewPager() {
        weatherBackground = (GifImageView) findViewById(R.id.weather_background);
        //将要加载的fragment加入进来
        fragments = new ArrayList<Fragment>();
        WeatherFragment f1 = new WeatherFragment();
        f1.tag = 1;
        WeatherFragment f2 = new WeatherFragment();
        f2.tag = 2;
        WeatherFragment f3 = new WeatherFragment();
        f3.tag = 3;
        WeatherFragment f4 = new WeatherFragment();
        f4.tag = 4;
        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
        fragments.add(f4);
        mJazzy.setAdapter(new WeatherPagerAdapter(getSupportFragmentManager(), fragments));

        mJazzy.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                currentPosition = position;
                mJazzy.setCurrentItem(position);
                setSelectedDot(position);
            }
        });

    }

    /**
     * 初始化组件信息
     */
    private void initWidgetInfo() {
        mJazzy.setCurrentItem(0);
        setSelectedDot(0);
    }

    /**
     * MainViewPager的适配器，用来控制Activity和Fragment的交互
     *
     * @author Administrator
     */
    private class WeatherPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;

        public WeatherPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            // TODO Auto-generated method stub
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return fragments.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            mJazzy.setObjectForPosition(fragments.get(position), position);
            return super.instantiateItem(container, position);
        }
    }

    /**
     * 设置小圆点
     *
     * @param position
     */
    private void setSelectedDot(int position) {
        for (int i = 0; i < dots.length; i++) {
            if (i == position) {
                dots[i].setBackgroundResource(R.drawable.selected_dot);
            } else {
                dots[i].setBackgroundResource(R.drawable.unselected_dot);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.more:
                Intent intent = new Intent(ApplicationInfo.ACTIVITY_DEFAULT_CITY);
                startActivity(intent);
                break;
            case R.id.update_weather:
                if (Network.isNetworkAvailable(this)) {//检测是否打开了网络

                    switch (currentPosition) {
                        case 0:
                            currentCityName = UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME1);
                            break;
                        case 1:
                            currentCityName = UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME2);
                            break;
                        case 2:
                            currentCityName = UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME3);
                            break;
                        case 3:
                            currentCityName = UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME4);
                            break;
                    }
                    if (currentCityName != null) {
                        RefreshDownLoad task = new RefreshDownLoad(currentCityName, currentPosition);
                        task.execute();
                    }
                } else {
                    Toast.makeText(this, "请打开网络", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.d("debug", "activity onstart");
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.d("debug", "activity onresume");
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unRegistReceiver();
    }

    /**
     * 用于更新界面
     *
     * @author Administrator
     */
    private class RefreshviewReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ApplicationInfo.BROADCAST_REFRESH_VIEW)) {
                handler.sendEmptyMessage(REFRESH_VIEW);//通知更新页面
            }
        }

    }

    //注册广播
    private void registReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ApplicationInfo.BROADCAST_REFRESH_VIEW);
        registerReceiver(receiver, filter);
    }

    //注销广播
    private void unRegistReceiver() {
        unregisterReceiver(receiver);
    }

    @Override
    public void refreshGifView() {
        // TODO Auto-generated method stub
        handler.sendEmptyMessage(REFRESH_GIF_VIEW_BY_FRAGMENT);
    }


    private void setupJazziness(JazzyViewPager.TransitionEffect effect) {
        mJazzy = (JazzyViewPager) findViewById(R.id.jazzy_pager);
        mJazzy.setTransitionEffect(effect);

        mJazzy.setPageMargin(30);
//		mJazzy.setOutlineEnabled(true);
    }

}
