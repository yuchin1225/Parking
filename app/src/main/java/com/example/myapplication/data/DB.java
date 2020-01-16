package com.example.myapplication.data;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;


public class DB {

    public String getJson(String url) {
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

    public String getJsonArray(String url){
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
}