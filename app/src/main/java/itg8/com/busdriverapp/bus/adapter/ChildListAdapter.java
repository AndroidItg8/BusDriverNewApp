package itg8.com.busdriverapp.bus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import itg8.com.busdriverapp.R;

public class ChildListAdapter extends RecyclerView.Adapter<ChildListAdapter.ChildViewHolder> {

    private final OnChildItemClickedListener listener;
    private Context context;

    public ChildListAdapter(Context context, ChildListAdapter.OnChildItemClickedListener listener) {

        this.context = context;
        this.listener = listener;
    }


    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_child,parent,false);

        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder childViewHolder, int position) {

    }



    @Override
    public int getItemCount() {
        return 10;
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {


        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onChildItemClicked(getAdapterPosition());
                }
            });
        }
    }

    public interface OnChildItemClickedListener {
        void onChildItemClicked(int position);


    }
}