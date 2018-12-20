package soexample.umeng.com.beiwei.popupwindow;

import soexample.umeng.com.beiwei.bean.CommodityByCategoryBean;
import soexample.umeng.com.beiwei.bean.PopBean;

/**
 * Created by Shinelon on 2018/12/18.
 */

public interface PopView {
    void getFirst(PopBean data);

    void getCommodityByCategory(CommodityByCategoryBean data);

    void getSecond(PopBean data);

    void failed(Exception e);
}
