package org.yun.net.core.requests;


import org.apache.http.NameValuePair;
import org.yun.net.core.Request;
import org.yun.net.core.http.YunHttp;

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
        if(httpHeadMap==null){
            setDefaultHttpHead();
        }
        YunHttp yunHttp = getYunHttp();
        try {
            String resultstr;
            if(nameValuePairList != null&&nameValuePairList.size()>0){
                resultstr = yunHttp.poet(url,nameValuePairList);
            }else{
                resultstr = yunHttp.get(url);
            }
            forResult(resultstr);
        } catch (final Exception e) {
            if(requestListenter != null){
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        requestListenter.onError(e, tag);
                    }
                });
            }
        }
    }
    @Override
    protected void forResult(final String resultStr) throws Exception {
        if(requestListenter != null){
            mainLooperHandler.post(new Runnable() {
                @Override
                public void run() {
                    requestListenter.onComlete(resultStr, tag);
                }
            });
        }
    }


}
