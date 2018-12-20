package soexample.umeng.com.beiwei.modifypassword;

import soexample.umeng.com.beiwei.bean.RegisterBean;

/**
 * Created by Shinelon on 2018/12/13.
 */

public interface ModifyPasswordView {
    void successful(RegisterBean data);

    void failed(Exception e);
}
