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
import soexample.umeng.com.beiwei.bean.CommodityByCategoryBean;

/**
 * Created by Shinelon on 2018/12/18.
 */

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ViewHolder> {
    private Context context;
    private List<CommodityByCategoryBean.ResultBean> list;

    public ProductCategoryAdapter(Context context, List<CommodityByCategoryBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_commoditybycategory, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommodityByCategoryBean.ResultBean resultBean = list.get(position);
        holder.commodityImg.setImageURI(resultBean.getMasterPic());
        holder.commodityTitle.setText(resultBean.getCommodityName());
        holder.commodityPrice.setText("￥:"+resultBean.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView commodityImg;
        private TextView commodityTitle;
        private TextView commodityPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            commodityImg=itemView.findViewById(R.id.commodity_img);
            commodityTitle=itemView.findViewById(R.id.commodity_title);
            commodityPrice=itemView.findViewById(R.id.commodity_price);
        }
    }
}
