package com.winning.djj.startactivityforresultdemo;

import android.os.Handler;
import android.os.Message;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * desc: 访问WebService的工具类,
 * author: dj
 * date: 2017/3/9 10:00
 * 参考：http://blog.csdn.net/zl570932980/article/details/51842574
 */
public class WebServiceUtils {

    // 含有3个线程的线程池
    private static final ExecutorService executorService = Executors.newFixedThreadPool(3);
    private static SoapObject soapObject;
    public static String NAME_SPACE = "http://www.winning.com.cn/";


    /**
     * 单例模式获取到Object的实例
     */
    public static SoapObject getObjectInstance(String Space, String method) {
        if (soapObject == null) {
            synchronized (WebServiceUtils.class) {
                if (soapObject == null) {
                    soapObject = new SoapObject(Space, method);
                }
            }
        }
        return soapObject;
    }

    public static void close() {
        if (soapObject != null) {
            soapObject = null;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param url                WebService服务器地址
     * @param methodName         WebService的调用方法名
     * @param values             WebService的参数
     * @param webServiceCallBack 回调接口
     */
    public static void callWebService(String url, final String methodName,String interfaceCode,
                                      String values,
                                      final WebServiceCallBack webServiceCallBack) {//HashMap<String, String> properties

        soapObject = getObjectInstance(NAME_SPACE, methodName);
        soapObject.addProperty("userId", "");
        soapObject.addProperty("userPassword", "");
        soapObject.addProperty("businessCode", interfaceCode);
        soapObject.addProperty("businessInfo", values);
        // 实例化SoapSerializationEnvelope，传入WebService的SOAP协议的版本号
        final SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        // 设置是否调用的是.Net开发的WebService
        soapEnvelope.setOutputSoapObject(soapObject);
        soapEnvelope.dotNet = true;
        // 创建HttpTransportSE对象，传递WebService服务器地址
        final HttpTransportSE httpTransportSE = new HttpTransportSE(url);
        httpTransportSE.debug = true;
        // 用于子线程与主线程通信的Handler
        final Handler mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // 将返回值回调到callBack的参数中
                webServiceCallBack.callBack((SoapPrimitive) msg.obj);
            }

        };

        // 开启线程去访问WebService
        executorService.submit(new Runnable() {

            @Override
            public void run() {
                SoapPrimitive result = null;
                try {
                    httpTransportSE.call(NAME_SPACE + methodName, soapEnvelope);
                    if (soapEnvelope.getResponse() != null) {
                        // 获取服务器响应返回的SoapObject
                        result = (SoapPrimitive) soapEnvelope.getResponse();
                    }
                } catch (HttpResponseException e) {
                    e.printStackTrace();
                } catch (AssertionError e) {  //防止SocketTimeoutException
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } finally {
                    // 将获取的消息利用Handler发送到主线程
                    mHandler.sendMessage(mHandler.obtainMessage(0, result));
                }
            }
        });
    }

    public interface WebServiceCallBack {
        void callBack(SoapPrimitive result);

    }

}
