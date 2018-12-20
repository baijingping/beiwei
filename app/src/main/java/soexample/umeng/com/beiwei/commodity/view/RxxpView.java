package soexample.umeng.com.beiwei.commodity.view;

import soexample.umeng.com.beiwei.bean.CommodityBean;

/**
 * Created by Shinelon on 2018/12/7.
 */

public interface RxxpView {
    void successful(CommodityBean data);
    void failed(Exception e);
}
