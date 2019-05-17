package com.winning.djj.startactivityforresultdemo.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.winning.djj.startactivityforresultdemo.R;

/**
 * 描述: 第二个activity
 * 作者|时间: djj on 2019/1/14 10:16
 * 博客地址: http://www.jianshu.com/u/dfbde65a03fc
 */
public class SecondActivity extends AppCompatActivity {

    private Button mBtnColse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mBtnColse = findViewById(R.id.btn_close);

        mBtnColse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //把需要返回的数据存放在intent
                intent.putExtra("second", "我是第二页的信息！");
                //设置返回数据
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
