package soexample.umeng.com.beiwei.mycircle;

import soexample.umeng.com.beiwei.bean.MyCircleBean;

/**
 * Created by Shinelon on 2018/12/13.
 */

public interface MyCircleView {
    void successful(MyCircleBean data);

    void failed(Exception e);
}
