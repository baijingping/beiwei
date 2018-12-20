package soexample.umeng.com.beiwei.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.beiwei.MainActivity;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.LoginBean;
import soexample.umeng.com.beiwei.login.presenter.LoginPresenter;
import soexample.umeng.com.beiwei.login.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.userPaw)
    EditText userPaw;
    @BindView(R.id.btn_keep)
    CheckBox btnKeep;
    @BindView(R.id.btn_regist)
    TextView btnRegist;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private LoginPresenter presenter;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        ButterKnife.bind(this);
        sp = getSharedPreferences("user", MODE_PRIVATE);
        presenter=new LoginPresenter();
        presenter.attach(this);
    }

    @Override
    public void successful(LoginBean data) {
        Toast.makeText(this, ""+data.getMessage(), Toast.LENGTH_SHORT).show();
        if ("0000".equals(data.getStatus())||"登录成功".equals(data.getMessage())){
            sp.edit().putString("sid",data.getResult().getSessionId())
                    .putInt("uid",data.getResult().getUserId())
                    .putString("name",data.getResult().getNickName())
                    .putString("pwd",getUserPassWord())
                    .putString("timg",data.getResult().getHeadPic())
                    .putBoolean("isFirst",false)
                    .commit();
            finish();
        }
    }

    @Override
    public String getUserName() {
        return userName.getText().toString().trim();
    }

    @Override
    public String getUserPassWord() {
        return userPaw.getText().toString();
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn_regist, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_regist:
                Intent intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                String userName = getUserName();
                String userPassWord = getUserPassWord();
                presenter.getData(userName,userPassWord);
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
