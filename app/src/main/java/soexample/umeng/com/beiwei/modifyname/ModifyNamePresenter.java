package soexample.umeng.com.beiwei.modifyname;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.RegisterBean;

/**
 * Created by Shinelon on 2018/12/13.
 */

public class ModifyNamePresenter {
    private ModifyNamwView mv;
    private ModifyNameModel model;
    public void attach(ModifyNamwView mv){
        this.mv=mv;
        model=new ModifyNameModel();
    }

    public void getData(String nickName){
        Type type=new TypeToken<RegisterBean>(){}.getType();
        model.getData(nickName,new ICallBack() {
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

