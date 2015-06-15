package org.yun.net.core.http;

import org.apache.http.NameValuePair;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 15-6-12.
 */
public class YunHttpUrlConnection implements YunHttp {
    private HashMap<String,String> httpHeadMap;
    public YunHttpUrlConnection(HashMap<String, String> httpHeadMap){
        this.httpHeadMap = httpHeadMap;
    }
    @Override
    public String get(String url) throws IOException {
        HttpURLConnection httpURLConnection =getHttpUrlConnection(url);
        return YunHttpUtil.encodedString(httpURLConnection.getInputStream());
    }

    @Override
    public String poet(String url, List<NameValuePair> nameValuePairList) throws IOException {
        HttpURLConnection httpURLConnection =getHttpUrlConnection(url);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.connect();
        DataOutputStream outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        String content ="";
        for(NameValuePair nameValuePair : nameValuePairList){
            if(content.length()<1){
                content = content + nameValuePair.getName()+"="+nameValuePair.getValue();
            }else{
                content = content +"&" + nameValuePair.getName()+"="+nameValuePair.getValue();
            }
        }
        outputStream.writeBytes(content);
        outputStream.flush();
        outputStream.close();
        return YunHttpUtil.encodedString(httpURLConnection.getInputStream());
    }

    private HttpURLConnection getHttpUrlConnection(String url) throws IOException {
        URL httpUrl = new URL(url);
        HttpURLConnection httpConnection = (HttpURLConnection) httpUrl.openConnection();
        Iterator iterator = httpHeadMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = (Map.Entry<String, String>) iterator.next();
            httpConnection.addRequestProperty(entry.getKey(),entry.getValue());
        }
        return httpConnection;
    }
}
