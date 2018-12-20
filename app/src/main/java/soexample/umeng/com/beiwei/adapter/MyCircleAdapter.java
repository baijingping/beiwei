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

import java.sql.Date;
import java.util.List;

import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.MyCircleBean;

/**
 * Created by Shinelon on 2018/12/13.
 */

public class MyCircleAdapter extends RecyclerView.Adapter<MyCircleAdapter.ViewHolder>{
    public interface OnMyCircleClickListener {
        void onMyCircleClick(int id);
    }

    private OnMyCircleClickListener listener;

    public void setMyCircleClickListener(OnMyCircleClickListener listener) {
        this.listener = listener;
    }


    private Context context;
    private List<MyCircleBean.ResultBean> list;
    private boolean check;
    public MyCircleAdapter(Context context, List<MyCircleBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }
    public void getCheck(boolean isCheck){
        check=isCheck;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_mycircle, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MyCircleBean.ResultBean resultBean = list.get(position);
        holder.circleHeadPortrait.setImageURI(resultBean.getHeadPic());
        holder.circleName.setText(resultBean.getNickName());
        long time = resultBean.getCreateTime();
        Date date=new Date(time);
        holder.circleTime.setText(date+"");
        holder.circleTitle.setText(resultBean.getContent());
        holder.circleImg.setImageURI(resultBean.getImage());
        holder.circleNum.setText(resultBean.getGreatNum()+"");
        if (check){
            holder.checckDelete.setVisibility(View.VISIBLE);
        }else {
            holder.checckDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checckDelete;
        private SimpleDraweeView circleHeadPortrait;
        private TextView circleName;
        private TextView circleTime;
        private TextView circleTitle;
        private SimpleDraweeView circleImg;
        private TextView circleNum;

        public ViewHolder(View itemView) {
            super(itemView);
            checckDelete=itemView.findViewById(R.id.check_delete);
            circleHeadPortrait = itemView.findViewById(R.id.my_circle_head_portrait);
            circleName = itemView.findViewById(R.id.my_circle_name);
            circleTime = itemView.findViewById(R.id.my_circle_time);
            circleTitle = itemView.findViewById(R.id.my_circle_title);
            circleImg = itemView.findViewById(R.id.my_circle_img);
            circleNum = itemView.findViewById(R.id.my_circle_num);
        }
    }
}
