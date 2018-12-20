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
import soexample.umeng.com.beiwei.bean.FootBean;

/**
 * Created by Shinelon on 2018/12/11.
 */

public class FootAdapter extends RecyclerView.Adapter<FootAdapter.ViewHolder> {
    private Context context;
    private List<FootBean.ResultBean> list;

    public FootAdapter(Context context, List<FootBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_foot, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FootBean.ResultBean resultBean = list.get(position);
        holder.img.setImageURI(resultBean.getMasterPic());
        holder.name.setText(resultBean.getCommodityName());
        holder.price.setText(resultBean.getPrice()+"");
        holder.browseNum.setText("已浏览"+resultBean.getBrowseNum()+"次");
        holder.time.setText(resultBean.getBrowseTime()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView img;
        private TextView name;
        private TextView price;
        private TextView browseNum;
        private TextView time;
        public ViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.foot_img);
            name=itemView.findViewById(R.id.foot_name);
            price=itemView.findViewById(R.id.foot_price);
            browseNum=itemView.findViewById(R.id.foot_browseNum);
            time=itemView.findViewById(R.id.foot_time);
        }
    }
}
