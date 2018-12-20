package soexample.umeng.com.beiwei.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.activity.AddressActivity;
import soexample.umeng.com.beiwei.activity.MyCircleActivity;
import soexample.umeng.com.beiwei.activity.DataActivity;
import soexample.umeng.com.beiwei.activity.FootPrintActivity;
import soexample.umeng.com.beiwei.activity.LoginActivity;
import soexample.umeng.com.beiwei.activity.MyActivity;
import soexample.umeng.com.beiwei.activity.WalletActivity;


/**
 * Created by Shinelon on 2018/11/29.
 */

public class MyFragMent extends Fragment {
    private static final String TAG = "MyFragMent";
    @BindView(R.id.my_name)
    TextView myName;
    @BindView(R.id.my_data)
    TextView myData;
    @BindView(R.id.my_circle)
    TextView myCircle;
    @BindView(R.id.my_footprint)
    TextView myFootprint;
    @BindView(R.id.my_wallet)
    TextView myWallet;
    @BindView(R.id.my_address)
    TextView myAddress;
    @BindView(R.id.portrait)
    ImageView portrait;
    Unbinder unbinder;
    private SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst", true);
        Log.d(TAG, "onCreateView+++++: "+isFirst);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.my_name, R.id.my_data, R.id.my_circle, R.id.my_footprint, R.id.my_wallet, R.id.my_address, R.id.portrait})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_name:
                if (!sp.getBoolean("isFirst",true)) {
                    Intent intent2 = new Intent(getActivity(),MyActivity .class);
                    startActivity(intent2);
                }else {
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.my_data:
                if (!sp.getBoolean("isFirst",true)){
                    Intent intent3=new Intent(getActivity(), DataActivity.class);
                    startActivity(intent3);
                }else {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.my_circle:
                if (!sp.getBoolean("isFirst",true)){
                    Intent intent3=new Intent(getActivity(), MyCircleActivity.class);
                    startActivity(intent3);
                }else {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.my_footprint:
                if (!sp.getBoolean("isFirst",true)){
                    Intent intent3=new Intent(getActivity(), FootPrintActivity.class);
                    startActivity(intent3);
                }else {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.my_wallet:
                if (!sp.getBoolean("isFirst",true)){
                    Intent intent3=new Intent(getActivity(), WalletActivity.class);
                    startActivity(intent3);
                }else {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.my_address:
                if (!sp.getBoolean("isFirst",true)){
                    Intent intent3=new Intent(getActivity(), AddressActivity.class);
                    startActivity(intent3);
                }else {
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.portrait:
                if (!sp.getBoolean("isFirst",true)) {
                    Intent intent2 = new Intent(getActivity(),MyActivity .class);
                    startActivity(intent2);
                }else {
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!sp.getBoolean("isFirst",true)){
            String timg = sp.getString("timg", "");
            String name = sp.getString("name", "");
            Log.d(TAG, "onCreateView66666: "+timg+"   name   "+name);
            myName.setText(name);
            Glide.with(getActivity()).load(timg).into(portrait);
        }else {
            portrait.setImageResource(R.drawable.zhanwei);
            myName.setText("请先登录/注册");
        }
    }
}
