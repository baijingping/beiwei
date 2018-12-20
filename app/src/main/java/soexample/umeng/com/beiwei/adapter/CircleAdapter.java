package soexample.umeng.com.beiwei.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.sql.Date;
import java.util.List;

import soexample.umeng.com.beiwei.R;
import soexample.umeng.com.beiwei.bean.CircleBean;

/**
 * Created by Shinelon on 2018/12/11.
 */

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder>{
    public interface OnCircleClickListener {
        void onCircleClick(int id, boolean isChecked);
    }

    private OnCircleClickListener listener;

    public void setOnCircleClickListener(OnCircleClickListener listener) {
        this.listener = listener;
    }

    private static final String TAG = "CircleAdapter";
    private Context context;
    private List<CircleBean.ResultBean> list;
    int num;
    public CircleAdapter(Context context, List<CircleBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_circle, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final CircleBean.ResultBean resultBean = list.get(position);
        holder.circleHeadPortrait.setImageURI(resultBean.getHeadPic());
        holder.circleName.setText(resultBean.getNickName());
        long time = resultBean.getCreateTime();
        Date date=new Date(time);
        holder.circleTime.setText(date+"");
        holder.circleTitle.setText(resultBean.getContent());
        holder.circleImg.setImageURI(resultBean.getImage());
        holder.circleNum.setText(resultBean.getGreatNum()+"");
        holder.circlePrise.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(TAG, "onCheckedChanged: "+b);
                int id = resultBean.getId();
                int greatNum = resultBean.getGreatNum();
                num=greatNum;
                if (b){
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.common_btn_prise_s_hdpi);
                    holder.circlePrise.setBackground(drawable);
                    num++;
                    resultBean.setGreatNum(num);
                    holder.circleNum.setText(num+"");
                    if (listener!=null){
                        listener.onCircleClick(id,b);
                    }
                }else {
                    Drawable drawable = context.getResources().getDrawable(R.mipmap.common_prise_n_hdpi);
                    holder.circlePrise.setBackground(drawable);
                    num--;
                    resultBean.setGreatNum(num);
                    holder.circleNum.setText(num+"");
                    if (listener!=null){
                        listener.onCircleClick(id,b);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox circlePrise;
        private SimpleDraweeView circleHeadPortrait;
        private TextView circleName;
        private TextView circleTime;
        private TextView circleTitle;
        private SimpleDraweeView circleImg;
        private TextView circleNum;
        public ViewHolder(View itemView) {
            super(itemView);
            circlePrise=itemView.findViewById(R.id.circle_prise);
            circleHeadPortrait=itemView.findViewById(R.id.circle_head_portrait);
            circleName=itemView.findViewById(R.id.circle_name);
            circleTime=itemView.findViewById(R.id.circle_time);
            circleTitle=itemView.findViewById(R.id.circle_title);
            circleImg=itemView.findViewById(R.id.circle_img);
            circleNum=itemView.findViewById(R.id.circle_num);
        }
    }
}
