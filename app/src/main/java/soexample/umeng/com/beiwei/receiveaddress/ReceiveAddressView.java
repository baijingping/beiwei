package soexample.umeng.com.beiwei.receiveaddress;

import soexample.umeng.com.beiwei.bean.RegisterBean;

/**
 * Created by Shinelon on 2018/12/12.
 */

public interface ReceiveAddressView {
    void successful(RegisterBean data);

    void failed(Exception e);
}
