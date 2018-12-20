package soexample.umeng.com.beiwei.showproduct.view;

import soexample.umeng.com.beiwei.bean.ShowProductBean;

/**
 * Created by Shinelon on 2018/12/7.
 */

public interface ShowProductView {
    void successful(ShowProductBean data);

    void failed(Exception e);
}
