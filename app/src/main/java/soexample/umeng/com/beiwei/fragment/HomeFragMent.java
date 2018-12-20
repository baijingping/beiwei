package soexample.umeng.com.beiwei.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.activity.MissActivity;
import soexample.umeng.com.beiwei.activity.ProductCategoryActivity;
import soexample.umeng.com.beiwei.activity.PzshActivity;
import soexample.umeng.com.beiwei.activity.RxxpActivity;
import soexample.umeng.com.beiwei.activity.SearchActivity;
import soexample.umeng.com.beiwei.adapter.BannerAdapter;
import soexample.umeng.com.beiwei.adapter.MissAdapter;
import soexample.umeng.com.beiwei.adapter.PzshAdapter;
import soexample.umeng.com.beiwei.adapter.RxxpAdapter;
import soexample.umeng.com.beiwei.bean.BannerBean;
import soexample.umeng.com.beiwei.bean.HomeBean;
import soexample.umeng.com.beiwei.home.model.presenter.HomePresenter;
import soexample.umeng.com.beiwei.home.model.view.HomeView;


/**
 * Created by Shinelon on 2018/11/29.
 */

public class HomeFragMent extends Fragment implements HomeView {
    private static final String TAG = "HomeFragMent";
    @BindView(R.id.banner)
    RecyclerCoverFlow banner;
    Unbinder unbinder;
    @BindView(R.id.rxxp)
    RecyclerView rxxp;
    @BindView(R.id.miss)
    RecyclerView miss;
    @BindView(R.id.pzsh)
    RecyclerView pzsh;
    @BindView(R.id.btn_rxxp)
    ImageView btnRxxp;
    @BindView(R.id.btn_miss)
    ImageView btnMiss;
    @BindView(R.id.btn_pzsh)
    ImageView btnPzsh;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.img_choose)
    ImageView imgChoose;
    private HomePresenter presenter;
    private List<BannerBean.ResultBean> bannerList;
    private BannerAdapter bannerAdapter;
    private List<HomeBean.ResultBean.RxxpBean.CommodityListBean> rxxpList;
    private List<HomeBean.ResultBean.MlssBean.CommodityListBeanXX> missList;
    private List<HomeBean.ResultBean.PzshBean.CommodityListBeanX> pzshList;
    private RxxpAdapter rxxpAdapter;
    private MissAdapter missAdapter;
    private PzshAdapter pzshAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new HomePresenter();
        presenter.attach(this);
        initData();
        presenter.getBanner();
        presenter.getHome();
        imgChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ProductCategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        bannerList = new ArrayList<>();
        bannerAdapter = new BannerAdapter(getActivity(), bannerList);
        banner.setAdapter(bannerAdapter);
        banner.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            @Override
            public void onItemSelected(int position) {
                banner.getLayoutManager().getItemCount();
            }
        });
        //rxxp
        rxxpList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rxxp.setLayoutManager(layoutManager);
        rxxpAdapter = new RxxpAdapter(getActivity(), rxxpList);
        rxxp.setAdapter(rxxpAdapter);
        //miss
        missList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
        miss.setLayoutManager(layoutManager1);
        missAdapter = new MissAdapter(getActivity(), missList);
        miss.setAdapter(missAdapter);
        //pzsh
        pzshList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(getActivity(), 2);
        pzsh.setLayoutManager(layoutManager2);
        pzshAdapter = new PzshAdapter(getActivity(), pzshList);
        pzsh.setAdapter(pzshAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getBanner(BannerBean data) {
        if (data != null) {
            bannerList.clear();
            bannerList.addAll(data.getResult());
            bannerAdapter.notifyDataSetChanged();
            banner.scrollToPosition(2);
        }
    }

    @Override
    public void getRxxp(List<HomeBean.ResultBean.RxxpBean> rxxp) {
        if (rxxp != null) {
            for (HomeBean.ResultBean.RxxpBean rxxpBean : rxxp) {
                rxxpList.clear();
                rxxpList.addAll(rxxpBean.getCommodityList());
                rxxpAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void getMiss(List<HomeBean.ResultBean.MlssBean> miss) {
        if (miss != null) {
            for (HomeBean.ResultBean.MlssBean mlssBean : miss) {
                missList.clear();
                missList.addAll(mlssBean.getCommodityList());
                missAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void getPzsh(List<HomeBean.ResultBean.PzshBean> pzsh) {
        if (pzsh != null) {
            for (HomeBean.ResultBean.PzshBean pzshBean : pzsh) {
                pzshList.clear();
                pzshList.addAll(pzshBean.getCommodityList());
                pzshAdapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void failed(Exception e) {
        Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn_rxxp, R.id.btn_miss, R.id.btn_pzsh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_rxxp:
                Intent intent = new Intent(getActivity(), RxxpActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_miss:
                Intent intent1 = new Intent(getActivity(), MissActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_pzsh:
                Intent intent2 = new Intent(getActivity(), PzshActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @OnClick(R.id.search)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);
    }

}
