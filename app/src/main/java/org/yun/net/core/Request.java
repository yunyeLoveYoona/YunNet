package org.yun.net.core;

import android.os.Handler;
import android.os.Looper;

import org.apache.http.NameValuePair;
import org.yun.net.core.http.YunHttp;
import org.yun.net.core.http.YunHttpFactory;

import java.util.HashMap;
import java.util.List;

/**
 *用来执行网络请求，并传递请求结果
 * 可以设置请求执行的优先级,并且可以给请求设置tag
 * 当你在一个activity或者其他情况下，需要一次发出多个请求并对所有的请求进行监听的话
 * 有了tag就可以在一个监听回调中完成对所有请求的监听，提供代码的简洁度
 * Created by yunye on 15-6-12.
 */
public abstract class Request implements Runnable,Comparable<Request>{
    private final int priority;
    protected String url;
    protected List<NameValuePair> nameValuePairList;
    protected int tag;
    protected Handler mainLooperHandler;
    protected HashMap<String,String> httpHeadMap;
    protected RequestListenter requestListenter;

    /**
     * 无参数时默认为Get方式请求
     * @param priority
     * @param url
     * @param tag
     * @param requestListenter
     */
    public Request(int priority,String url,int tag,RequestListenter requestListenter) {
        this.priority = priority;
        this.url = url;
        this.tag = tag;
        this.requestListenter =requestListenter;
    }

    /**
     * 有参数时默认为Post方式请求
     * @param priority
     * @param url
     * @param nameValuePairList
     * @param tag
     * @param requestListenter
     */
    public Request(int priority,String url,List<NameValuePair> nameValuePairList
            ,int tag,RequestListenter requestListenter){
        this.priority = priority;
        this.url = url;
        this.nameValuePairList = nameValuePairList;
        this.tag = tag;
        this.requestListenter =requestListenter;
    }

    @Override
    public void run() {
        startRequest();
    }
    protected abstract void startRequest();
    protected abstract void forResult(String resultStr) throws Exception;

    public void setHttpHead(HashMap<String,String> httpHeadMap){
        this.httpHeadMap = httpHeadMap;
    }

    protected YunHttp getYunHttp(){
       YunHttp yunHttp = YunHttpFactory.createYunHttp(httpHeadMap);
        return  yunHttp;
    }

    /**
     * 设置默认的http头信息
     */
    protected void setDefaultHttpHead(){
        httpHeadMap = new HashMap<String,String>();
        httpHeadMap.put("Accept", "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpHeadMap.put("Accept-Charset", "uft-8");
        httpHeadMap.put("Accept-Language", "zh-cn,zh;q=0.5");
        httpHeadMap.put("Connection", "keep-alive");
        httpHeadMap.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
    }


    @Override
    public int compareTo(Request request) {
        if(priority > request.priority){
            return 1;
        }else if(priority < request.priority){
            return -1;
        }
        return 0;
    }
    public interface RequestListenter<T>{
        public void onComlete(T response,int tag);
        public void onError(Exception error,int tag);
    }
    protected void setMainLooper(Looper mainLooper){
        mainLooperHandler = new Handler(mainLooper);
    }
}
