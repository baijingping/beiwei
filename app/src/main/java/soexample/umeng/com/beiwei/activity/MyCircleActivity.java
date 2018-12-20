package soexample.umeng.com.beiwei.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.adapter.MyCircleAdapter;
import soexample.umeng.com.beiwei.bean.MyCircleBean;
import soexample.umeng.com.beiwei.mycircle.MyCirclePresenter;
import soexample.umeng.com.beiwei.mycircle.MyCircleView;

public class MyCircleActivity extends AppCompatActivity implements MyCircleView{

    @BindView(R.id.circle_delect)
    ImageView circleDelect;
    @BindView(R.id.my_circle_main)
    XRecyclerView myCircleMain;
    private MyCirclePresenter presenter;
    private List<MyCircleBean.ResultBean> list;
    private MyCircleAdapter adapter;
    private int count=1;
    private Handler handler=new Handler();
    private boolean isCheck=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        ButterKnife.bind(this);
        presenter=new MyCirclePresenter();
        presenter.attach(this);
        initView();
        presenter.getData(1,5);
    }

    private void initView() {
        list=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        myCircleMain.setLayoutManager(layoutManager);
        adapter=new MyCircleAdapter(this,list);
        myCircleMain.setAdapter(adapter);
        myCircleMain.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count=1;
                presenter.getData(count,5);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        myCircleMain.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                count++;
                presenter.getData(count,5);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        myCircleMain.loadMoreComplete();
                    }
                },2000);
            }
        });
    }

    @OnClick(R.id.circle_delect)
    public void onViewClicked() {
        isCheck=!isCheck;
        adapter.getCheck(isCheck);
        if (isCheck){
            circleDelect.setImageResource(R.mipmap.common_icon_n_hdpi);
        }else {
            circleDelect.setImageResource(R.mipmap.common_btn_delete_n_hdpi);
        }
    }

    @Override
    public void successful(MyCircleBean data) {
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
