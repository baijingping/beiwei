package soexample.umeng.com.beiwei.user;

import soexample.umeng.com.beiwei.bean.UserBean;

/**
 * Created by Shinelon on 2018/12/13.
 */

public interface UserView {
    void successful(UserBean data);

    void failed(Exception e);
}
