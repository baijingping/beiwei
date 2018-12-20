package soexample.umeng.com.beiwei.order;

import soexample.umeng.com.beiwei.bean.OrderBean;

/**
 * Created by Shinelon on 2018/12/14.
 */

public interface OrderView {
    void successful(OrderBean data);
    void failed(Exception e);
}
