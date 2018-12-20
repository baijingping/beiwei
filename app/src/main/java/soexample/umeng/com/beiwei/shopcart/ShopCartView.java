package soexample.umeng.com.beiwei.shopcart;

import soexample.umeng.com.beiwei.bean.ShopCartBean;

/**
 * Created by Shinelon on 2018/12/13.
 */

public interface ShopCartView {
    void successful(ShopCartBean data);

    void failed(Exception e);
}
