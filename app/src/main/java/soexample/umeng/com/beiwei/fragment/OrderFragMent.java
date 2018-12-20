package soexample.umeng.com.beiwei.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.orderfragment.CompleteFragMent;
import soexample.umeng.com.beiwei.orderfragment.EvaluationFragMent;
import soexample.umeng.com.beiwei.orderfragment.GoodsFragMent;
import soexample.umeng.com.beiwei.orderfragment.OrdersFragMent;
import soexample.umeng.com.beiwei.orderfragment.PaymentFragMent;


/**
 * Created by Shinelon on 2018/11/29.
 */

public class OrderFragMent extends Fragment {
    @BindView(R.id.orders)
    LinearLayout orders;
    @BindView(R.id.payment)
    LinearLayout payment;
    @BindView(R.id.goods)
    LinearLayout goods;
    @BindView(R.id.evaluation)
    LinearLayout evaluation;
    @BindView(R.id.complete)
    LinearLayout complete;
    @BindView(R.id.page)
    ViewPager page;
    Unbinder unbinder;
    private List<Fragment> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.found_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list = new ArrayList<>();
        list.add(new OrdersFragMent());
        list.add(new PaymentFragMent());
        list.add(new GoodsFragMent());
        list.add(new EvaluationFragMent());
        list.add(new CompleteFragMent());
        page.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.orders, R.id.payment, R.id.goods, R.id.evaluation, R.id.complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.orders:
                page.setCurrentItem(0);
                break;
            case R.id.payment:
                page.setCurrentItem(1);
                break;
            case R.id.goods:
                page.setCurrentItem(2);
                break;
            case R.id.evaluation:
                page.setCurrentItem(3);
                break;
            case R.id.complete:
                page.setCurrentItem(4);
                break;
        }
    }
}
