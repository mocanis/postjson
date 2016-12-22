package com.chris.app1222;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    //    String url = "http://172.16.11.183:8080/alarm/saveAlarm.json";
    String url = "http://121.199.5.95:10010/alarm/saveAlarm.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                requestByPostMethod();
                getByVolley();
            }
        }).start();
//        requestByPostMethod();
    }

    public void requestByPostMethod() {
        HttpClient httpClient = new DefaultHttpClient();
//        String url = "http://172.16.11.183:8080/alarm/saveAlarm.json";
        HttpPost httpPost = new HttpPost(url);

        //添加http头信息
//        httpPost.addHeader("Authorization", "your token"); //认证token
        httpPost.addHeader("Content-Type", "application/json");
//        httpPost.addHeader("User-Agent", "imgfornote");
/**
 * {
 "sostype": "SOS_TYPE_SOS",
 "x": "120.0816748183",
 "y": "30.2733292576",
 "scene": "",
 "telphone": "",
 "name": "[Terminal2]消防报警",
 "triggerdevice": "2",
 "alarmtime": "2016-12-01 16:32:04"
 }
 */


        //http post的json数据格式：  {"name": "your name","parentId": "id_of_parent"}
        JSONObject obj = new JSONObject();
        try {
            obj.put("sostype", "SOS_TYPE_SOS");
            obj.put("x", "120.0816748183");
            obj.put("y", "30.2733292576");
            obj.put("scene", "");
            obj.put("telphone", "123");
            obj.put("name", "[Terminal2]消防报警");
            obj.put("triggerdevice", "2");
            obj.put("alarmtime", "2016-12-01 16:32:04");

            httpPost.setEntity(new StringEntity(obj.toString()));
            HttpResponse response;
            response = httpClient.execute(httpPost);
            //检验状态码，如果成功接收数据
            int code = response.getStatusLine().getStatusCode();
            System.out.println(code + "qqqqqqqq");
            if (code == 200) {
                String rev = EntityUtils.toString(response.getEntity());//返回json格式： {"id": "27JpL~j4vsL0LX00E00005","version": "abc"}
                System.out.println(rev + "11111111");
//              obj = new JSONObject(rev);
//
//              String id = obj.getString("id");
//              System.out.println(rev+"222222222");
//              String version = obj.getString("version");
//              System.out.println(rev+"333333333");
                System.out.println("wawawawa");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getByVolley() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        Map<String, String> map = new HashMap<String, String>();
        map.put("sostype", "SOS_TYPE_SOS");
        map.put("x", "120.0816748183");
        map.put("y", "30.2733292576");
        map.put("scene", "");
        map.put("telphone", "123");
        map.put("name", "[Terminal2]消防报警");
        map.put("triggerdevice", "2");
        map.put("alarmtime", "2016-12-01 16:32:04");
        JSONObject jsonObject = new JSONObject(map);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };
        requestQueue.add(jsonRequest);
    }
}
