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

public class ELisrAdapter extends RecyclerView.Adapter<ELisrAdapter.ViewHolder> {
    private Context context;
    private List<OrderBean.OrderListBean.DetailListBean> list;

    public ELisrAdapter(Context context, List<OrderBean.OrderListBean.DetailListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.elist_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderBean.OrderListBean.DetailListBean detailListBean = list.get(position);
        String commodityPic = detailListBean.getCommodityPic();
        String[] split = commodityPic.split("\\,");
        holder.elistImg.setImageURI(split[0]);
        holder.elistName.setText(detailListBean.getCommodityName());
        holder.elistPrice.setText("￥:"+detailListBean.getCommodityPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView elistImg;
        private TextView elistName;
        private TextView elistPrice;
        private TextView goEvaluation;
        public ViewHolder(View itemView) {
            super(itemView);
            elistImg=itemView.findViewById(R.id.elist_img);
            elistName=itemView.findViewById(R.id.elist_name);
            elistPrice=itemView.findViewById(R.id.elist_price);
            goEvaluation=itemView.findViewById(R.id.go_evaluation);
        }
    }
}
