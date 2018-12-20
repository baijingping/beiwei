package soexample.umeng.com.beiwei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.adapter.ShoeProductAdapter;
import soexample.umeng.com.beiwei.bean.ShowProductBean;
import soexample.umeng.com.beiwei.showproduct.presenter.ShowPrpductPresenter;
import soexample.umeng.com.beiwei.showproduct.view.ShowProductView;

public class ShowProductActivity extends AppCompatActivity implements ShowProductView{
    private static final String TAG = "ShowProductActivity";

    @BindView(R.id.product_show)
    RecyclerView productShow;
    private ShowPrpductPresenter presenter;
    private ShowProductBean.ResultBean list;
    private ShoeProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String s = intent.getStringExtra("cid");
        Log.d(TAG, "onCreate++++++666: "+s);
        int cid = Integer.valueOf(s);
        Log.d(TAG, "onCreate++++++: "+cid);
        presenter=new ShowPrpductPresenter();
        presenter.attach(this);
        presenter.getData(cid);
    }


    @Override
    public void successful(ShowProductBean data) {
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        productShow.setLayoutManager(layoutManager);
        adapter=new ShoeProductAdapter(ShowProductActivity.this,data.getResult());
        productShow.setAdapter(adapter);
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
