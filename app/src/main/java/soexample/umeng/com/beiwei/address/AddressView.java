package soexample.umeng.com.beiwei.address;

import soexample.umeng.com.beiwei.bean.AddressBean;

/**
 * Created by Shinelon on 2018/12/12.
 */

public interface AddressView {
    void successful(AddressBean data);

    void failed(Exception e);
}
