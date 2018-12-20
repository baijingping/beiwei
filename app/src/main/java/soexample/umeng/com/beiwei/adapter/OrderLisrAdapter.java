package soexample.umeng.com.beiwei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.OrderBean;

/**
 * Created by Shinelon on 2018/12/15.
 */

public class OrderLisrAdapter extends RecyclerView.Adapter<OrderLisrAdapter.ViewHolder> {
    private Context context;
    private List<OrderBean.OrderListBean.DetailListBean> list;

    public OrderLisrAdapter(Context context, List<OrderBean.OrderListBean.DetailListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.orderlist_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderBean.OrderListBean.DetailListBean detailListBean = list.get(position);
        String commodityPic = detailListBean.getCommodityPic();
        String[] split = commodityPic.split("\\,");
        holder.orderImg.setImageURI(split[0]);
        holder.orderName.setText(detailListBean.getCommodityName());
        holder.orderPrice.setText("￥:"+detailListBean.getCommodityPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView orderImg;
        private TextView orderName;
        private TextView orderPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            orderImg=itemView.findViewById(R.id.order_img);
            orderName=itemView.findViewById(R.id.order_name);
            orderPrice=itemView.findViewById(R.id.order_price);
        }
    }
}
