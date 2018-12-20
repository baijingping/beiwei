package soexample.umeng.com.beiwei.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.UserBean;
import soexample.umeng.com.beiwei.user.UserPresenter;
import soexample.umeng.com.beiwei.user.UserView;
import soexample.umeng.com.beiwei.utils.OkHttpUtils;
import soexample.umeng.com.beiwei.utils.RetiofitHttp;
import soexample.umeng.com.beiwei.utils.UtilsUrl;

public class DataActivity extends AppCompatActivity implements UserView{
    private static final String TAG = "DataActivity";
    @BindView(R.id.deta_img)
    SimpleDraweeView detaImg;
    @BindView(R.id.data_name)
    TextView dataName;
    @BindView(R.id.data_paw)
    TextView dataPaw;
    private UserPresenter presenter;
    private ImageView img;//找到图片img并提成成员变量
    private static String path = "/sdcard/myHead/";// sd路径
    private Bitmap head;
    private String fileName;
    private File file1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        ButterKnife.bind(this);
        presenter=new UserPresenter();
        presenter.attach(this);
        presenter.getData();
        detaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //alertDialog弹框
                AlertDialog.Builder builder = new AlertDialog.Builder(DataActivity.this);
                final AlertDialog dialog = builder.create();
                View view1 = View.inflate(DataActivity.this, R.layout.popopwindow, null);
                TextView tv_select_gallery = (TextView) view1.findViewById(R.id.pop_album);
                TextView tv_select_camera = (TextView) view1.findViewById(R.id.pop_pictures);
                TextView tv_select_cancel = (TextView) view1.findViewById(R.id.pop_cancel);
                tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intent1, 1);
                        dialog.dismiss();
                    }
                });
                tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
                    @Override
                    public void onClick(View v) {
                        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                "head.png")));
                        startActivityForResult(intent2, 2);//采用ForResult打开
                        dialog.dismiss();
                    }
                });
                tv_select_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                dialog.setView(view1);
                dialog.show();
            }
        });
    }

    @OnClick({R.id.data_name, R.id.data_paw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.data_name:
                Intent intent=new Intent(this,ModifyNameActivity.class);
                startActivity(intent);
                break;
            case R.id.data_paw:
                Intent intent1=new Intent(this,ModifyPassWordActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void successful(UserBean data) {
        if (data!=null){
            UserBean.ResultBean result = data.getResult();
            detaImg.setImageURI(result.getHeadPic());
            dataName.setText(result.getNickName());
            dataPaw.setText(result.getPassword());
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detach();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.png");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras == null) {
                        return;
                    }
                    head = extras.getParcelable("data");
                    if (head != null) {
//                        detaImg.setImageBitmap(head);
                        //图片名字
                        fileName = path + "/head.png";
                        setPicToView(head);//保存在SD卡中
                        File file1 = new File(fileName);
                        uploadPic(file1);
                    }
                }
                break;
            default:
                break;

        }
    }
    //上传头像
    private void uploadPic(File file1) {
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        RequestBody file = RequestBody.create(MediaType.parse("multipart/form-data"),file1);
        MultipartBody.Part part=MultipartBody.Part.createFormData("file","head.png",file);
        HashMap<String,String> map = new HashMap<>();
        map.put("userId",user.getInt("uid",0)+"");
        map.put("sessionId",user.getString("sid",""));
        new RetiofitHttp().part(UtilsUrl.upimgurl,map,part).result(new RetiofitHttp.HttpRxListener() {
            @Override
            public void success(String data) {
                Log.d(TAG, "success66666666666: "+data);
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.png";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
