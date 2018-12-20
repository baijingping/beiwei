package soexample.umeng.com.beiwei.orderfragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.adapter.EvaluationAdapter;
import soexample.umeng.com.beiwei.adapter.GoodsAdapter;
import soexample.umeng.com.beiwei.bean.OrderBean;
import soexample.umeng.com.beiwei.order.OrderPresenter;
import soexample.umeng.com.beiwei.order.OrderView;

/**
 * Created by Shinelon on 2018/12/14.
 */

public class EvaluationFragMent extends Fragment implements OrderView{
    @BindView(R.id.evaluation_main)
    XRecyclerView evaluationMain;
    Unbinder unbinder;
    private OrderPresenter presenter;
    private List<OrderBean.OrderListBean> list;
    private EvaluationAdapter adapter;
    private int count=1;
    private Handler handler=new Handler();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.evaluationfragment_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter=new OrderPresenter();
        presenter.attach(this);
        initView();
        presenter.getData(3,1,5);
    }
    private void initView() {
        list=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        evaluationMain.setLayoutManager(layoutManager);
        adapter=new EvaluationAdapter(getActivity(),list);
        evaluationMain.setAdapter(adapter);
        evaluationMain.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count=1;
                presenter.getData(3,count,5);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        evaluationMain.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                count++;
                presenter.getData(3,count,5);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        evaluationMain.loadMoreComplete();
                    }
                },2000);
            }
        });
    }

    @Override
    public void successful(OrderBean data) {
        if (data!=null){
            if (count==1) {
                list.clear();
            }
            list.addAll(data.getOrderList());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(getActivity(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    public void onResume() {
        super.onResume();
        presenter.getData(3,1,5);
    }
}
