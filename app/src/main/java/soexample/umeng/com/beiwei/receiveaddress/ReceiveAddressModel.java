package soexample.umeng.com.beiwei.receiveaddress;

import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.utils.OkHttpUtils;
import soexample.umeng.com.beiwei.utils.UtilsUrl;

/**
 * Created by Shinelon on 2018/12/12.
 */

public class ReceiveAddressModel {
    Handler handler=new Handler();
    public void getData(int id, String realName, String phone, String address, String code, final ICallBack callBack, final Type type){
        Map<String,String> map=new HashMap<>();
        String s = String.valueOf(id);
        map.put("id",s);
        map.put("realName",realName);
        map.put("phone",phone);
        map.put("address",address);
        map.put("zipCode",code);
        //String url= UtilsUrl.receiveAddressUrl+"?id="+id+"&realName="+realName+"&phone="+phone+"&address="+address+"&code="+code;
        OkHttpUtils.enqueuPut(UtilsUrl.receiveAddressUrl,map,new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                      callBack.failed(e);
                    }
                });
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
