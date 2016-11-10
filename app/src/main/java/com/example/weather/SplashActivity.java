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
                "有生之年能遇见你 竟花光我所有运气",
                "一起长大的约定 那样珍惜 与你聊不完的曾经",
                "载着你仿佛载着阳光 不管到哪里都是晴天",
                "缓缓飘落的枫叶像思念 我点燃烛火温暖岁末的秋天",
                "爱是不是不开口 才珍贵",
                "青石板街 回眸一笑你婉约",
                "城郊牧笛声 落在那座野村",
                "远方传来风笛 我只在意有你的消息",
                "我想就这样牵着你的手不放开 爱可不可以简简单单没有伤害",
                "看不见你的笑我怎么睡得着",
                "远离人间尘嚣 柳絮飘 执子之手便逍遥",
                "风筝在阴天搁浅 想念还在等待救援 我拉着线复习你给的温柔",
                "城堡为爱守着秘密 而我为你守着回忆",
                "在山腰间飘逸的红雨 随著北风凋零 我轻轻摇曳风铃",
                "想笑 来伪装掉下的眼泪",
                "海鸟和鱼相爱 其实只是一场意外",
                "花开一次就成熟 我却错过",
                "风不停留 何苦绕来 摇晃灯火",
                "天空仍灿烂 它爱着大海",
                "你的心事太多 我不会戳破",
                "我给的思念很小心",
                "你的身影那么近可我却抱不到",
                "雨淋湿了天空 毁得很讲究 ",
                "没有做完的梦最痛",

                "窗外的雨滴 倒影出那过去 我微笑想起 黑白画面的记忆",
                "秋刀鱼的滋味 猫跟你都想了解",
                "梦醒来是谁在窗台把结局打开",
                "你的爱飞很远 像候鸟季节变迁",
                "所谓的距离 是不知道你在哪里 写好的想你 没办法投递",
                "想看你看的世界 想在你梦的画面",
                "那童年的希望是一台时光机 我可以一路开心到底都不换气",
                "手中的铅笔 在纸上来来回回 我用几行字形容你是我的谁",
                "回忆是 一行行无从 剪接的风景 爱始终年轻",
                "我说缘分 一如参禅不说话",
                "我将对你的喜好 一瓶装全喝掉",
                "我接着写 把永远爱你写进诗的结尾",
                "原来爱跟心碎 都可以很细节 原来诗跟离别 可以没有结尾",
                "天青色等烟雨 而我在等你",
                "繁华如三千东流水 我只取一瓢爱了解",
                "很轻 很小心 就像猫跟风铃 念了一首诗 给你听",
                "或许命运的签 只让我们遇见",
                "那薄如蝉翼的未来 经不起谁来猜",
                "我用第三人称描述来不及温存就已经转身的青春",
                "弹指岁月倾城顷刻间烟灭 青石板街回眸一笑你婉约",
                "我温了一壶乡愁 将往事喝个够",
                "最美的不是下雨天 是曾你躲雨的屋檐",
                "蒲公英的约定 那样清晰 打过勾的我相信",
                "全世界 好像只有我疲惫",
                "天灰灰 会不会 让你忘了我是谁",
                "消失的下雨天 我好想再淋一遍 ",
                "能不能给我一首歌的时间 紧紧的把那拥抱变成永远",
                "美人鱼的眼泪是个连伤心都透明的世界",
                "信誓旦旦给了承诺 却被时间扑了空",
                "糖果罐里好多颜色 微笑却不甜了",
                "童年的纸飞机 现在终于飞回我手里",
                "该不该放下重重的壳 寻找到哪里有蓝天",
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
     * 开启动画
     */
    private void startAnim() {

        // 动画集合
        AnimationSet set = new AnimationSet(false);

        // 旋转动画
        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotate.setDuration(1000);// 动画时间
        rotate.setFillAfter(true);// 保持动画状态

        // 缩放动画
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scale.setDuration(2000);// 动画时间
        scale.setFillAfter(true);// 保持动画状态

        // 渐变动画
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(7000);// 动画时间
        alpha.setFillAfter(true);// 保持动画状态

        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);

        // 设置动画监听
        set.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            // 动画执行结束
            @Override
            public void onAnimationEnd(Animation animation) {
                jumpNextPage();
            }
        });

        rl.startAnimation(set);
    }

    /**
     * 跳转下一个页面
     */
    private void jumpNextPage() {

        startActivity(new Intent(SplashActivity.this, GuideActivity.class));


        finish();
    }
}
