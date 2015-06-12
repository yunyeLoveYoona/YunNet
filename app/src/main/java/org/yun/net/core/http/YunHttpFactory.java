package org.yun.net.core.http;

import android.os.Build;
import java.util.HashMap;

/**
 *
 * Created by yunye on 15-6-12.
 */
public class YunHttpFactory {
    public static YunHttp createYunHttp(HashMap<String,String> httpHeadMap){
        YunHttp yunHttp;
        if(Build.VERSION.SDK_INT<9){
            yunHttp = new YunHttpClient(httpHeadMap);
        }else{
            yunHttp = new YunHttpConnection(httpHeadMap);
        }
        return yunHttp;
    }

}
