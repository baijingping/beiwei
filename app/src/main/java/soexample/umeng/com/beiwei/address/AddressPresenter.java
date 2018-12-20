package soexample.umeng.com.beiwei.address;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.AddressBean;

/**
 * Created by Shinelon on 2018/12/12.
 */

public class AddressPresenter {
    private AddressView av;
    private AddressModel model;
    public void attach(AddressView av){
        this.av=av;
        model=new AddressModel();
    }

    public void getData(){
        Type type=new TypeToken<AddressBean>(){}.getType();
        model.getData(new ICallBack() {
            @Override
            public void successful(Object o) {
                AddressBean data= (AddressBean) o;
                if (data!=null & "0000".equals(data.getStatus())){
                    av.successful(data);
                }
            }

            @Override
            public void failed(Exception e) {
                av.failed(e);
            }
        },type);
    }

    public void detach(){
        if (av!=null){
            av=null;
        }
    }
}
