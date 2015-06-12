package org.yun.net.core.requests;


import org.apache.http.NameValuePair;
import org.yun.net.core.Request;

import java.util.List;

/**
 * Created by Administrator on 15-6-12.
 */
public class StringRequest extends Request{

    public StringRequest(int priority, String url, int tag, RequestListenter<String> requestListenter) {
        super(priority, url, tag, requestListenter);
    }
    public StringRequest(int priority, String url, List<NameValuePair> nameValuePairList,
                         int tag, RequestListenter<String> requestListenter) {
        super(priority, url, nameValuePairList, tag, requestListenter);
    }

    @Override
    protected void startRequest() {

    }

    @Override
    protected void setHttpHead() {

    }
}
