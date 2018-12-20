package soexample.umeng.com.beiwei.search.view;

import soexample.umeng.com.beiwei.bean.SearchBean;

/**
 * Created by Shinelon on 2018/12/10.
 */

public interface SearchView {
    void successful(SearchBean data);

    void failed(Exception e);
}
