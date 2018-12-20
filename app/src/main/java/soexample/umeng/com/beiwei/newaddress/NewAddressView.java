package soexample.umeng.com.beiwei.newaddress;

import soexample.umeng.com.beiwei.bean.RegisterBean;

/**
 * Created by Shinelon on 2018/12/12.
 */

public interface NewAddressView {
    void successful(RegisterBean data);

    void failed(Exception e);
}
