package soexample.umeng.com.beiwei.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.beiwei.R;

public class MyActivity extends AppCompatActivity {

    @BindView(R.id.log_out)
    Button logOut;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.bind(this);
        sp = getSharedPreferences("user", MODE_PRIVATE);
    }

    @OnClick(R.id.log_out)
    public void onViewClicked() {
        sp.edit().clear().commit();
        finish();
    }
}
