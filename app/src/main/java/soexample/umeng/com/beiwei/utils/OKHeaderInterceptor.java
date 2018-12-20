package soexample.umeng.com.beiwei.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import soexample.umeng.com.beiwei.base.BaseApplication;

/**
 * Created by Shinelon on 2018/12/5.
 */

public class OKHeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        SharedPreferences sp = BaseApplication.sContext.getSharedPreferences("user", Context.MODE_PRIVATE);
        String sid = sp.getString("sid", "");
        int uid = sp.getInt("uid", 0);
        Request request=chain.request();
        Request.Builder builder=request.newBuilder();
        builder.addHeader("userId",uid+"");
        builder.addHeader("sessionId",sid);
        request=builder.build();
        return chain.proceed(request);
    }
}
