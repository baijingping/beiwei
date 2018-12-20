package soexample.umeng.com.beiwei.utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Shinelon on 2018/12/5.
 */

public class OkLogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        long l = System.nanoTime();
        LogUtil.d(" request  = " + String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));
        //拿到Response对象
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        //得出请求网络,到得到结果,中间消耗了多长时间
        LogUtil.d("response  " + String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - l) / 1e6d, response.headers()));
        String method=request.method();
        HttpUrl url=request.url();
        LogUtil.d("本次请求","ulr:"+url.toString()+"method:"+method);
        LogUtil.d("方法体:","msg"+response.toString());
        Headers headers=request.headers();
        Set<String> names=headers.names();
        Iterator<String> iterator=names.iterator();
        while (iterator.hasNext()){
            String next=iterator.next();
            String value=headers.get(next);
            LogUtil.d(next+":"+value);
        }
        return chain.proceed(request);
    }
}
