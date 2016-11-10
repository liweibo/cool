package com.example.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.viewpagerswitch.JazzyViewPager;

import java.util.ArrayList;

import yjj.weather.tool.NoStatus;
public class GuideActivity extends Activity {
    private static final int[] mImageIds = new int[] { R.drawable.guide_1,
            R.drawable.guide_2, R.drawable.guide_3 };

    private JazzyViewPager vpGuide;
    private ArrayList<ImageView> mImageViewList;

    private LinearLayout llPointGroup;// ����Բ��ĸ��ؼ�

    private int mPointWidth;// Բ���ľ���

    private View viewRedPoint;// С���

    private Button btnStart;// ��ʼ����
    private RelativeLayout rootGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ������
        setContentView(R.layout.activity_guide);
        vpGuide = (JazzyViewPager) findViewById(R.id.vp_guide);
        llPointGroup = (LinearLayout) findViewById(R.id.ll_point_group);
        viewRedPoint = findViewById(R.id.view_red_point);
        btnStart = (Button) findViewById(R.id.btn_start);
        rootGuide = (RelativeLayout) findViewById(R.id.rl_root_guide);
        View lll = findViewById(R.id.linear_bar);
        NoStatus.initAfterSetContentView(this, lll);

        setupJazziness(JazzyViewPager.TransitionEffect.CubeOut);


        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // ��ת��ҳ��
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });

        initViews();
        vpGuide.setAdapter(new GuideAdapter());

        vpGuide.setOnPageChangeListener(new GuidePageListener());
    }

    /**
     * ��ʼ������
     */
    private void initViews() {

      //  vpGuide.setCurrentItem(0);
        mImageViewList = new ArrayList<ImageView>();

        // ��ʼ������ҳ��3��ҳ��
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(mImageIds[i]);// ��������ҳ����
            mImageViewList.add(image);
        }

      RelativeLayout.LayoutParams paramss = new RelativeLayout.LayoutParams(
              15, 15);
        viewRedPoint.setLayoutParams(paramss);


        // ��ʼ������ҳ��СԲ��
        for (int i = 0; i < mImageIds.length; i++) {
            View point = new View(this);
            point.setBackgroundResource(R.drawable.shape_point_gray);// ��������ҳĬ��Բ��

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    15, 15);
            if (i > 0) {
                params.leftMargin = 30;// ����Բ����
            }

            point.setLayoutParams(params);// ����Բ��Ĵ�С

            llPointGroup.addView(point);// ��Բ����Ӹ����Բ���
        }

        // ��ȡ��ͼ��, ��layout�����¼����м���
        llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    // ��layoutִ�н�����ص��˷���
                    @Override
                    public void onGlobalLayout() {
                        System.out.println("layout ����");
                        llPointGroup.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                        mPointWidth = llPointGroup.getChildAt(1).getLeft()
                                - llPointGroup.getChildAt(0).getLeft();
                        System.out.println("Բ�����:" + mPointWidth);
                    }
                });
    }

    /**
     * ViewPager����������
     *
     * @author Kevin
     *
     */
    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            vpGuide.setObjectForPosition(mImageViewList.get(position), position);
            return mImageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * viewpager�Ļ�������
     *
     * @author Kevin
     *
     */
    class GuidePageListener implements ViewPager.OnPageChangeListener {

        // �����¼�
        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
            // System.out.println("��ǰλ��:" + position + ";�ٷֱ�:" + positionOffset
            // + ";�ƶ�����:" + positionOffsetPixels);
            int len = (int) (mPointWidth * positionOffset) + position
                    * mPointWidth;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewRedPoint
                    .getLayoutParams();// ��ȡ��ǰ���Ĳ��ֲ���
            params.leftMargin = len;// ������߾�

            viewRedPoint.setLayoutParams(params);// ���¸�С������ò��ֲ���
        }

        // ĳ��ҳ�汻ѡ��
        @Override
        public void onPageSelected(int position) {
            vpGuide.setCurrentItem(position);
            if (position == mImageIds.length - 1) {// ���һ��ҳ��
                btnStart.setVisibility(View.VISIBLE);// ��ʾ��ʼ����İ�ť
            } else {
                btnStart.setVisibility(View.INVISIBLE);
            }
        }

        // ����״̬�����仯
        @Override
        public void onPageScrollStateChanged(int state) {

        }

    }


    private void setupJazziness(JazzyViewPager.TransitionEffect effect) {
        vpGuide = (JazzyViewPager) findViewById(R.id.vp_guide);
        vpGuide.setTransitionEffect(effect);

        vpGuide.setPageMargin(30);
//		mJazzy.setOutlineEnabled(true);
    }
}
