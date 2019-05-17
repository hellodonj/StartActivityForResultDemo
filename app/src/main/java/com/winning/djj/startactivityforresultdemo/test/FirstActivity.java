package com.winning.djj.startactivityforresultdemo.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.winning.djj.startactivityforresultdemo.R;

/**
 * 描述: 第一个activity
 * 作者|时间: djj on 2019/1/14 10:11
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */
public class FirstActivity extends AppCompatActivity {

    private Button mBtnStart;
    private TextView mTvShow;
    private final static int REQUEST_CODE = 1; // 返回的结果码

    /**
     * 为了得到传回的数据，必须在前面的Activity中（指FirstActivity类）重写onActivityResult方法
     *
     * @param requestCode 请求码，即调用startActivityForResult()传递过去的值
     * @param resultCode  返回码，结果码用于标识返回数据来自哪个新Activity
     * @param data        更新后的数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                mTvShow.setText(data.getExtras().getString("second") + "requestCode:" + requestCode + "resultCode:" + resultCode);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mBtnStart = findViewById(R.id.btn_start);
        mTvShow = findViewById(R.id.tv_shows);

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到新打开Activity关闭后返回的数据
                //第二个参数为请求码，可以根据业务需求自己编号
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
}
