package soexample.umeng.com.beiwei;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.beiwei.fragment.CircleFragMent;
import soexample.umeng.com.beiwei.fragment.OrderFragMent;
import soexample.umeng.com.beiwei.fragment.HomeFragMent;
import soexample.umeng.com.beiwei.fragment.MyFragMent;
import soexample.umeng.com.beiwei.fragment.ShoppingCartFragMent;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_home)
    ImageView btnHome;
    @BindView(R.id.btn_classification)
    ImageView btnClassification;
    @BindView(R.id.btn_shop)
    ImageView btnShop;
    @BindView(R.id.btn_found)
    ImageView btnFound;
    @BindView(R.id.btn_my)
    ImageView btnMy;
    @BindView(R.id.pager)
    FrameLayout pager;
    private FragmentManager supportFragmentManager;
    private HomeFragMent homeFragMent;
    private CircleFragMent classificationFragMent;
    private OrderFragMent foundFragMent;
    private ShoppingCartFragMent shoppingCartFragMent;
    private MyFragMent myFragMent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        homeFragMent = new HomeFragMent();
        classificationFragMent = new CircleFragMent();
        foundFragMent = new OrderFragMent();
        shoppingCartFragMent = new ShoppingCartFragMent();
        myFragMent = new MyFragMent();
        supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction().replace(R.id.pager,homeFragMent).commit();
    }


    @OnClick({R.id.btn_home, R.id.btn_classification, R.id.btn_shop, R.id.btn_found, R.id.btn_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_home:
                supportFragmentManager.beginTransaction().replace(R.id.pager,homeFragMent).commit();
                changeBackGround(0);
                break;
            case R.id.btn_classification:
                supportFragmentManager.beginTransaction().replace(R.id.pager,classificationFragMent).commit();
                changeBackGround(1);
                break;
            case R.id.btn_shop:
                supportFragmentManager.beginTransaction().replace(R.id.pager,foundFragMent).commit();
                changeBackGround(2);
                break;
            case R.id.btn_found:
                supportFragmentManager.beginTransaction().replace(R.id.pager,shoppingCartFragMent).commit();
                changeBackGround(3);
                break;
            case R.id.btn_my:
                supportFragmentManager.beginTransaction().replace(R.id.pager,myFragMent).commit();
                changeBackGround(4);
                break;
        }
    }
    public void changeBackGround(int dex) {
        switch (dex) {
            case 0:
                btnHome.setImageResource(R.drawable.ac1);
                btnClassification.setImageResource(R.drawable.abw);
                btnShop.setImageResource(R.drawable.aby);
                btnFound.setImageResource(R.drawable.abu);
                btnMy.setImageResource(R.drawable.ac2);
                break;
            case 1:
                btnHome.setImageResource(R.drawable.ac0);
                btnClassification.setImageResource(R.drawable.abx);
                btnShop.setImageResource(R.drawable.aby);
                btnFound.setImageResource(R.drawable.abu);
                btnMy.setImageResource(R.drawable.ac2);
                break;
            case 2:
                btnHome.setImageResource(R.drawable.ac0);
                btnClassification.setImageResource(R.drawable.abw);
                btnShop.setImageResource(R.drawable.abz);
                btnFound.setImageResource(R.drawable.abu);
                btnMy.setImageResource(R.drawable.ac2);
                break;
            case 3:
                btnHome.setImageResource(R.drawable.ac0);
                btnClassification.setImageResource(R.drawable.abw);
                btnShop.setImageResource(R.drawable.aby);
                btnFound.setImageResource(R.drawable.abv);
                btnMy.setImageResource(R.drawable.ac2);
                break;
            case 4:
                btnHome.setImageResource(R.drawable.ac0);
                btnClassification.setImageResource(R.drawable.abw);
                btnShop.setImageResource(R.drawable.aby);
                btnFound.setImageResource(R.drawable.abu);
                btnMy.setImageResource(R.drawable.ac3);
                break;
        }
    }
}
