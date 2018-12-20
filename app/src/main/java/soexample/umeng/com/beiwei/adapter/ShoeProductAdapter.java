package soexample.umeng.com.beiwei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.ShowProductBean;

/**
 * Created by Shinelon on 2018/12/7.
 */

public class ShoeProductAdapter extends RecyclerView.Adapter<ShoeProductAdapter.ViewHolder> {
    private Context context;
    private ShowProductBean.ResultBean list;

    public ShoeProductAdapter(Context context, ShowProductBean.ResultBean list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_showproduct, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String picture = list.getPicture();
        String[] split = picture.split("\\,");
        holder.img.setImageURI(split[0]);
        holder.categoryName.setText(list.getCategoryName());
        holder.commentNum.setText(list.getCommentNum()+"");
        holder.commodityName.setText(list.getCommodityName());
        holder.describe.setText(list.getDescribe());
        holder.price.setText(list.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView img;
        private TextView categoryName;
        private TextView commentNum;
        private TextView commodityName;
        private TextView describe;
        private TextView price;
        public ViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.show_img);
            categoryName=itemView.findViewById(R.id.categoryName);
            commentNum=itemView.findViewById(R.id.commentNum);
            commodityName=itemView.findViewById(R.id.commodityName);
            describe=itemView.findViewById(R.id.describe);
            price=itemView.findViewById(R.id.price);
        }
    }
}
