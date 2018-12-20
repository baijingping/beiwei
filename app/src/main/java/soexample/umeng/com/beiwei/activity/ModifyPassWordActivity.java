package soexample.umeng.com.beiwei.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import soexample.umeng.com.beiwei.modifypassword.ModifyPasswordPresenter;
import soexample.umeng.com.beiwei.modifypassword.ModifyPasswordView;

public class ModifyPassWordActivity extends AppCompatActivity implements ModifyPasswordView{

    @BindView(R.id.paw)
    EditText paw;
    @BindView(R.id.new_paw)
    EditText newPaw;
    @BindView(R.id.btn_name)
    Button btnName;
    private ModifyPasswordPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pass_word);
        ButterKnife.bind(this);
        presenter=new ModifyPasswordPresenter();
        presenter.attach(this);
    }

    @OnClick(R.id.btn_name)
    public void onViewClicked() {
        String yPas = paw.getText().toString().trim();
        String xPas = newPaw.getText().toString().trim();
        presenter.getData(yPas,xPas);
    }

    @Override
    public void successful(RegisterBean data) {
        if (data!=null){
            Toast.makeText(this,"密码"+data.getMessage()+"请重新登录",Toast.LENGTH_SHORT).show();
            SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
            finish();
            user.edit().clear().commit();
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);

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
