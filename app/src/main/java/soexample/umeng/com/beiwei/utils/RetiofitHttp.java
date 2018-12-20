package soexample.umeng.com.beiwei.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Shinelon on 2018/12/16.
 */

public class RetiofitHttp {

    private final BaseUtils baseService;
    private Observable<ResponseBody> observable;
    private android.database.Observable<ResponseBody> observable1;

    public RetiofitHttp(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://172.17.8.100/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        baseService = retrofit.create(BaseUtils.class);
    }
    public RetiofitHttp part(String url, Map<String,String> map, MultipartBody.Part part){
        if(map==null){
            map = new HashMap<>();
        }
        observable = baseService.part(url, map, part);

        setObserver();
        return this;
    }
    private void setObserver() {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    private Observer observer = new Observer<ResponseBody>(){

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(ResponseBody responseBody) {
            try {
                String string = responseBody.string();
                string = string.replace("https","http");
                listener.success(string);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable e) {
            String message = e.getMessage();
            listener.fail(message);
        }

        @Override
        public void onComplete() {

        }
    };
    private HttpRxListener listener;
    public void result(HttpRxListener listener){
        this.listener=listener;
    }

    public interface HttpRxListener{
        void success(String data);
        void fail(String error);
    }
}
