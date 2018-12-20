package soexample.umeng.com.beiwei.order;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.RegisterBean;

/**
 * Created by Shinelon on 2018/12/18.
 */

public class ReceiptPresenter {
    private PayView pv;
    private OrderModel model;
    public void attach(PayView pv){
        this.pv=pv;
        model=new OrderModel();
    }

    public void getData(String orderId){
        Type type=new TypeToken<RegisterBean>(){}.getType();
        model.getReceipt(orderId,new ICallBack() {
            @Override
            public void successful(Object o) {
                RegisterBean data= (RegisterBean) o;
                if (data!=null){
                    pv.getPay(data);
                }
            }

            @Override
            public void failed(Exception e) {
                pv.payfailed(e);
            }
        },type);
    }

    public void detach(){
        if (pv!=null){
            pv=null;
        }
    }
}
