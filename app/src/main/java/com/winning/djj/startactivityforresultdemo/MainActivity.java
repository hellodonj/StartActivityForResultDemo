package com.winning.djj.startactivityforresultdemo;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:第一个activity
 * 作者|时间: djj on 2018/10/30 15:38
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnOpen;
    private TextView mTvShow;
    private Vibrator mVibrator;
    private Ringtone mRingtone;

    // 界面上的控件
    private ListView mListView;

    //列标题
    private TextView mTitle;

    //数据
    private List<List<String>> contentList = new ArrayList<>();

    //行数
    private int pageNum = 20;

    //列数
    private int columnNum = 7;

    //适配器
    private CommonAdapter<List<String>> mContentAdapter;

    /**
     * 为了得到传回的数据，必须在前面的Activity中（指MainActivity类）重写onActivityResult方法
     *
     * @param requestCode 请求码，即调用startActivityForResult()传递过去的值
     * @param resultCode  返回码，结果码用于标识返回数据来自哪个新Activity
     * @param data        更新后的数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //得到新activity关闭后的数据
        mTvShow.setText(data.getExtras().getString("result"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnOpen = findViewById(R.id.btn_open);
        mTvShow = findViewById(R.id.tv_show);

//        mListView = findViewById(R.id.list_view);
//       mTitle = findViewById(R.id.tv_title);

        mBtnOpen.setOnClickListener(this);
        // 初始化震动通知
        if (isInitVibratorNotify()) {
            mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        }

        if (isInitRingNotify()) {
            Uri notifyUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            mRingtone = RingtoneManager.getRingtone(MainActivity.this, notifyUri);
        }

//        for (int i = 0; i < columnNum; i++) {
//            View view = View.inflate(this, R.layout.head_item, null);
//            TextView mTvData = view.findViewById(R.id.tv_data);
////            if (i == 0) {
////                mTvData.setText("星期一");
////            } else if (i == 1) {
////                mTvData.setText("星期二");
////            } else if (i == 2) {
////                mTvData.setText("星期三");
////            } else if (i == 3) {
////                mTvData.setText("星期四");
////            } else if (i == 4) {
////                mTvData.setText("星期五");
////            } else if (i == 5) {
////                mTvData.setText("星期六");
////            } else if (i == 6) {
////                mTvData.setText("星期日");
////            }
//            mTvData.setText("星期" + i);
//            mTitleLayout.addView(view);
//        }
//
//        initData();
//
//        mContentAdapter = new CommonAdapter<List<String>>(contentList, this, R.layout.data_item) {
//            @Override
//            public void fillData(int position, CommonViewHolder holder) {
//                List<String> strings = contentList.get(position);
//                LinearLayout linContent = holder.getView(R.id.lin_content);
//                for (int i = 0; i < linContent.getChildCount(); i++) {
//                    if (i % 2 == 0) {
//                        TextView tv = (TextView) linContent.getChildAt(i);
//                        tv.setText(strings.get(i / 2));
//                    }
//                }
//            }
//        };
//        mListView.setAdapter(mContentAdapter);
    }

    private void initData() {
        for (int i = 0; i < pageNum; i++) {
            List<String> titles = new ArrayList<String>();
            for (int j = 0; j < columnNum; j++) {
                titles.add(i + "患者" + j);
            }
            contentList.add(titles);
        }
    }

    @Override
    public void onClick(View v) {
        //得到新打开Activity关闭后返回的数据
        //第二个参数为请求码，可以根据业务需求自己编号
        Intent intent = new Intent(MainActivity.this, NewActivity.class);
        startActivityForResult(intent, 1);
        notifyRing();
        notifyVibrator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVibrator != null) {
            mVibrator.cancel();
        }

        if (mRingtone != null && mRingtone.isPlaying()) {
            mRingtone.stop();
        }
    }

    /**
     * 震动通知
     */
    protected void notifyVibrator() {
        if (mVibrator != null) {
            // 震动 1s
            mVibrator.vibrate(1000);
        }
    }

    /**
     * 声音通知
     */
    protected void notifyRing() {
        if (mRingtone != null) {
            mRingtone.play();
        }
    }

    /**
     * 是否打开震动
     *
     * @return 震动
     */
    protected boolean isInitVibratorNotify() {
        return true;
    }

    /**
     * 是否打开声音提醒
     *
     * @return 声音
     */
    protected boolean isInitRingNotify() {
        return true;
    }
}
