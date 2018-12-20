package soexample.umeng.com.beiwei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.adapter.CommodityAdapter;
import soexample.umeng.com.beiwei.bean.CommodityBean;
import soexample.umeng.com.beiwei.commodity.presenter.RxxpPresenter;
import soexample.umeng.com.beiwei.commodity.view.RxxpView;

public class MissActivity extends AppCompatActivity implements RxxpView{

    @BindView(R.id.miss_main)
    XRecyclerView missMain;
    private RxxpPresenter presenter;
    private List<CommodityBean.ResultBean> list;
    private CommodityAdapter adapter;
    private int count=1;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miss);
        ButterKnife.bind(this);
        presenter=new RxxpPresenter();
        presenter.attach(this);
        initView();
        presenter.getRxxp("1003",count,10);
    }

    private void initView() {
        list=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        missMain.setLayoutManager(layoutManager);
        adapter=new CommodityAdapter(this,list);
        missMain.setAdapter(adapter);
        adapter.setOnProductClickListener(new CommodityAdapter.OnCommodityClickListener() {
            @Override
            public void onCommodityClick(int commodityId) {
                Intent intent=new Intent(MissActivity.this,WebViewActivity.class);
                String s = String.valueOf(commodityId);
                intent.putExtra("cid",s);
                startActivity(intent);
            }
        });
        missMain.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count=1;
                presenter.getRxxp("1003",count,10);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        missMain.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                count++;
                presenter.getRxxp("1003",count,10);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        missMain.loadMoreComplete();
                    }
                },2000);
            }
        });
    }
    @Override
    public void successful(CommodityBean data) {
        if (data!=null){
            if (count==1) {
                list.clear();
            }
            list.addAll(data.getResult());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
}
