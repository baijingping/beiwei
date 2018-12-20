package soexample.umeng.com.beiwei.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Shinelon on 2018/12/18.
 */

public class CommodityByCategoryBean {


    private String message;
    private String status;
    private List<ResultBean> result;

    public static CommodityByCategoryBean objectFromData(String str) {

        return new Gson().fromJson(str, CommodityByCategoryBean.class);
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
        /**
         * commodityId : 52
         * commodityName : 唐狮女鞋夏季女款凉鞋女士凉鞋女鞋平底女士凉拖夹脚女鞋凉鞋休闲凉鞋
         * masterPic : http://172.17.8.100/images/small/commodity/nx/lx/7/1.jpg
         * price : 109
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private float price;
        private int saleNum;

        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
