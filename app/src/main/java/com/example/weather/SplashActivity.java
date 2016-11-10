package com.example.weather;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import yjj.weather.tool.NoStatus;
public class SplashActivity extends Activity {

    ImageView ivSplash;
    private RelativeLayout rl;
    private TextView tvSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        ivSplash = (ImageView) findViewById(R.id.iv_splash);
       View ll = findViewById(R.id.ll_splash);
        rl = (RelativeLayout) findViewById(R.id.rl_root_splash);
        tvSplash = (TextView) findViewById(R.id.tv_splash);
        startAnim();

        int[] ivs = new int[]{R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4,
                R.drawable.a5,
                R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9,
                R.drawable.a10, R.drawable.a11, R.drawable.a12,
                R.drawable.a13, R.drawable.a14, R.drawable.a15, R.drawable.a16,
                R.drawable.a17, R.drawable.a18, R.drawable.a19,
                R.drawable.a20, R.drawable.a21, R.drawable.a22,
                R.drawable.a23, R.drawable.a24, R.drawable.a25,
                R.drawable.a26, R.drawable.a27, R.drawable.a28,
                R.drawable.a29, R.drawable.a30, R.drawable.a31,
                R.drawable.a32, R.drawable.a33, R.drawable.a34,
                R.drawable.a35, R.drawable.a36, R.drawable.a37,
                R.drawable.a38, R.drawable.a39, R.drawable.a40,
                R.drawable.a41
        };
        int i = new Random().nextInt(41);
        ivSplash.setImageResource(ivs[i]);
        NoStatus.initAfterSetContentView(SplashActivity.this, ll);


        String[] strings = {
                "����֮���������� ����������������",
                "һ�𳤴��Լ�� ������ϧ �����Ĳ��������",
                "������·��������� ���ܵ����ﶼ������",
                "����Ʈ��ķ�Ҷ��˼�� �ҵ�ȼ�����ů��ĩ������",
                "���ǲ��ǲ����� �����",
                "��ʯ��� ����һЦ����Լ",
                "�ǽ������� ��������Ұ��",
                "Զ��������� ��ֻ�����������Ϣ",
                "���������ǣ������ֲ��ſ� ���ɲ����Լ�򵥵�û���˺�",
                "���������Ц����ô˯����",
                "Զ���˼䳾�� ����Ʈ ִ��֮�ֱ���ң",
                "�����������ǳ ����ڵȴ���Ԯ �������߸�ϰ���������",
                "�Ǳ�Ϊ���������� ����Ϊ�����Ż���",
                "��ɽ����Ʈ�ݵĺ��� ����������� ������ҡҷ����",
                "��Ц ��αװ���µ�����",
                "��������మ ��ʵֻ��һ������",
                "����һ�ξͳ��� ��ȴ���",
                "�粻ͣ�� �ο����� ҡ�εƻ�",
                "����Բ��� �����Ŵ�",
                "�������̫�� �Ҳ������",
                "�Ҹ���˼���С��",
                "�����Ӱ��ô������ȴ������",
                "����ʪ����� �ٵúܽ��� ",
                "û�����������ʹ",

                "�������� ��Ӱ���ǹ�ȥ ��΢Ц���� �ڰ׻���ļ���",
                "�ﵶ�����ζ è���㶼���˽�",
                "��������˭�ڴ�̨�ѽ�ִ�",
                "��İ��ɺ�Զ ����񼾽ڱ�Ǩ",
                "��ν�ľ��� �ǲ�֪���������� д�õ����� û�취Ͷ��",
                "�뿴�㿴������ �������εĻ���",
                "��ͯ���ϣ����һ̨ʱ��� �ҿ���һ·���ĵ��׶�������",
                "���е�Ǧ�� ��ֽ�������ػ� ���ü��������������ҵ�˭",
                "������ һ�����޴� ���ӵķ羰 ��ʼ������",
                "��˵Ե�� һ�������˵��",
                "�ҽ������ϲ�� һƿװȫ�ȵ�",
                "�ҽ���д ����Զ����д��ʫ�Ľ�β",
                "ԭ���������� �����Ժ�ϸ�� ԭ��ʫ����� ����û�н�β",
                "����ɫ������ �����ڵ���",
                "��������ǧ����ˮ ��ֻȡһư���˽�",
                "���� ��С�� ����è������ ����һ��ʫ ������",
                "�������˵�ǩ ֻ����������",
                "�Ǳ�������δ�� ������˭����",
                "���õ����˳������������´���Ѿ�ת����ഺ",
                "��ָ���������̼����� ��ʯ��ֻ���һЦ����Լ",
                "������һ����� �����ºȸ���",
                "�����Ĳ��������� ��������������",
                "�ѹ�Ӣ��Լ�� �������� �������������",
                "ȫ���� ����ֻ����ƣ��",
                "��һ� �᲻�� ������������˭",
                "��ʧ�������� �Һ�������һ�� ",
                "�ܲ��ܸ���һ�׸��ʱ�� �����İ���ӵ�������Զ",
                "������������Ǹ������Ķ�͸��������",
                "���ĵ������˳�ŵ ȴ��ʱ�����˿�",
                "�ǹ�����ö���ɫ ΢Цȴ������",
                "ͯ���ֽ�ɻ� �������ڷɻ�������",
                "�ò��÷������صĿ� Ѱ�ҵ�����������",
        };

        int k = new Random().nextInt(strings.length - 1);
        tvSplash.setText(strings[k]);
        int[] colors = {Color.rgb(78 ,238 ,148),Color.rgb(65 ,105 ,225),
                Color.rgb(0, 205, 0),
                Color.rgb(0 ,255 ,255 ),
                Color.rgb(139 ,117, 0),Color.rgb(139, 69, 19),
                Color.rgb(255, 106, 106),
                Color.rgb(255, 105 ,180   ),
                Color.rgb(255 ,0 ,255 ),
                Color.rgb(139 ,0 ,0),Color.rgb(85 ,26, 139),
        };
        int x = new Random().nextInt(colors.length - 1);
        tvSplash.setTextColor(colors[x]);
    }

    /**
     * ��������
     */
    private void startAnim() {

        // ��������
        AnimationSet set = new AnimationSet(false);

        // ��ת����
        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotate.setDuration(1000);// ����ʱ��
        rotate.setFillAfter(true);// ���ֶ���״̬

        // ���Ŷ���
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scale.setDuration(2000);// ����ʱ��
        scale.setFillAfter(true);// ���ֶ���״̬

        // ���䶯��
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(7000);// ����ʱ��
        alpha.setFillAfter(true);// ���ֶ���״̬

        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);

        // ���ö�������
        set.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            // ����ִ�н���
            @Override
            public void onAnimationEnd(Animation animation) {
                jumpNextPage();
            }
        });

        rl.startAnimation(set);
    }

    /**
     * ��ת��һ��ҳ��
     */
    private void jumpNextPage() {

        startActivity(new Intent(SplashActivity.this, GuideActivity.class));


        finish();
    }
}
