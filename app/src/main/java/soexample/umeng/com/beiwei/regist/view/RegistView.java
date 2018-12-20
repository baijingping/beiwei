package soexample.umeng.com.beiwei.regist.view;

import soexample.umeng.com.beiwei.bean.RegisterBean;

/**
 * Created by Shinelon on 2018/12/6.
 */

public interface RegistView {
    void successful(RegisterBean data);

    void failed(Exception e);

    String getUserName();

    String getPassWord();

}
