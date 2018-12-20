package soexample.umeng.com.beiwei.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.RegisterBean;
import soexample.umeng.com.beiwei.regist.presenter.RegistPresenter;
import soexample.umeng.com.beiwei.regist.view.RegistView;

public class RegistActivity extends AppCompatActivity implements RegistView{

    @BindView(R.id.re_name)
    EditText reName;
    @BindView(R.id.re_password)
    EditText rePassword;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private RegistPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenter=new RegistPresenter();
        presenter.attach(this);
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                finish();
                break;
            case R.id.btn_register:
                presenter.getData(getUserName(), getPassWord());
                break;
        }
    }

    @Override
    public void successful(RegisterBean data) {
        Toast.makeText(this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
        if ("0000".equals(data.getStatus()) || "该手机号已注册，不能重复注册！".equals(data.getMessage())){
            finish();
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUserName() {
        return reName.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return rePassword.getText().toString();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
