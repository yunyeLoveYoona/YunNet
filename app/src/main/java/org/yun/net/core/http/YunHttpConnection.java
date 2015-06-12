package org.yun.net.core.http;

import org.apache.http.NameValuePair;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 15-6-12.
 */
public class YunHttpConnection implements YunHttp {
    private HashMap<String,String> httpHeadMap;
    public YunHttpConnection(HashMap<String,String> httpHeadMap){
        this.httpHeadMap = httpHeadMap;
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
