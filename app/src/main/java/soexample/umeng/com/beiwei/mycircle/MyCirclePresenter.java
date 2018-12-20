package soexample.umeng.com.beiwei.mycircle;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.MyCircleBean;

/**
 * Created by Shinelon on 2018/12/13.
 */

public class MyCirclePresenter {
    private MyCircleView mv;
    private MyCircleModel model;
    public void attach(MyCircleView mv){
        this.mv=mv;
        model=new MyCircleModel();
    }

    public void getData(int page,int count){
        Type type=new TypeToken<MyCircleBean>(){}.getType();
        model.getData(page, count, new ICallBack() {
            @Override
            public void successful(Object o) {
                MyCircleBean data= (MyCircleBean) o;
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
