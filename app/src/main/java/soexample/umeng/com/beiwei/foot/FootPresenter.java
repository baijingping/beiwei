package soexample.umeng.com.beiwei.foot;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.FootBean;

/**
 * Created by Shinelon on 2018/12/11.
 */

public class FootPresenter {
    private FootView fv;
    private FootModel model;
    public void attach(FootView fv){
        this.fv=fv;
        model=new FootModel();
    }

    public void getData(int page,int count){
        Type type=new TypeToken<FootBean>(){}.getType();
        model.getData(page, count, new ICallBack() {
            @Override
            public void successful(Object o) {
                FootBean data= (FootBean) o;
                if (data!=null&"0000".equals(data.getStatus())){
                    fv.successful(data);
                }
            }

            @Override
            public void failed(Exception e) {
                    fv.failed(e);
            }
        },type);
    }

    public void detach(){
        if (fv!=null){
            fv=null;
        }
    }
}
