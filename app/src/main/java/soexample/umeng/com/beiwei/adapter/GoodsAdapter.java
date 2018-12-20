package soexample.umeng.com.beiwei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.OrderBean;

/**
 * Created by Shinelon on 2018/12/15.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {
    public interface OnGoodsClickListener {
        void onGoodsClick(String orderid);
    }

    private OnGoodsClickListener listener;

    public void setOnGoodsClickListener(OnGoodsClickListener listener) {
        this.listener = listener;
    }
    private Context context;
    private List<OrderBean.OrderListBean> list;

    public GoodsAdapter(Context context, List<OrderBean.OrderListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.goods_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrderBean.OrderListBean orderListBean = list.get(position);
        holder.goodsCode.setText("WD"+orderListBean.getOrderId());
        holder.goodsTime.setText("2018-9-30");
        holder.courierName.setText(orderListBean.getExpressCompName());
        holder.code.setText(orderListBean.getExpressSn());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        holder.goodsList.setLayoutManager(layoutManager);
        OrderLisrAdapter adapter=new OrderLisrAdapter(context,orderListBean.getDetailList());
        holder.goodsList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        holder.goodsPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderId = orderListBean.getOrderId();
                if (listener!=null){
                    listener.onGoodsClick(orderId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView goodsCode;
        private TextView goodsTime;
        private RecyclerView goodsList;
        private TextView courierName;
        private TextView code;
        private TextView goodsPay;
        public ViewHolder(View itemView) {
            super(itemView);
            goodsCode=itemView.findViewById(R.id.goods_code);
            goodsTime=itemView.findViewById(R.id.goods_time);
            goodsList=itemView.findViewById(R.id.goods_list);
            courierName=itemView.findViewById(R.id.courier_name);
            code=itemView.findViewById(R.id.code);
            goodsPay=itemView.findViewById(R.id.goods_pay);
        }
    }
}
