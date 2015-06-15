package com.example.administrator.yunnet;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.yun.net.core.Request;
import org.yun.net.core.RequestQueue;
import org.yun.net.core.YunNet;
import org.yun.net.core.requests.StringRequest;


public class MainActivity extends ActionBarActivity {
    int num =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RequestQueue requestQueue = YunNet.createRequestQueue(this);
        Request.RequestListenter<String> requestListenter = new Request.RequestListenter<String>() {
            @Override
            public void onComlete(String response, int tag) {
                ( (TextView) findViewById(R.id.text)).setText(num+ "-----"+response);
                num = num+1;
                if(num==3){
                    requestQueue.stop();
                }
            }
            @Override
            public void onError(Exception error, int tag) {
                Log.v("yunye",error.toString());
            }
        };
        StringRequest request = new StringRequest(1,"http://blog.csdn.net/blue_jjw/article/details/8768624"
        ,1000,requestListenter);
        requestQueue.addRequest(request);
        StringRequest request1 = new StringRequest(2,"http://www.ifeng.com/?tongji=baiduxinshouye"
                ,1002,requestListenter);
        requestQueue.addRequest(request1);
        StringRequest request2 = new StringRequest(1,"http://www.csdn.net/?ref=toolbar"
                ,1005,requestListenter);
        requestQueue.addRequest(request2);
        StringRequest request3 = new StringRequest(3,"http://geek.csdn.net/news/detail/34298"
                ,1002,requestListenter);
        requestQueue.addRequest(request3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
