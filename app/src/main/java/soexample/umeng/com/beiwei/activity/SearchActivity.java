package soexample.umeng.com.beiwei.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.adapter.SearchAdapter;
import soexample.umeng.com.beiwei.bean.SearchBean;
import soexample.umeng.com.beiwei.search.presenter.SearchPresenter;
import soexample.umeng.com.beiwei.search.view.SearchView;

public class SearchActivity extends AppCompatActivity implements SearchView {

    @BindView(R.id.search_right)
    TextView searchRight;
    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.search_main)
    XRecyclerView searchMain;
    @BindView(R.id.search_left)
    ImageView searchLeft;
    private SearchPresenter presenter;
    private List<SearchBean.ResultBean> list;
    private SearchAdapter adapter;
    private String name = "板鞋";
    private int count = 1;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        presenter = new SearchPresenter();
        presenter.attach(this);
        initView();
        presenter.getData(name, count, 10);
    }

    private void initView() {
        list = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        searchMain.setLayoutManager(layoutManager);
        adapter = new SearchAdapter(this, list);
        searchMain.setAdapter(adapter);
        searchMain.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count = 1;
                presenter.getData(name, count, 10);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        searchMain.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                count++;
                presenter.getData(name, count, 10);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        searchMain.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void successful(SearchBean data) {
        if (data != null) {
            if (count == 1) {
                list.clear();
            }
            list.addAll(data.getResult());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
    }


    @OnClick({R.id.search_left, R.id.search_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_left:

                break;
            case R.id.search_right:
                String s = search.getText().toString();
                if (s.length()>0) {
                    name = s;
                    count = 1;
                    presenter.getData(name, count, 10);
                }else {
                    Toast.makeText(this,"请输入您想要查找的东西",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
