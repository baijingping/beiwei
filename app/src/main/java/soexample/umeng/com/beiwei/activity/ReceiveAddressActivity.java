package soexample.umeng.com.beiwei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.RegisterBean;
import soexample.umeng.com.beiwei.receiveaddress.ReceiveAddressPresenter;
import soexample.umeng.com.beiwei.receiveaddress.ReceiveAddressView;

public class ReceiveAddressActivity extends AppCompatActivity implements ReceiveAddressView{
    private static final String TAG = "ReceiveAddressActivity";
    @BindView(R.id.modify)
    Button modify;
    @BindView(R.id.receive_name)
    EditText receiveName;
    @BindView(R.id.receive_phone)
    EditText receivePhone;
    @BindView(R.id.receive_address)
    EditText receiveAddress;
    @BindView(R.id.receive_code)
    EditText receiveCode;
    private int id;
    private ReceiveAddressPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_address);
        ButterKnife.bind(this);
        presenter=new ReceiveAddressPresenter();
        presenter.attach(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String address = intent.getStringExtra("address");
        String code = intent.getStringExtra("code");
        Log.d(TAG, "onCreate: " + id + "  name  " + name + "  phone  " + phone + "  address  " + address + "  code  " + code);
        receiveName.setText(name);
        receivePhone.setText(phone);
        receiveAddress.setText(address);
        receiveCode.setText(code);

    }

    @OnClick(R.id.modify)
    public void onViewClicked() {
        String name = receiveName.getText().toString().trim();
        String phone = receivePhone.getText().toString().trim();
        String address = receiveAddress.getText().toString().trim();
        String code = receiveCode.getText().toString().trim();
        presenter.getData(id,name,phone,address,code);
    }

    @Override
    public void successful(RegisterBean data) {
        if (data!=null){
            Toast.makeText(this,""+data.getMessage(),Toast.LENGTH_SHORT).show();
            Log.d(TAG, "failed:66666 "+data.getMessage());
            finish();
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
        Log.d(TAG, "failed:66666 "+e.getMessage());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
}
