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
import soexample.umeng.com.beiwei.bean.CommodityBean;

/**
 * Created by Shinelon on 2018/12/7.
 */

public class CommodityAdapter extends RecyclerView.Adapter<CommodityAdapter.ViewHolder> {
    public interface OnCommodityClickListener {
        void onCommodityClick(int commodityId);
    }

    private OnCommodityClickListener commodityClickListener;

    public void setOnProductClickListener(OnCommodityClickListener listener) {
        this.commodityClickListener = listener;
    }
    private Context context;
    private List<CommodityBean.ResultBean> list;

    public CommodityAdapter(Context context, List<CommodityBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_commodity, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CommodityBean.ResultBean resultBean = list.get(position);
        holder.commImg.setImageURI(resultBean.getMasterPic());
        holder.commName.setText(resultBean.getCommodityName());
        holder.commPrice.setText(resultBean.getPrice()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int commodityId = resultBean.getCommodityId();
                if (commodityClickListener!=null){
                    commodityClickListener.onCommodityClick(commodityId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView commImg;
        private TextView commName;
        private TextView commPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            commImg=itemView.findViewById(R.id.comm_img);
            commName=itemView.findViewById(R.id.comm_name);
            commPrice=itemView.findViewById(R.id.comm_price);
        }
    }
}
