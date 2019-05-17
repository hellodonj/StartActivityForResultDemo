package com.winning.djj.startactivityforresultdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 描述: 新的activity
 * 作者|时间: djj on 2018/10/30 15:58
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */
public class NewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        mBtnClose = findViewById(R.id.btn_close);
        mBtnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //数据使用Intent返回
        Intent intent = new Intent();
        //把数据存放在intent
        intent.putExtra("result", "I am a new message!");
        //设置返回数据
        this.setResult(RESULT_OK, intent);
        //关闭activity
        this.finish();
    }
}
