package org.yun.net.core;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 请求队列
 * Created by yunye on 15-6-12.
 */
public class RequestQueue implements Runnable{
    private Looper mainLooper;
    private static RequestQueue instance;
    private PriorityBlockingQueue<Runnable> queue;
    public static RequestQueue getInstance(Context context){
        instance = new RequestQueue(context);
        return instance;
    }
    private RequestQueue(){

    }
    private RequestQueue(Context context){
        mainLooper = context.getMainLooper();
        queue = new PriorityBlockingQueue<Runnable>();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(this);
    }
    public void addRequest(Request request){
        request.setMainLooper(mainLooper);
        queue.add(request);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            try {
                queue.take().run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
