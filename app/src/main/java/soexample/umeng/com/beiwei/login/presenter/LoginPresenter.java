package soexample.umeng.com.beiwei.login.presenter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.LoginBean;
import soexample.umeng.com.beiwei.login.model.LoginModel;
import soexample.umeng.com.beiwei.login.view.LoginView;

/**
 * Created by Shinelon on 2018/12/5.
 */

public class LoginPresenter {
    private LoginView iv;
    private LoginModel model;
    public void attach(LoginView iv){
        this.iv=iv;
        model=new LoginModel();
    }

    public void getData(String phone,String pwd){
        Type type=new TypeToken<LoginBean>(){}.getType();
        model.getData(phone, pwd, new ICallBack() {
            @Override
            public void successful(Object o) {
                LoginBean data= (LoginBean) o;
                if (data!=null){
                    iv.successful(data);
                }
            }

            @Override
            public void failed(Exception e) {
                iv.failed(e);
            }
        },type);
    }

    public void detach(){
        if (iv!=null){
            iv=null;
        }
    }
}
