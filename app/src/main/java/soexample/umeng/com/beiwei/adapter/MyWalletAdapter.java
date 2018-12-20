package soexample.umeng.com.beiwei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Date;
import java.util.List;

import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.MyWalletBean;

/**
 * Created by Shinelon on 2018/12/16.
 */

public class MyWalletAdapter extends RecyclerView.Adapter<MyWalletAdapter.ViewHolder> {
    private Context context;
    private List<MyWalletBean.ResultBean.DetailListBean> list;

    public MyWalletAdapter(Context context, List<MyWalletBean.ResultBean.DetailListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.mywallet_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyWalletBean.ResultBean.DetailListBean detailListBean = list.get(position);
        holder.consumptionMoney.setText(detailListBean.getAmount()+".00");
        long consumerTime = detailListBean.getConsumerTime();
        Date date=new Date(consumerTime);
        holder.consumptionTime.setText(date+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView consumptionMoney;
        private TextView consumptionTime;
        public ViewHolder(View itemView) {
            super(itemView);
            consumptionMoney=itemView.findViewById(R.id.consumption_money);
            consumptionTime=itemView.findViewById(R.id.consumption_time);
        }
    }
}
