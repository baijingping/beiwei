package soexample.umeng.com.beiwei.order;

import soexample.umeng.com.beiwei.bean.RegisterBean;

/**
 * Created by Shinelon on 2018/12/18.
 */

public interface PayView {
    void getPay(RegisterBean data);

    void payfailed(Exception e);
}
