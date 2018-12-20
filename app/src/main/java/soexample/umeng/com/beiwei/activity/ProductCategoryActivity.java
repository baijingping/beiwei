package soexample.umeng.com.beiwei.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.adapter.BtnAdapter;
import soexample.umeng.com.beiwei.adapter.ProductCategoryAdapter;
import soexample.umeng.com.beiwei.adapter.TopAdapter;
import soexample.umeng.com.beiwei.bean.CommodityByCategoryBean;
import soexample.umeng.com.beiwei.bean.PopBean;
import soexample.umeng.com.beiwei.popupwindow.PopPresenter;
import soexample.umeng.com.beiwei.popupwindow.PopView;

public class ProductCategoryActivity extends AppCompatActivity implements PopView{
    private RecyclerView topMain,btnMain;
    @BindView(R.id.category_choose)
    ImageView categoryChoose;
    @BindView(R.id.product_category_main)
    XRecyclerView productCategoryMain;
    private PopPresenter presenter;
    private List<PopBean.ResultBean> topList;
    private List<PopBean.ResultBean> btnList;
    private List<CommodityByCategoryBean.ResultBean> list;
    private TopAdapter topAdapter;
    private BtnAdapter btnAdapter;
    private ProductCategoryAdapter adapter;
    private int count=1;
    private String cid;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);
        ButterKnife.bind(this);
        initView();
        presenter.getFirst();
    }

    private void initView() {
        presenter=new PopPresenter();
        presenter.attach(this);
        topList=new ArrayList<>();
        topAdapter=new TopAdapter(this,topList);
        btnList=new ArrayList<>();
        btnAdapter=new BtnAdapter(this,btnList);

        list=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
        productCategoryMain.setLayoutManager(layoutManager);
        adapter=new ProductCategoryAdapter(this,list);
        productCategoryMain.setAdapter(adapter);
        productCategoryMain.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count=1;
                presenter.getCommodityByCategory(cid,count,5);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        productCategoryMain.refreshComplete();
                    }
                },3000);
            }

            @Override
            public void onLoadMore() {
                count++;
                presenter.getCommodityByCategory(cid,count,5);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        productCategoryMain.loadMoreComplete();
                    }
                },3000);
            }
        });
    }

    @OnClick(R.id.category_choose)
    public void onViewClicked() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_item, null, false);
        PopupWindow window=new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,350,true);
        window.setBackgroundDrawable(new ColorDrawable());
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAsDropDown(categoryChoose);
        topMain=view.findViewById(R.id.top_main);
        btnMain=view.findViewById(R.id.btn_main);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        topMain.setLayoutManager(layoutManager);
        topMain.setAdapter(topAdapter);
        topAdapter.setOnTopClickListener(new TopAdapter.OnTopClickListener() {
            @Override
            public void onTopClick(String id) {
                presenter.getSecond(id);
            }
        });
        RecyclerView.LayoutManager layoutManager1=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        btnMain.setLayoutManager(layoutManager1);
        btnMain.setAdapter(btnAdapter);
        btnAdapter.setOnBtnClickListener(new BtnAdapter.OnBtnClickListener() {
            @Override
            public void onBtnClick(String id) {
                cid=id;
                presenter.getCommodityByCategory(id,count,5);
            }
        });

    }


    @Override
    public void getFirst(PopBean data) {
        if (data!=null){
            topList.clear();
            topList.addAll(data.getResult());
            topAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getCommodityByCategory(CommodityByCategoryBean data) {
          if (data!=null){
              if (count==1) {
                  list.clear();
              }
              list.addAll(data.getResult());
              adapter.notifyDataSetChanged();
          }
    }

    @Override
    public void getSecond(PopBean data) {
        if (data!=null){
            btnList.clear();
            btnList.addAll(data.getResult());
            btnAdapter.notifyDataSetChanged();
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
