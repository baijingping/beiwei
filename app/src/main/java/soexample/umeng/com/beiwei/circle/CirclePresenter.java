package soexample.umeng.com.beiwei.circle;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.CircleBean;
import soexample.umeng.com.beiwei.bean.RegisterBean;

/**
 * Created by Shinelon on 2018/12/11.
 */

public class CirclePresenter {
    private CircleView cv;
    private CircleModel model;
    public void attach(CircleView cv){
        this.cv=cv;
        model=new CircleModel();
    }

    public void getData(int page,int count){
        Type type=new TypeToken<CircleBean>(){}.getType();
        model.getData(page, count, new ICallBack() {
            @Override
            public void successful(Object o) {
                CircleBean data= (CircleBean) o;
                if (data!=null & "0000".equals(data.getStatus())){
                    cv.successful(data);
                }
            }

            @Override
            public void failed(Exception e) {
                cv.failed(e);
            }
        },type);
    }

    public void getCircleGread(String id){
        Type type=new TypeToken<RegisterBean>(){}.getType();
        model.getData1(id, new ICallBack() {
            @Override
            public void successful(Object o) {
                RegisterBean data= (RegisterBean) o;
                if (data!=null){
                    cv.getCirceGread(data);
                }
            }

            @Override
            public void failed(Exception e) {
                    cv.failed(e);
            }
        },type);
    }
    public void getCanceCircleGread(String id){
        Type type=new TypeToken<RegisterBean>(){}.getType();
        model.getData2(id, new ICallBack() {
            @Override
            public void successful(Object o) {
                RegisterBean data= (RegisterBean) o;
                if (data!=null){
                    cv.getCanceCirceGread(data);
                }
            }

            @Override
            public void failed(Exception e) {
                cv.failed(e);
            }
        },type);
    }
    public void detach(){
        if (cv!=null){
            cv=null;
        }
    }
}
