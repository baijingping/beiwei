package soexample.umeng.com.beiwei.utils;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Shinelon on 2018/12/16.
 */

public interface BaseUtils {
    @Multipart
    @POST
    Observable<ResponseBody> part(@Url String url, @HeaderMap Map<String,String> map, @Part MultipartBody.Part part);
}
