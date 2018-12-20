package soexample.umeng.com.beiwei.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.adapter.FootAdapter;
import soexample.umeng.com.beiwei.bean.FootBean;
import soexample.umeng.com.beiwei.foot.FootPresenter;
import soexample.umeng.com.beiwei.foot.FootView;

public class FootPrintActivity extends AppCompatActivity implements FootView{

    @BindView(R.id.foot_main)
    XRecyclerView footMain;
    private FootPresenter presenter;
    private List<FootBean.ResultBean> list;
    private FootAdapter adapter;
    private Handler handler=new Handler();
    private int num=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_print);
        ButterKnife.bind(this);
        presenter=new FootPresenter();
        presenter.attach(this);
        initView();
        presenter.getData(num,5);
    }

    private void initView() {
        list=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        footMain.setLayoutManager(layoutManager);
        adapter=new FootAdapter(this,list);
        footMain.setAdapter(adapter);
        footMain.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
               num=1;
               presenter.getData(num,5);
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       footMain.refreshComplete();
                   }
               });
            }

            @Override
            public void onLoadMore() {
               num++;
               presenter.getData(num,5);
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       footMain.loadMoreComplete();
                   }
               });
            }
        });
    }

    @Override
    public void successful(FootBean data) {
        if (data!=null){
            if (num==1) {
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
