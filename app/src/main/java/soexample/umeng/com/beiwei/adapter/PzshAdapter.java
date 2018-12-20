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

public class PzshAdapter extends RecyclerView.Adapter<PzshAdapter.ViewHolder>{
    private Context context;
    private List<HomeBean.ResultBean.PzshBean.CommodityListBeanX> list;

    public PzshAdapter(Context context, List<HomeBean.ResultBean.PzshBean.CommodityListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.pzsh_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeBean.ResultBean.PzshBean.CommodityListBeanX commodityListBeanX = list.get(position);
        holder.pzshImg.setImageURI(commodityListBeanX.getMasterPic());
        holder.pzshName.setText(commodityListBeanX.getCommodityName());
        holder.pzshPrice.setText("￥"+commodityListBeanX.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView pzshImg;
        private TextView pzshName;
        private TextView pzshPrice;
      public ViewHolder(View itemView) {
          super(itemView);
          pzshImg=itemView.findViewById(R.id.pzsh_img);
          pzshName=itemView.findViewById(R.id.pzsh_name);
          pzshPrice=itemView.findViewById(R.id.pzsh_price);
      }
  }
}
