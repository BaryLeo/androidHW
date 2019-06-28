package com.wyu.takeleave.util;

import android.util.Log;

import com.google.gson.Gson;
import com.wyu.takeleave.login.LoginModel;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MyOkHttpUtils {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private static String output;


    /**
     * POST请求，发送JSON数据
     * @param url
     * @param inJson
     * @return
     */
    public static String postRequestWithJSON(final String url, final String inJson){
        new Thread(){
            @Override
            public void run() {
                try{
                    RequestBody body = RequestBody.create(JSON, inJson);
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .post(body)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
        return "";
    }

    /**
     * POST请求，发送Params数据
     * @param url
     * @param body
     * @return
     */
    public static void postRequestWithParams(final String url, final RequestBody body){
        new Thread(){
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .post(body)
                            .build();
                    Response response = client.newCall(request).execute();
                    output = response.body().string();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static Object parseJSONWithGSON(String jsonData, Class inClass) {
        Gson gson = new Gson();
        Object obj = gson.fromJson(jsonData, inClass);
        return obj;
    }


}
