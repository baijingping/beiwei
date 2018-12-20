package soexample.umeng.com.beiwei.newaddress;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.RegisterBean;

/**
 * Created by Shinelon on 2018/12/12.
 */

public class NewAddressPresenter {
    private NewAddressView nv;
    private NewAddressModel model;
    public void attach(NewAddressView nv){
        this.nv=nv;
        model=new NewAddressModel();
    }

    public void getData(String realName,String phone,String address,String zipCode){
        Type type=new TypeToken<RegisterBean>(){}.getType();
        model.getData(realName, phone, address, zipCode, new ICallBack() {
            @Override
            public void successful(Object o) {
               RegisterBean data= (RegisterBean) o;
               if (data!=null & "0000".equals(data.getStatus())){
                   nv.successful(data);
               }else {
                   nv.failed(new Exception(data.getMessage()));
               }
            }

            @Override
            public void failed(Exception e) {
               nv.failed(e);
            }
        },type);
    }

    public void detach(){
        if (nv!=null){
            nv=null;
        }
    }
}
