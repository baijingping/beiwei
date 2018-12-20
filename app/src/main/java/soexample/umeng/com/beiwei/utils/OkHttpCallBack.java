package soexample.umeng.com.beiwei.utils;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import soexample.umeng.com.beiwei.bean.DataLogin;

/**
 * Created by Shinelon on 2018/12/5.
 */

public class OkHttpCallBack implements Callback {

    private OkHttpDataListener okHttpDataListener;

    public OkHttpCallBack(OkHttpDataListener okHttpDataListener) {
        this.okHttpDataListener = okHttpDataListener;
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String string = response.body().string();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Gson gson =new Gson();
                DataLogin dataLogin = gson.fromJson(string, DataLogin.class);
                okHttpDataListener.success(dataLogin.getOb());
            }
        });

    }
    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

}
