package soexample.umeng.com.beiwei.circle;

import soexample.umeng.com.beiwei.bean.CircleBean;
import soexample.umeng.com.beiwei.bean.RegisterBean;

/**
 * Created by Shinelon on 2018/12/11.
 */

public interface CircleView {
    void successful(CircleBean data);

    void getCirceGread(RegisterBean data);

    void getCanceCirceGread(RegisterBean data);

    void failed(Exception e);
}
