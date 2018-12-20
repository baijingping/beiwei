package soexample.umeng.com.beiwei.search.presenter;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import soexample.umeng.com.beiwei.ICallBack;
import soexample.umeng.com.beiwei.bean.SearchBean;
import soexample.umeng.com.beiwei.search.model.SearchModel;
import soexample.umeng.com.beiwei.search.view.SearchView;

/**
 * Created by Shinelon on 2018/12/10.
 */

public class SearchPresenter {
    private SearchView sv;
    private SearchModel model;
    public void attach(SearchView sv){
        this.sv=sv;
        model=new SearchModel();
    }

    public void getData(String keyword,int page,int count){
        Type type=new TypeToken<SearchBean>(){}.getType();
        model.getData(keyword, page, count, new ICallBack() {
            @Override
            public void successful(Object o) {
                SearchBean data= (SearchBean) o;
                if (data!=null){
                    sv.successful(data);
                }
            }

            @Override
            public void failed(Exception e) {
                 sv.failed(e);
            }
        },type);
    }

    public void detach(){
        if (sv!=null){
            sv=null;
        }
    }
}
