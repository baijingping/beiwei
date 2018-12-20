package soexample.umeng.com.beiwei.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Shinelon on 2018/12/11.
 */

public class FootBean {

    /**
     * result : [{"browseNum":1,"browseTime":1544236922000,"commodityId":23,"commodityName":"小白鞋 女款 时尚百搭休闲板鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/bx/6/1.jpg","price":139,"userId":51},{"browseNum":1,"browseTime":1544236846000,"commodityId":29,"commodityName":"秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲","masterPic":"http://172.17.8.100/images/small/commodity/nx/ddx/5/1.jpg","price":278,"userId":51},{"browseNum":3,"browseTime":1544236703000,"commodityId":19,"commodityName":"环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/bx/2/1.jpg","price":78,"userId":51},{"browseNum":1,"browseTime":1544236578000,"commodityId":17,"commodityName":"化妆镜","masterPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/1.jpg","price":31,"userId":51}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public static FootBean objectFromData(String str) {

        return new Gson().fromJson(str, FootBean.class);
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
         * browseNum : 1
         * browseTime : 1544236922000
         * commodityId : 23
         * commodityName : 小白鞋 女款 时尚百搭休闲板鞋
         * masterPic : http://172.17.8.100/images/small/commodity/nx/bx/6/1.jpg
         * price : 139
         * userId : 51
         */

        private int browseNum;
        private long browseTime;
        private int commodityId;
        private String commodityName;
        private String masterPic;
        private float price;
        private int userId;

        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }

        public int getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(int browseNum) {
            this.browseNum = browseNum;
        }

        public long getBrowseTime() {
            return browseTime;
        }

        public void setBrowseTime(long browseTime) {
            this.browseTime = browseTime;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
