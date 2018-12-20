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

public class MissAdapter extends RecyclerView.Adapter<MissAdapter.ViewHolder> {
    private Context context;
    private List<HomeBean.ResultBean.MlssBean.CommodityListBeanXX> list;

    public MissAdapter(Context context, List<HomeBean.ResultBean.MlssBean.CommodityListBeanXX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.miss_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeBean.ResultBean.MlssBean.CommodityListBeanXX commodityListBeanXX = list.get(position);
        holder.missImg.setImageURI(commodityListBeanXX.getMasterPic());
        holder.missName.setText(commodityListBeanXX.getCommodityName());
        holder.missPrice.setText("￥"+commodityListBeanXX.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView missImg;
        private TextView missName;
        private TextView missPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            missImg=itemView.findViewById(R.id.miss_img);
            missName=itemView.findViewById(R.id.miss_name);
            missPrice=itemView.findViewById(R.id.miss_price);
        }
    }
}
