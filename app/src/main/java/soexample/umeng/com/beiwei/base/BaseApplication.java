package soexample.umeng.com.beiwei.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.util.HashMap;

import soexample.umeng.com.beiwei.utils.Constant;
import soexample.umeng.com.beiwei.utils.LogUtil;
import soexample.umeng.com.beiwei.utils.OkHttpUtils;

/**
 * Created by Shinelon on 2018/12/5.
 */

public class BaseApplication extends Application {
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        DiskCacheConfig.Builder builder = DiskCacheConfig.newBuilder(this);
        builder.setBaseDirectoryPath(getCacheDir());
        DiskCacheConfig config = builder.build();
        ImagePipelineConfig build = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(config)
                .build();
        Fresco.initialize(this,build);
        sContext=this;
        LogUtil.init();
        initHttpHeader();
    }

    private void initHttpHeader() {

        OkHttpUtils.init();
    }
}
