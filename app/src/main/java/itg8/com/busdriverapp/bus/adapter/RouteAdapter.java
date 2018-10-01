package itg8.com.busdriverapp.bus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.home.busModel.Checkpoint;
import itg8.com.busdriverapp.home.busModel.Checkpoints;
import itg8.com.busdriverapp.home.busModel.User;
import itg8.com.busdriverapp.home.busModel.User_;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder> {
    private final Context context;
    private final OnRouteItemClickedListener listener;


    private List<User> list;

    public RouteAdapter(Context context, OnRouteItemClickedListener listener, List<User> list) {
        this.context = context;
        this.listener = listener;
        this.list = list;
    }

    @NonNull
    @Override
    public RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_route, parent, false);
        return new RouteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteViewHolder holder, int position) {


//        holder.mTxtCheckCount.setText(String .valueOf(list.get(position).getListCheckPoints().size()));
//        holder.mTxtChildCount.setText(String.valueOf(list.get(position).getListCheckPoints().get(position).getUsersChild().size()));
        holder.mLblStartRouteName.setText(list.get(position).getRouteName());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RouteViewHolder extends RecyclerView.ViewHolder {

        public Checkpoints checkModel;
        public List<User_> userModel;
        public List<Checkpoint> checkList;
        @BindView(R.id.lbl_routeName)
        TextView mLblRouteName;
        @BindView(R.id.lbl_start_routeName)
        TextView mLblStartRouteName;
        @BindView(R.id.rl)
        RelativeLayout mRl;
        @BindView(R.id.txt_childCount)
        TextView mTxtChildCount;
        @BindView(R.id.lbl_childCount)
        TextView mLblChildCount;
        @BindView(R.id.ll_child_count)
        LinearLayout mLlChildCount;
        @BindView(R.id.txt_checkCount)
        TextView mTxtCheckCount;
        @BindView(R.id.lbl_checkCount)
        TextView mLblCheckCount;
        @BindView(R.id.ll_check_point)
        LinearLayout mLlCheckPoint;

        public RouteViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRouteItemClicked(getAdapterPosition(),list.get(getAdapterPosition()).getListCheckPoints());
                }
            });
        }
    }

    public interface OnRouteItemClickedListener {
        void onRouteItemClicked(int position, List<Checkpoint> checkpoints);


    }
}
