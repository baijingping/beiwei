package soexample.umeng.com.beiwei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.AddressBean;

/**
 * Created by Shinelon on 2018/12/12.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    public interface OnAddressClickListener {
        void onAddressClick(int id,String name,String phone,String address,String code);
    }

    private OnAddressClickListener AddressClickListener;

    public void setOnAddressClickListener(OnAddressClickListener listener) {
        this.AddressClickListener = listener;
    }
    private Context context;
    private List<AddressBean.ResultBean> list;

    public AddressAdapter(Context context, List<AddressBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_address, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final AddressBean.ResultBean resultBean = list.get(position);
        holder.addressName.setText(resultBean.getRealName());
        holder.addressPhone.setText(resultBean.getPhone());
        holder.address.setText(resultBean.getAddress());
        holder.addressModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = resultBean.getId();
                String name = resultBean.getRealName();
                String phone = resultBean.getPhone();
                String address = resultBean.getAddress();
                String code = resultBean.getZipCode();
                if (AddressClickListener!=null){
                    AddressClickListener.onAddressClick(id,name,phone,address,code);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView addressName;
        private TextView addressPhone;
        private TextView address;
        private CheckBox addressCheck;
        private Button addressModify;
        private Button addressDelete;
        public ViewHolder(View itemView) {
            super(itemView);
            addressName=itemView.findViewById(R.id.address_name);
            addressPhone=itemView.findViewById(R.id.address_phonr);
            address=itemView.findViewById(R.id.address);
            addressCheck=itemView.findViewById(R.id.address_check);
            addressModify=itemView.findViewById(R.id.address_modify);
            addressDelete=itemView.findViewById(R.id.address_delect);
        }
    }
}
