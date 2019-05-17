package com.winning.djj.startactivityforresultdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.ksoap2.serialization.SoapPrimitive;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        button = findViewById(R.id.btn_request);
        textView = findViewById(R.id.tv_request);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://172.17.17.81:8084/WebInterface.asmx?wsdl";
                final LoginRequest request = new LoginRequest();
                request.setId("李林森");
                request.setPassword("123456");
                String parms = JsonUtils.toJson(request);

                WebServiceUtils.callWebService(url, "RIMSInterface","RIMS01", parms, new WebServiceUtils.WebServiceCallBack() {
                    @Override
                    public void callBack(SoapPrimitive result) {
                        if (result==null){

                        }
//                        LoginRequest request1 = JsonUtils.fromJson(result.toString(), new TypeToken<List<LoginRequest>>() {
//                        }.getType());
                        Log.i("--------Test-------",result.toString());
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_request:

                break;
        }
    }
}
