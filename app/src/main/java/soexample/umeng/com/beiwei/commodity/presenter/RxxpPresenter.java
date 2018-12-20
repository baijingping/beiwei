package soexample.umeng.com.beiwei.commodity.presenter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.CommodityBean;
import soexample.umeng.com.beiwei.commodity.model.RxxpModel;
import soexample.umeng.com.beiwei.commodity.view.RxxpView;

/**
 * Created by Shinelon on 2018/12/7.
 */

public class RxxpPresenter {
    private RxxpView rv;
    private RxxpModel model;
    public void attach(RxxpView rv){
        this.rv=rv;
        model=new RxxpModel();
    }

    public void getRxxp(String labelId,int page,int count){
        Type type=new TypeToken<CommodityBean>(){}.getType();
        model.getData(labelId, page, count, new ICallBack() {
            @Override
            public void successful(Object o) {
                CommodityBean data= (CommodityBean) o;
                if (data!=null&"0000".equals(data.getStatus())){
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
