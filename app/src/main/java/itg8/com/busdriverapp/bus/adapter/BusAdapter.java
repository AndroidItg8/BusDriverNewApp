package itg8.com.busdriverapp.bus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import itg8.com.busdriverapp.R;


public class BusAdapter extends RecyclerView.Adapter<BusAdapter.BusViewHolder> {

    private Context context;
    private OnBusItemClickedListener listener;

    public BusAdapter(Context context, OnBusItemClickedListener listener) {

        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public BusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bus, parent, false);

        return new BusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusViewHolder busViewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class BusViewHolder extends RecyclerView.ViewHolder {

        public BusViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBusItemClicked(getAdapterPosition());
                }
            });
        }
    }

    public interface OnBusItemClickedListener {
        void onBusItemClicked(int position);


    }

}
