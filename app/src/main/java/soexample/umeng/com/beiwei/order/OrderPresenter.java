package soexample.umeng.com.beiwei.order;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.OrderBean;

/**
 * Created by Shinelon on 2018/12/15.
 */

public class OrderPresenter {
    private OrderView ov;
    private OrderModel model;
    public void attach(OrderView ov){
        this.ov=ov;
        model=new OrderModel();
    }

    public void getData(int status,int page,int count){
        Type type=new TypeToken<OrderBean>(){}.getType();
        model.getData(status, page, count, new ICallBack() {
            @Override
            public void successful(Object o) {
                OrderBean data= (OrderBean) o;
                if (data!=null & "0000".equals(data.getStatus())){
                    ov.successful(data);
                }
            }

            @Override
            public void failed(Exception e) {
                ov.failed(e);
            }
        },type);
    }

    public void detach(){
        if (ov!=null){
            ov=null;
        }
    }
}
