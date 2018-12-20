package soexample.umeng.com.beiwei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.ShowProductBean;
import soexample.umeng.com.beiwei.showproduct.presenter.ShowPrpductPresenter;
import soexample.umeng.com.beiwei.showproduct.view.ShowProductView;

public class WebViewActivity extends AppCompatActivity implements ShowProductView{

    @BindView(R.id.web_shop)
    WebView webShop;
    private ShowPrpductPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        webShop.loadUrl("http://www.baidu.com");
        webShop.setWebViewClient(new WebViewClient());
        WebSettings settings = webShop.getSettings();
        settings.setJavaScriptEnabled(true);
        presenter=new ShowPrpductPresenter();
        presenter.attach(this);
        Intent intent = getIntent();
        String s = intent.getStringExtra("cid");
        int cid = Integer.valueOf(s);
        presenter.getData(cid);
    }

    @Override
    public void successful(ShowProductBean data) {
        String details = data.getResult().getDetails();
        /*webShop.setWebViewClient(new WebViewClient());
        webShop.getSettings().setJavaScriptEnabled(true);
        webShop.getSettings().setBlockNetworkImage(false);*/
        webShop.loadDataWithBaseURL(null,"<!DOCTYPE html><html><body>" + details + "</body></html>", "text/html", "utf-8", null);
    }

    @Override
    public void failed(Exception e) {

    }
}
