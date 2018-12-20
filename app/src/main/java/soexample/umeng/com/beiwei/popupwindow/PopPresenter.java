package soexample.umeng.com.beiwei.popupwindow;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.CommodityByCategoryBean;
import soexample.umeng.com.beiwei.bean.PopBean;

/**
 * Created by Shinelon on 2018/12/18.
 */

public class PopPresenter {
    private PopView pv;
    private PopModel model;
    public void attach(PopView pv){
        this.pv=pv;
        model=new PopModel();
    }

    public void getFirst(){
        Type type=new TypeToken<PopBean>(){}.getType();
        model.getFirst(new ICallBack() {
            @Override
            public void successful(Object o) {
                PopBean data= (PopBean) o;
                if (data!=null && "0000".equals(data.getStatus())){
                    pv.getFirst(data);
                }
            }

            @Override
            public void failed(Exception e) {
               pv.failed(e);
            }
        },type);
    }
    public void getSecond(String firstCategoryId){
        Type type=new TypeToken<PopBean>(){}.getType();
        model.getSecond(firstCategoryId,new ICallBack() {
            @Override
            public void successful(Object o) {
                PopBean data= (PopBean) o;
                if (data!=null && "0000".equals(data.getStatus())){
                    pv.getSecond(data);
                }
            }

            @Override
            public void failed(Exception e) {
                pv.failed(e);
            }
        },type);
    }

    public void getCommodityByCategory(String categoryId,int page,int count){
        Type type=new TypeToken<CommodityByCategoryBean>(){}.getType();
        model.getCommodityByCategory(categoryId,page,count,new ICallBack() {
            @Override
            public void successful(Object o) {
                CommodityByCategoryBean data= (CommodityByCategoryBean) o;
                if (data!=null && "0000".equals(data.getStatus())){
                    pv.getCommodityByCategory(data);
                }
            }

            @Override
            public void failed(Exception e) {
                pv.failed(e);
            }
        },type);
    }
    public void detach(){
        if (pv!=null){
            pv=null;
        }
    }
}
