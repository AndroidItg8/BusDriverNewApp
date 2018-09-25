package itg8.com.busdriverapp.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.home.model.Checkpoint;
import itg8.com.busdriverapp.home.model.CheckpointData;

class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.ViewHolder> {

    RouteClickListener listener;
    private List<CheckpointData> checkpointData;

    public RouteAdapter(List<CheckpointData> checkpointData,RouteClickListener listener) {
        this.checkpointData = checkpointData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_routes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtCheckpointCount.setText(String.valueOf(((List<Checkpoint>)checkpointData.get(position).getCheckpoints().getCheckpoint()).size()));
        holder.txtRouteName.setText(checkpointData.get(position).getRouteName());
    }

    @Override
    public int getItemCount() {
        return checkpointData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txtRouteName)
        TextView txtRouteName;
        @BindView(R.id.lblCheckpoints)
        TextView lblCheckpoints;
        @BindView(R.id.txtCheckpointCount)
        TextView txtCheckpointCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onRouteClicked(checkpointData.get(getAdapterPosition()));
        }
    }

    public interface RouteClickListener{
        void onRouteClicked(CheckpointData data);
    }
}
