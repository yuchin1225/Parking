package com.example.myapplication.data;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.ArrayMap;
import android.util.JsonWriter;
import android.util.Log;

import androidx.annotation.Nullable;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class API_DB extends Service {

    String postdata;


    public static String getJson(String url) {
        String result = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
                //API的回傳文字，格式為json格式
                String getJsontext = EntityUtils.toString(httpResponse.getEntity(),HTTP.UTF_8);
                result = new JSONObject(getJsontext).getString("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  result;
    }

    public static String getJsonArray(String url){
        String result= "";
        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
                //API的回傳文字，格式為json格式
                String getJsontext = EntityUtils.toString(httpResponse.getEntity(),HTTP.UTF_8);
                //建立一個JSONArray並帶入JSON格式文字，getString(String key)取出欄位的數值
                JSONArray array = new JSONArray(new JSONObject(getJsontext).getString("data"));
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    result = jsonObject.getString("機關名稱");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public String postData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    HttpClient posthc = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("https://api.kcg.gov.tw/api/" +
                            "service/Get/b45264fc-c635-4398-8a8d-76f3245739b6");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    httpPost.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
                    HttpResponse postresponse = posthc.execute(httpPost);
                    if(postresponse.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK )
                    {
                        //API的回傳文字，格式為json格式
                        String postJson = EntityUtils.toString(postresponse.getEntity());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        return postdata;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}