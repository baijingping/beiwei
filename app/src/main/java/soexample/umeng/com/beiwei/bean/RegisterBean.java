package soexample.umeng.com.beiwei.bean;

import com.google.gson.Gson;

/**
 * Created by Shinelon on 2018/11/29.
 */

public class RegisterBean {

    /**
     * message : 该手机号已注册，不能重复注册！
     * status : 1001
     */

    private String message;
    private String status;

    public static RegisterBean objectFromData(String str) {

        return new Gson().fromJson(str, RegisterBean.class);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
