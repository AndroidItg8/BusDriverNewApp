package itg8.com.busdriverapp.bus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import itg8.com.busdriverapp.R;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder> {
    private final Context context;
    private final OnRouteItemClickedListener listener;

    public RouteAdapter(Context context, OnRouteItemClickedListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_route, parent, false);
        return new RouteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteViewHolder routeViewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class RouteViewHolder extends RecyclerView.ViewHolder {
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
