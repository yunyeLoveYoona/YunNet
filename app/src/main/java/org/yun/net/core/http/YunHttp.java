package org.yun.net.core.http;

import org.apache.http.NameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Administrator on 15-6-12.
 */
public interface YunHttp {
    public String get(String url) throws IOException;
    public String poet(String url, List<NameValuePair> nameValuePairList) throws IOException;
}
