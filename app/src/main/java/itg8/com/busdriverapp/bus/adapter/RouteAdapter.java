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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.home.busModel.Checkpoint;
import itg8.com.busdriverapp.home.busModel.User;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder> {
    private final Context context;
    private final OnRouteItemClickedListener listener;

    private List<User> busModel;

    public RouteAdapter(Context context, OnRouteItemClickedListener listener, List<User> busModel) {
        this.context = context;
        this.listener = listener;
        this.busModel = busModel;
    }

    @NonNull
    @Override
    public RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_route, parent, false);
        return new RouteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteViewHolder holder, int position) {
        holder.chekModel = (Checkpoint) busModel.get(position).getCheckpoints().getCheckpointList();
        holder.mTxtCheckCount.setText( busModel.get(position).getCheckpoints().getCheckpointList().size());
        holder.mTxtChildCount.setText( holder.chekModel.getUsers().size());
        holder.mLblStartRouteName.setText( busModel.get(position).getRouteName());

    }

    @Override
    public int getItemCount() {
        return busModel.size();
    }

    public class RouteViewHolder extends RecyclerView.ViewHolder {

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

        Checkpoint chekModel;
        public RouteViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRouteItemClicked(getAdapterPosition());
                }
            });
        }
    }

    public interface OnRouteItemClickedListener {
        void onRouteItemClicked(int position);


    }
}
