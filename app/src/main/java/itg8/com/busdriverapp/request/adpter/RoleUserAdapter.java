package itg8.com.busdriverapp.request.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.request.model.Role;
import itg8.com.busdriverapp.request.model.User;

public class RoleUserAdapter extends RecyclerView.Adapter<RoleUserAdapter.RoleUserViewHolder> {
    private final Context context;
    private final List<User> userList;
    private OnItemCheckedUserListner listner;


    public RoleUserAdapter(Context context, List<User> userList, OnItemCheckedUserListner listner) {
        this.context = context;
        this.userList = userList;
        this.listner = listner;
    }

    @NonNull
    @Override
    public RoleUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_user_role, parent, false);

        return new RoleUserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RoleUserViewHolder holder, int position) {
        holder.txtStudent.setText(userList.get(position).getFullName());
        if(userList.get(position).getChecked()!=null){
        if(userList.get(position).getChecked()){
            holder.checkboxStatus.setChecked(true);
        }else{
            holder.checkboxStatus.setChecked(false);
        }}
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class RoleUserViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgProfilePic)
        ImageView imgProfilePic;
        @BindView(R.id.txtStudent)
        TextView txtStudent;
        @BindView(R.id.txtCheckpointAddress)
        TextView txtCheckpointAddress;
        @BindView(R.id.img_location)
        ImageView imgLocation;
        @BindView(R.id.checkbox_statusa)
        CheckBox checkboxStatus;

        public RoleUserViewHolder(@NonNull View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);


            checkboxStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(compoundButton.isPressed())
                        listner.onItemCheckedUser(getAdapterPosition(), userList.get(getAdapterPosition()),b);

                }
            });
        }
    }
    public interface OnItemCheckedUserListner {
        void onItemCheckedUser(int position, User users, Boolean b);
    }
}
