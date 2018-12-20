package soexample.umeng.com.beiwei.login.view;

import soexample.umeng.com.beiwei.bean.LoginBean;

/**
 * Created by Shinelon on 2018/12/5.
 */

public interface LoginView {
  void successful(LoginBean data);
  String getUserName();
  String getUserPassWord();
  void failed(Exception e);
}
