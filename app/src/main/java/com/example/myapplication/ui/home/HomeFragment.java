package com.example.myapplication.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;


public class HomeFragment extends Fragment {
    String url = "https://api.kcg.gov.tw/api/" +
            "service/Get/b45264fc-c635-4398-8a8d-76f3245739b6";
    TextView textView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container,false);
        textView = root.findViewById(R.id.text_home);
        new jsondata().execute(url);
        return root;
    }

    class jsondata extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... strings) {
            String stringurl = strings[0];
            String result = "";
            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(stringurl);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                if (httpResponse.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
                    //API的回傳文字，格式為json格式
                    String getJsontext = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
                    String jsondata = new JSONObject(getJsontext).getString("data");
                    JSONArray jsonArray = new JSONArray(new JSONObject(getJsontext).getString("data"));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String seq = jsonObject.getString("seq");
                        String name = jsonObject.getString("機關名稱");
                        String name2 = jsonObject.getString("要求提供網路個人資料的次數");
                        result = seq+"\n"+name+"\n"+name2;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return  result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText("Json:"+s);
        }
    }

}