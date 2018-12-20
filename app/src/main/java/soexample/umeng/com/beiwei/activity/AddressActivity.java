package soexample.umeng.com.beiwei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.adapter.AddressAdapter;
import soexample.umeng.com.beiwei.address.AddressPresenter;
import soexample.umeng.com.beiwei.address.AddressView;
import soexample.umeng.com.beiwei.bean.AddressBean;

public class AddressActivity extends AppCompatActivity implements AddressView{

    @BindView(R.id.address_complete)
    TextView addressComplete;
    @BindView(R.id.add_address)
    Button addAddress;
    @BindView(R.id.address_main)
    RecyclerView addressMain;
    private AddressPresenter presenter;
    private List<AddressBean.ResultBean> list;
    private AddressAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        presenter=new AddressPresenter();
        presenter.attach(this);
        initView();
        presenter.getData();
    }

    private void initView() {
        list=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        addressMain.setLayoutManager(layoutManager);
        adapter=new AddressAdapter(this,list);
        addressMain.setAdapter(adapter);
        adapter.setOnAddressClickListener(new AddressAdapter.OnAddressClickListener() {
            @Override
            public void onAddressClick(int id, String name, String phone, String address, String code) {
                  Intent intent=new Intent(AddressActivity.this,ReceiveAddressActivity.class);
                  intent.putExtra("id",id);
                  intent.putExtra("name",name);
                  intent.putExtra("phone",phone);
                  intent.putExtra("address",address);
                  intent.putExtra("code",code);
                  startActivity(intent);
            }
        });
    }

    @OnClick({R.id.address_complete, R.id.add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.address_complete:
                break;
            case R.id.add_address:
                Intent intent=new Intent(this,NewAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void successful(AddressBean data) {
        if (data!=null){
            list.clear();
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

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getData();
    }
}
