package soexample.umeng.com.beiwei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.OrderBean;

/**
 * Created by Shinelon on 2018/12/15.
 */

public class EvaluationAdapter extends RecyclerView.Adapter<EvaluationAdapter.ViewHolder> {
    private Context context;
    private List<OrderBean.OrderListBean> list;

    public EvaluationAdapter(Context context, List<OrderBean.OrderListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.evaluation_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderBean.OrderListBean orderListBean = list.get(position);
        holder.eCode.setText("WD"+orderListBean.getOrderId());
        holder.eTime.setText("2018-9-30  11:35");
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        holder.eList.setLayoutManager(layoutManager);
        ELisrAdapter adapter=new ELisrAdapter(context,orderListBean.getDetailList());
        holder.eList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView eCode;
        private TextView eTime;
        private ImageView eImg;
        private RecyclerView eList;

        public ViewHolder(View itemView) {
            super(itemView);
            eCode=itemView.findViewById(R.id.e_code);
            eTime=itemView.findViewById(R.id.e_time);
            eList=itemView.findViewById(R.id.e_list);
            eImg=itemView.findViewById(R.id.e_img);
        }
    }
}
