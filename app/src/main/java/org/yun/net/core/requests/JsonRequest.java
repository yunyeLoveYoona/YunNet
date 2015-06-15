package org.yun.net.core.requests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.yun.net.core.Request;
import org.yun.net.core.http.YunHttp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yune on 15-6-12.
 */
public class JsonRequest<T> extends Request{
    public JsonRequest(int priority, String url, int tag, RequestListenter<T> requestListenter) {
        super(priority, url, tag, requestListenter);
    }

    public JsonRequest(int priority, String url, List<NameValuePair> nameValuePairList,
                       int tag, RequestListenter<T> requestListenter) {
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
        } catch (Exception e) {
            if(requestListenter != null){
                requestListenter.onError(e,tag);
            }
        }
    }

    @Override
    protected void forResult(String resultStr) throws Exception{
            Gson gson = new Gson();
            final T result = gson.fromJson(resultStr,new TypeToken<T>(){}.getType());
            if(requestListenter != null){
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        requestListenter.onComlete(result, tag);
                    }
                });
            }
    }
}
