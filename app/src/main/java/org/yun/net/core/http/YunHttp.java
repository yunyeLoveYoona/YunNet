package org.yun.net.core.http;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Created by Administrator on 15-6-12.
 */
public interface YunHttp {
    public String get(String url);
    public String poet(String url, List<NameValuePair> nameValuePairList);
}
