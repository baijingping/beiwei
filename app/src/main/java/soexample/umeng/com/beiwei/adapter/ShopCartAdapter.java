package soexample.umeng.com.beiwei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.ShopCartBean;
import soexample.umeng.com.beiwei.weight.AddDecreaseView;

/**
 * Created by Shinelon on 2018/12/13.
 */

public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartAdapter.ViewHolder> {
    public interface OnShopCartClickListener {
        void onShopCartClick(int position, boolean isChecked);
    }

    private OnShopCartClickListener listener;

    public void setOnShopCartClickListener(OnShopCartClickListener listener) {
        this.listener = listener;
    }

    public interface OnAddDecreaseListener{
        void onChang(int position,int num);
    }
    private OnAddDecreaseListener addListener;
    public void setOnAddDecreaseListener(OnAddDecreaseListener listener){
        this.addListener=listener;
    }
    private Context context;
    private List<ShopCartBean.ResultBean> list;

    public ShopCartAdapter(Context context, List<ShopCartBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_shopcart, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ShopCartBean.ResultBean resultBean = list.get(position);
        holder.productImg.setImageURI(resultBean.getPic());
        holder.productTitle.setText(resultBean.getCommodityName());
        holder.productPrice.setText("￥:"+resultBean.getPrice()+".00");

        holder.productAdd.setNum(resultBean.getCount());
        holder.productAdd.setOnAddClickListener(new AddDecreaseView.OnAddClickListener() {
            @Override
            public void add(int num) {
                resultBean.setCount(num);
                if (addListener!=null){
                    addListener.onChang(position,num);
                }
            }

            @Override
            public void decrease(int num) {
                resultBean.setCount(num);
                if (addListener!=null){
                    addListener.onChang(position,num);
                }
            }
        });

        holder.productCheck.setOnCheckedChangeListener(null);
        holder.productCheck.setChecked(resultBean.isCheck());
        holder.productCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                 resultBean.setCheck(b);
                 if (listener!=null){
                     listener.onShopCartClick(position,b);
                 }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private AddDecreaseView productAdd;
        private CheckBox productCheck;
        private SimpleDraweeView productImg;
        private TextView productTitle;
        private TextView productPrice;
        public ViewHolder(View itemView) {
            super(itemView);
            productCheck=itemView.findViewById(R.id.product_check);
            productImg=itemView.findViewById(R.id.product_img);
            productTitle=itemView.findViewById(R.id.product_name);
            productPrice=itemView.findViewById(R.id.product_price);
            productAdd=itemView.findViewById(R.id.product_add);
        }
    }
}
