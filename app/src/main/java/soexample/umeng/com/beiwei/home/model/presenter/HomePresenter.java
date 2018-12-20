package soexample.umeng.com.beiwei.home.model.presenter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.BannerBean;
import soexample.umeng.com.beiwei.bean.HomeBean;
import soexample.umeng.com.beiwei.home.model.HomeModel;
import soexample.umeng.com.beiwei.home.model.view.HomeView;

/**
 * Created by Shinelon on 2018/12/5.
 */

public class HomePresenter {
 private HomeView hv;
 private HomeModel model;
 public void attach(HomeView hv){
     this.hv=hv;
     model=new HomeModel();
 }

 public void getBanner(){
     Type type=new TypeToken<BannerBean>(){}.getType();
     model.getBanner(new ICallBack() {
         @Override
         public void successful(Object o) {
             BannerBean data= (BannerBean) o;
             if (data!=null){
                 hv.getBanner(data);
             }
         }

         @Override
         public void failed(Exception e) {
             hv.failed(e);
         }
     },type);
 }

 public void getHome(){
     Type type=new TypeToken<HomeBean>(){}.getType();
     model.getHome(new ICallBack() {
         @Override
         public void successful(Object o) {
             HomeBean data= (HomeBean) o;
             if (data!=null & "0000".equals(data.getStatus())){
                 hv.getRxxp(data.getResult().getRxxp());
                 hv.getMiss(data.getResult().getMlss());
                 hv.getPzsh(data.getResult().getPzsh());
             }
         }

         @Override
         public void failed(Exception e) {
                hv.failed(e);
         }
     },type);
 }

 public void detach(){
     if (hv!=null){
         hv=null;
     }
 }
}
