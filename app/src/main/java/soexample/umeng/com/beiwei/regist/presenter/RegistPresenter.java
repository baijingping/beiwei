package soexample.umeng.com.beiwei.regist.presenter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.RegisterBean;
import soexample.umeng.com.beiwei.regist.model.RegistModel;
import soexample.umeng.com.beiwei.regist.view.RegistView;

/**
 * Created by Shinelon on 2018/12/6.
 */

public class RegistPresenter {
    private RegistView rv;
    private RegistModel model;
    public void attach(RegistView rv){
        this.rv=rv;
        model=new RegistModel();
    }

    public void getData(String phone,String pwd){
        Type type=new TypeToken<RegisterBean>(){}.getType();
        model.getData(phone, pwd, new ICallBack() {
            @Override
            public void successful(Object o) {
                RegisterBean data= (RegisterBean) o;
                if (data!=null){
                    rv.successful(data);
                }
            }

            @Override
            public void failed(Exception e) {
              rv.failed(e);
            }
        },type);
    }

    public void detach(){
        if (rv!=null){
            rv=null;
        }
    }
}
