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

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder>{
    public interface OnTopClickListener {
        void onTopClick(String id);
    }

    private OnTopClickListener listener;

    public void setOnTopClickListener(OnTopClickListener listener) {
        this.listener = listener;
    }

    private Context context;
    private List<PopBean.ResultBean> list;

    public TopAdapter(Context context, List<PopBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_pop, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
         holder.topName.setText(list.get(position).getName());
         holder.topName.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String id = list.get(position).getId();
                 if (listener!=null){
                    listener.onTopClick(id);
                 }
             }
         });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView topName;
        public ViewHolder(View itemView) {
            super(itemView);
            topName=itemView.findViewById(R.id.top_name);
        }
    }
}
