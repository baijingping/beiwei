package soexample.umeng.com.beiwei.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.RegisterBean;
import soexample.umeng.com.beiwei.newaddress.NewAddressPresenter;
import soexample.umeng.com.beiwei.newaddress.NewAddressView;

public class NewAddressActivity extends AppCompatActivity implements NewAddressView{

    @BindView(R.id.save)
    Button save;
    @BindView(R.id.new_name)
    EditText newName;
    @BindView(R.id.new_phone)
    EditText newPhone;
    @BindView(R.id.new_region)
    EditText newRegion;
    @BindView(R.id.new_address)
    EditText newAddress;
    @BindView(R.id.new_code)
    EditText newCode;
    private NewAddressPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address);
        ButterKnife.bind(this);
        presenter=new NewAddressPresenter();
        presenter.attach(this);
    }

    @OnClick(R.id.save)
    public void onViewClicked() {
        String name= newName.getText().toString().trim();
        String phone = newPhone.getText().toString().trim();
        String address = newAddress.getText().toString().trim();
        String region = newRegion.getText().toString().trim();
        String code = newCode.getText().toString().trim();
        String newAddress=region+" "+address;
        presenter.getData(name,phone,newAddress,code);
    }

    @Override
    public void successful(RegisterBean data) {
        if (data!=null){
            Toast.makeText(this,""+data.getMessage(),Toast.LENGTH_SHORT).show();
            finish();
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
