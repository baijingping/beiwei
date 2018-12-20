package soexample.umeng.com.beiwei.foot;

import soexample.umeng.com.beiwei.bean.FootBean;

/**
 * Created by Shinelon on 2018/12/11.
 */

public interface FootView {
    void successful(FootBean data);

    void failed(Exception e);
}
