package soexample.umeng.com.beiwei.mywallet;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.MyWalletBean;

/**
 * Created by Shinelon on 2018/12/16.
 */

public class MyWalletPresenter {
    private MyWalletView mv;
    private MyWalletModel model;
    public void adtach(MyWalletView mv){
        this.mv=mv;
        model=new MyWalletModel();
    }

    public void getData(int page,int count){
        Type type=new TypeToken<MyWalletBean>(){}.getType();
        model.getData(page, count, new ICallBack() {
            @Override
            public void successful(Object o) {
                MyWalletBean data= (MyWalletBean) o;
                if (data!=null){
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
