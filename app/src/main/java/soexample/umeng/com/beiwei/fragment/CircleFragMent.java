package soexample.umeng.com.beiwei.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.adapter.CircleAdapter;
import soexample.umeng.com.beiwei.bean.CircleBean;
import soexample.umeng.com.beiwei.bean.RegisterBean;
import soexample.umeng.com.beiwei.circle.CirclePresenter;
import soexample.umeng.com.beiwei.circle.CircleView;


/**
 * Created by Shinelon on 2018/11/29.
 */

public class CircleFragMent extends Fragment implements CircleView{
    private static final String TAG = "CircleFragMent";
    @BindView(R.id.circle_main)
    XRecyclerView circleMain;
    Unbinder unbinder;
    private CirclePresenter presenter;
    private List<CircleBean.ResultBean> list;
    private CircleAdapter adapter;
    private int count=1;
    private Handler handler=new Handler();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.circle_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter=new CirclePresenter();
        presenter.attach(this);
        initView();
        presenter.getData(count,5);
    }

    private void initView() {
        list=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        circleMain.setLayoutManager(layoutManager);
        adapter=new CircleAdapter(getActivity(),list);
        circleMain.setAdapter(adapter);

        adapter.setOnCircleClickListener(new CircleAdapter.OnCircleClickListener() {
            @Override
            public void onCircleClick(int id, boolean isChecked) {
                Log.d(TAG, "onCircleClick: "+isChecked);
                if (isChecked){
                    String s = String.valueOf(id);
                    presenter.getCircleGread(s);
                }else {
                    String s = String.valueOf(id);
                    presenter.getCanceCircleGread(s);
                }
            }
        });

        circleMain.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count=1;
                presenter.getData(count,5);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        circleMain.refreshComplete();
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
                        circleMain.loadMoreComplete();
                    }
                },2000);
            }
        });
    }

    @Override
    public void successful(CircleBean data) {
         if (data!=null){
             if (count==1) {
                 list.clear();
             }
             list.addAll(data.getResult());
             adapter.notifyDataSetChanged();
         }
    }

    @Override
    public void getCirceGread(RegisterBean data) {
        Toast.makeText(getActivity(),""+data.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCanceCirceGread(RegisterBean data) {
        Toast.makeText(getActivity(),""+data.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(getActivity(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
}
