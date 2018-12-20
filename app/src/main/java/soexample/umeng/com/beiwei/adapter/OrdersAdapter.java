package soexample.umeng.com.beiwei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Date;
import java.util.List;

import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.OrderBean;

/**
 * Created by Shinelon on 2018/12/15.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    private static final String TAG = "OrdersAdapter";
    public interface OnOrdersClickListener {
        void onOrdersClick(String orderid);
    }

    private OnOrdersClickListener listener;

    public void setOnOrdersClickListener(OnOrdersClickListener listener) {
        this.listener = listener;
    }
    public interface OnOrdersClickListener2 {
        void onOrdersClick2(String orderid);
    }

    private OnOrdersClickListener2 listener2;

    public void setOnOrdersClickListener2(OnOrdersClickListener2 listener2) {
        this.listener2 = listener2;
    }
    private Context context;
    private List<OrderBean.OrderListBean> list;

    public OrdersAdapter(Context context, List<OrderBean.OrderListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.order_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrderBean.OrderListBean orderListBean = list.get(position);
        holder.orderCode.setText(orderListBean.getOrderId());
        holder.orderTime.setText("2018-9-30");
        float sum=0;
        for (OrderBean.OrderListBean.DetailListBean detailListBean : orderListBean.getDetailList()) {
            sum+=detailListBean.getCommodityCount()*detailListBean.getCommodityPrice();
        }
        holder.orderSum.setText("共"+orderListBean.getDetailList().size()+"件商品,需付款"+sum+"元");
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        holder.orderList.setLayoutManager(layoutManager);
        OrderLisrAdapter adapter=new OrderLisrAdapter(context,orderListBean.getDetailList());
        holder.orderList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        holder.oederPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderId = orderListBean.getOrderId();
                Log.d(TAG, "onClick: "+orderId);
                if (listener!=null){
                    listener.onOrdersClick(orderId);
                }
            }
        });
        holder.orderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderId = orderListBean.getOrderId();
                Log.d(TAG, "onClick: "+orderId);
                if (listener2!=null){
                    listener2.onOrdersClick2(orderId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView orderCode;
        private TextView orderTime;
        private RecyclerView orderList;
        private TextView orderSum;
        private TextView orderCancel;
        private TextView oederPay;
        public ViewHolder(View itemView) {
            super(itemView);
            orderCode=itemView.findViewById(R.id.order_code);
            orderTime=itemView.findViewById(R.id.order_time);
            orderList=itemView.findViewById(R.id.order_list);
            orderSum=itemView.findViewById(R.id.order_sum);
            orderCancel=itemView.findViewById(R.id.order_cancel);
            oederPay=itemView.findViewById(R.id.order_pay);
        }
    }
}
