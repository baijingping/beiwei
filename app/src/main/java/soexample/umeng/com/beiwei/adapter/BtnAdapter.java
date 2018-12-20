package soexample.umeng.com.beiwei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.PopBean;

/**
 * Created by Shinelon on 2018/12/18.
 */

public class BtnAdapter extends RecyclerView.Adapter<BtnAdapter.ViewHolder>{
    public interface OnBtnClickListener {
        void onBtnClick(String id);
    }

    private OnBtnClickListener listener;

    public void setOnBtnClickListener(OnBtnClickListener listener) {
        this.listener = listener;
    }

    private Context context;
    private List<PopBean.ResultBean> list;

    public BtnAdapter(Context context, List<PopBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_btn, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
         holder.btnName.setText(list.get(position).getName());
         holder.btnName.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String id = list.get(position).getId();
                 if (listener!=null){
                     listener.onBtnClick(id);
                 }
             }
         });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView btnName;
        public ViewHolder(View itemView) {
            super(itemView);
            btnName=itemView.findViewById(R.id.btn_namea);
        }
    }
}
