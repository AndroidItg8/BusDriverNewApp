package itg8.com.busdriverapp.bus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.common.CommonMethod;
import itg8.com.busdriverapp.home.busModel.User_;

public class ChildListAdapter extends RecyclerView.Adapter<ChildListAdapter.ChildViewHolder> {

    private final List<User_> list;
    //    private final OnChildItemClickedListener listener;
    private Context context;
    private String address;
    private String onChildClicked;

    public ChildListAdapter(Context context, List<User_> list, String address, String onChildClicked) {

        this.context = context;
//        this.listener = listener;
        this.list = list;
        this.address = address;
        this.onChildClicked = onChildClicked;
    }


    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_child, parent, false);

        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {


        holder.txtStudent.setText(list.get(position).getFullName());
        if (onChildClicked.equals(CommonMethod.CHILD_CLICKED)) {
            holder.txtCheckpointAddress.setText((list.get(position).getAddress()));

        } else {
            holder.txtCheckpointAddress.setText(address);

        }

        if (!TextUtils.isEmpty(list.get(position).getInBus())) {

            if (list.get(position).getInBus().equals("true")) {
                holder.imgStatus.setImageResource(R.drawable.ic_my_location_24dp);

            } else {
                holder.imgStatus.setImageResource(R.drawable.ic_my_location_gray_24dp);

            }
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnChildItemClickedListener {
        void onChildItemClicked(int position);


    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgProfilePic)
        ImageView imgProfilePic;
        @BindView(R.id.txtStudent)
        TextView txtStudent;
        @BindView(R.id.txtCheckpointAddress)
        TextView txtCheckpointAddress;
        @BindView(R.id.img_location)
        ImageView imgLocation;
        @BindView(R.id.img_status)
        ImageView imgStatus;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    listener.onChildItemClicked(getAdapterPosition());
                }
            });
        }
    }
}