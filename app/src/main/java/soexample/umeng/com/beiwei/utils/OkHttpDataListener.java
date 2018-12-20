package soexample.umeng.com.beiwei.utils;

/**
 * Created by Shinelon on 2018/12/5.
 */

public interface OkHttpDataListener<T> {
    void success(T data);
    void fail(String msg);

}
