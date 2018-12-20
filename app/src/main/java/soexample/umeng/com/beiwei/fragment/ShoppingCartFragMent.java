package soexample.umeng.com.beiwei.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.adapter.ShopCartAdapter;
import soexample.umeng.com.beiwei.bean.ShopCartBean;
import soexample.umeng.com.beiwei.shopcart.ShopCartPresenter;
import soexample.umeng.com.beiwei.shopcart.ShopCartView;


/**
 * Created by Shinelon on 2018/11/29.
 */

public class ShoppingCartFragMent extends Fragment implements ShopCartView{
    private static final String TAG = "ShoppingCartFragMent";
    @BindView(R.id.shop_check)
    CheckBox shopCheck;
    @BindView(R.id.shop_sum)
    TextView shopSum;
    @BindView(R.id.shop_settlement)
    TextView shopSettlement;
    @BindView(R.id.shop_main)
    RecyclerView shopMain;
    Unbinder unbinder;
    private ShopCartPresenter presenter;
    private List<ShopCartBean.ResultBean> list;
    private ShopCartAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_cart_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter=new ShopCartPresenter();
        presenter.attach(this);
        initView();
        presenter.getData();
    }

    private void initView() {
        list=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        shopMain.setLayoutManager(layoutManager);
        adapter=new ShopCartAdapter(getActivity(),list);
        shopMain.setAdapter(adapter);
        shopCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = shopCheck.isChecked();
                for (ShopCartBean.ResultBean resultBean : list) {
                    resultBean.setCheck(checked);
                }
                adapter.notifyDataSetChanged();
                caclulatePrice();
            }
        });
        adapter.setOnShopCartClickListener(new ShopCartAdapter.OnShopCartClickListener() {
            @Override
            public void onShopCartClick(int position, boolean isChecked) {
                if (!isChecked){
                    shopCheck.setChecked(false);
                }else {
                    boolean isAllSelected=true;
                    for (ShopCartBean.ResultBean resultBean : list) {
                        if (!resultBean.isCheck()){
                            isAllSelected=false;
                            break;
                        }
                    }
                    shopCheck.setChecked(isAllSelected);
                    adapter.notifyDataSetChanged();
                }
                caclulatePrice();
            }
        });
        adapter.setOnAddDecreaseListener(new ShopCartAdapter.OnAddDecreaseListener() {
            @Override
            public void onChang(int position, int num) {
                caclulatePrice();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    

    @Override
    public void successful(ShopCartBean data) {
        if (data!=null){
            list.clear();
            list.addAll(data.getResult());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(getActivity(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    public void caclulatePrice(){
        float price=0;
        for (ShopCartBean.ResultBean resultBean : list) {
            if (resultBean.isCheck()) {
                price+=resultBean.getPrice()*resultBean.getCount();
            }
        }
        shopSum.setText("总价:"+price);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
}
