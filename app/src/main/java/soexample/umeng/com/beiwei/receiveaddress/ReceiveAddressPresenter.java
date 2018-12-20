package soexample.umeng.com.beiwei.receiveaddress;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.RegisterBean;

/**
 * Created by Shinelon on 2018/12/12.
 */

public class ReceiveAddressPresenter {
    private ReceiveAddressView rv;
    private ReceiveAddressModel model;
    public void attach(ReceiveAddressView rv){
        this.rv=rv;
        model=new ReceiveAddressModel();
    }

    public void getData(int id,String realName,String phone,String address,String code){
        Type type=new TypeToken<RegisterBean>(){}.getType();
        model.getData(id, realName, phone, address, code, new ICallBack() {
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
