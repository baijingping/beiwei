package soexample.umeng.com.beiwei.home.model.view;

import java.util.List;

import soexample.umeng.com.beiwei.bean.BannerBean;
import soexample.umeng.com.beiwei.bean.HomeBean;

/**
 * Created by Shinelon on 2018/12/5.
 */

public interface HomeView {
    void getBanner(BannerBean data);
    void getRxxp(List<HomeBean.ResultBean.RxxpBean> rxxp);
    void getMiss(List<HomeBean.ResultBean.MlssBean> miss);
    void getPzsh(List<HomeBean.ResultBean.PzshBean> pzsh);
    void failed(Exception e);
}
