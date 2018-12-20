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
import soexample.umeng.com.beiwei.bean.HomeBean;

/**
 * Created by Shinelon on 2018/12/6.
 */

public class RxxpAdapter extends RecyclerView.Adapter<RxxpAdapter.ViewHolder> {
    private Context context;
    private List<HomeBean.ResultBean.RxxpBean.CommodityListBean> list;

    public RxxpAdapter(Context context, List<HomeBean.ResultBean.RxxpBean.CommodityListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.rxxp_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeBean.ResultBean.RxxpBean.CommodityListBean commodityListBean = list.get(position);
        holder.rxxpImg.setImageURI(commodityListBean.getMasterPic());
        holder.rxxpName.setText(commodityListBean.getCommodityName());
        holder.rxxpPrice.setText("￥"+commodityListBean.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView rxxpImg;
        private TextView rxxpName;
        private TextView rxxpPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            rxxpImg=itemView.findViewById(R.id.rxxp_img);
            rxxpName=itemView.findViewById(R.id.rxxp_name);
            rxxpPrice=itemView.findViewById(R.id.rxxp_price);
        }
    }
}
