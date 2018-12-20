package soexample.umeng.com.beiwei.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Shinelon on 2018/12/16.
 */

public class MyWalletBean {

    private ResultBean result;
    private String message;
    private String status;

    public static MyWalletBean objectFromData(String str) {

        return new Gson().fromJson(str, MyWalletBean.class);
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
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

    public static class ResultBean {
        /**
         * balance : 99999951
         * detailList : [{"amount":48,"consumerTime":1544839868000,"orderId":"2018121420330633251","userId":51}]
         */

        private int balance;
        private List<DetailListBean> detailList;

        public static ResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ResultBean.class);
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            private int amount;
            private long consumerTime;
            private String orderId;
            private int userId;

            public static DetailListBean objectFromData(String str) {

                return new Gson().fromJson(str, DetailListBean.class);
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public long getConsumerTime() {
                return consumerTime;
            }

            public void setConsumerTime(long consumerTime) {
                this.consumerTime = consumerTime;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}
