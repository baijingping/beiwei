package soexample.umeng.com.beiwei.commodity.model;

import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.utils.OkHttpUtils;
import soexample.umeng.com.beiwei.utils.UtilsUrl;

/**
 * Created by Shinelon on 2018/12/7.
 */

public class RxxpModel {
    private Handler handler=new Handler();
    public void getData(String labelId, int page, int count, final ICallBack callBack, final Type type){
        String url= UtilsUrl.ByLabelUrl+"?labelId="+labelId+"&page="+page+"&count="+count;
        OkHttpUtils.enqueuGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.failed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Gson gson=new Gson();
                final Object o = gson.fromJson(result, type);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                      callBack.successful(o);
                    }
                });
            }
        },type);
    }
}
