package org.yun.net.core.http;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 15-6-12.
 */
public class YunHttpClient implements YunHttp {
    private HttpClient httpClient;
    public YunHttpClient(HashMap<String,String> httpHeadMap){
        httpClient = new DefaultHttpClient();
        List<Header> headers = new ArrayList<Header>();
        Iterator iterator = httpHeadMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = (Map.Entry<String, String>) iterator.next();
            headers.add(new BasicHeader(entry.getKey(),entry.getValue()));
        }
        httpClient.getParams().setParameter("http.default-headers", headers);

    }

    @Override
    public String get(String url) {
        return null;
    }

    @Override
    public String poet(String url, List<NameValuePair> nameValuePairList) {
        return null;
    }
}
