package soexample.umeng.com.beiwei.mywallet;

import soexample.umeng.com.beiwei.bean.MyWalletBean;

/**
 * Created by Shinelon on 2018/12/16.
 */

public interface MyWalletView {
    void successful(MyWalletBean data);

    void failed(Exception e);
}
