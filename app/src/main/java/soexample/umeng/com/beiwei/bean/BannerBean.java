package soexample.umeng.com.beiwei.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Shinelon on 2018/12/5.
 */

public class BannerBean {
    private String message;
    private String status;
    private List<ResultBean> result;
    public static BannerBean objectFromData(String str) {

        return new Gson().fromJson(str, BannerBean.class);
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
    public List<ResultBean> getResult() {
        return result;
    }
    public void setResult(List<ResultBean> result) {
        this.result = result;
    }
    public static class ResultBean {
        private String imageUrl;
        private String jumpUrl;
        private int rank;
        private String title;
        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }
        public String getImageUrl() {
            return imageUrl;
        }
        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
        public String getJumpUrl() {
            return jumpUrl;
        }
        public void setJumpUrl(String jumpUrl) {
            this.jumpUrl = jumpUrl;
        }
        public int getRank() {
            return rank;
        }
        public void setRank(int rank) {
            this.rank = rank;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
    }
}
