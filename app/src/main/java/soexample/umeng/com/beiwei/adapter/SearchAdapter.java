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
import soexample.umeng.com.beiwei.bean.SearchBean;

/**
 * Created by Shinelon on 2018/12/10.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    private Context context;
    private List<SearchBean.ResultBean> list;

    public SearchAdapter(Context context, List<SearchBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_search, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchBean.ResultBean resultBean = list.get(position);
        holder.searchImg.setImageURI(resultBean.getMasterPic());
        holder.searchName.setText(resultBean.getCommodityName());
        holder.searchPrice.setText(resultBean.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView searchImg;
        private TextView searchName;
        private TextView searchPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            searchImg=itemView.findViewById(R.id.search_img);
            searchName=itemView.findViewById(R.id.search_name);
            searchPrice=itemView.findViewById(R.id.search_price);
        }
    }
}
