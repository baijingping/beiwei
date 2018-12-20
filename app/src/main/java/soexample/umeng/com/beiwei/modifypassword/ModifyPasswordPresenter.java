package soexample.umeng.com.beiwei.modifypassword;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.RegisterBean;
import soexample.umeng.com.beiwei.modifyname.ModifyNameModel;
import soexample.umeng.com.beiwei.modifyname.ModifyNamwView;

/**
 * Created by Shinelon on 2018/12/13.
 */

public class ModifyPasswordPresenter {
    private ModifyPasswordView mv;
    private ModifyPasswordModel model;
    public void attach(ModifyPasswordView mv){
        this.mv=mv;
        model=new ModifyPasswordModel();
    }

    public void getData(String yPas,String xPas){
        Type type=new TypeToken<RegisterBean>(){}.getType();
        model.getData(yPas,xPas,new ICallBack() {
            @Override
            public void successful(Object o) {
               RegisterBean data= (RegisterBean) o;
               if (data!=null & "0000".equals(data.getStatus())){
                   mv.successful(data);
               }
            }

            @Override
            public void failed(Exception e) {
               mv.failed(e);
            }
        },type);
    }

    public void detach(){
        if (mv!=null){
            mv=null;
        }
    }
}

