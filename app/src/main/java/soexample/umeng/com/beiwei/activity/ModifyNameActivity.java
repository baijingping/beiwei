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
import soexample.umeng.com.beiwei.modifyname.ModifyNamePresenter;
import soexample.umeng.com.beiwei.modifyname.ModifyNamwView;

public class ModifyNameActivity extends AppCompatActivity implements ModifyNamwView{

    @BindView(R.id.new_name)
    EditText newName;
    @BindView(R.id.btn_name)
    Button btnName;
    private ModifyNamePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_name);
        ButterKnife.bind(this);
        presenter=new ModifyNamePresenter();
        presenter.attach(this);
    }

    @OnClick(R.id.btn_name)
    public void onViewClicked() {
        String name = newName.getText().toString();
        presenter.getData(name);
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
