package soexample.umeng.com.beiwei.shopcart;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.ShopCartBean;

/**
 * Created by Shinelon on 2018/12/13.
 */

public class ShopCartPresenter {
    private ShopCartView sv;
    private ShopCartModel model;
    public void attach(ShopCartView sv){
        this.sv=sv;
        model=new ShopCartModel();
    }

    public void getData(){
        Type type=new TypeToken<ShopCartBean>(){}.getType();
        model.getData(new ICallBack() {
            @Override
            public void successful(Object o) {
                ShopCartBean data= (ShopCartBean) o;
                if (data!=null & "0000".equals(data.getStatus())){
                    sv.successful(data);
                }
            }

            @Override
            public void failed(Exception e) {
                sv.failed(e);
            }
        },type);
    }

    public void detach(){
        if (sv!=null){
            sv=null;
        }
    }
}
