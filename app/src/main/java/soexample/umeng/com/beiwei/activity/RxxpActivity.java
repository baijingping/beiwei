package soexample.umeng.com.beiwei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class RxxpActivity extends AppCompatActivity implements RxxpView{
    private static final String TAG = "RxxpActivity";
    @BindView(R.id.rxxp_main)
    XRecyclerView rxxpMain;
    private RxxpPresenter presenter;
    private List<CommodityBean.ResultBean> list;
    private CommodityAdapter adapter;
    private int count=1;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxxp);
        ButterKnife.bind(this);
        presenter=new RxxpPresenter();
        presenter.attach(this);
        initView();
        presenter.getRxxp("1002",1,10);
    }

    private void initView() {
        list=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        rxxpMain.setLayoutManager(layoutManager);
        adapter=new CommodityAdapter(this,list);
        rxxpMain.setAdapter(adapter);
        adapter.setOnProductClickListener(new CommodityAdapter.OnCommodityClickListener() {
            @Override
            public void onCommodityClick(int commodityId) {
                Log.d(TAG, "onCommodityClick: "+commodityId);
                Intent intent=new Intent(RxxpActivity.this,WebViewActivity.class);
                String s = String.valueOf(commodityId);
                Log.d(TAG, "onCommodityClick: "+s);
                intent.putExtra("cid",s);
                startActivity(intent);
            }
        });
        rxxpMain.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count=1;
                presenter.getRxxp("1003",count,10);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rxxpMain.refreshComplete();
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
                        rxxpMain.loadMoreComplete();
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
