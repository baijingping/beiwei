package soexample.umeng.com.beiwei.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.adapter.MyWalletAdapter;
import soexample.umeng.com.beiwei.bean.MyWalletBean;
import soexample.umeng.com.beiwei.mywallet.MyWalletPresenter;
import soexample.umeng.com.beiwei.mywallet.MyWalletView;

public class WalletActivity extends AppCompatActivity implements MyWalletView{

    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.wallet_main)
    RecyclerView walletMain;
    private MyWalletPresenter presenter;
    private List<MyWalletBean.ResultBean.DetailListBean> list;
    private MyWalletAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        ButterKnife.bind(this);
        presenter=new MyWalletPresenter();
        presenter.adtach(this);
        presenter.getData(1,10);
        initView();
    }

    private void initView() {
        list=new ArrayList<>();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        walletMain.setLayoutManager(layoutManager);
        adapter=new MyWalletAdapter(this,list);
        walletMain.setAdapter(adapter);
    }

    @Override
    public void successful(MyWalletBean data) {
        if (data!=null){
            balance.setText(data.getResult().getBalance()+"");
            list.clear();
            list.addAll(data.getResult().getDetailList());
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
