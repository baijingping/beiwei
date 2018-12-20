package soexample.umeng.com.beiwei.user;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.UserBean;

/**
 * Created by Shinelon on 2018/12/13.
 */

public class UserPresenter {
    private UserView uv;
    private UserModel model;
    public void attach(UserView uv){
        this.uv=uv;
        model=new UserModel();
    }

    public void getData(){
        Type type=new TypeToken<UserBean>(){}.getType();
        model.getData(new ICallBack() {
            @Override
            public void successful(Object o) {
               UserBean data= (UserBean) o;
               if (data!=null & "0000".equals(data.getStatus())){
                   uv.successful(data);
               }
            }
            @Override
            public void failed(Exception e) {
               uv.failed(e);
            }
        },type);
    }

    public void detach(){
        if (uv!=null){
            uv=null;
        }
    }
}
