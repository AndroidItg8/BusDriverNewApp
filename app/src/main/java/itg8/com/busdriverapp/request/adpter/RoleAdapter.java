package itg8.com.busdriverapp.request.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.request.model.Role;
import itg8.com.busdriverapp.request.model.User;

public class RoleAdapter extends RecyclerView.Adapter<RoleAdapter.RoleViewHolder> {
    private final Context context;
    private final List<Role> roleList;
    private final OnItemClickedListner listener;


    public RoleAdapter(Context context, List<Role> roleList, OnItemClickedListner listener) {
        this.context = context;
        this.roleList = roleList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RoleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_role, parent, false);

        return new RoleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoleViewHolder holder, int position) {
        holder.mTxtRole.setText(roleList.get(position).getRoleName());
        holder.mTxtCount.setText(String.valueOf(roleList.get(position).getRoleUser().size()));
         if(roleList.get(position).isChecked()){
             holder.mChkStatus.setChecked(true);
         }else{
             holder.mChkStatus.setChecked(false);

         }


    }

    @Override
    public int getItemCount() {
        return roleList.size();
    }

    public class RoleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtRole)
        TextView mTxtRole;
        @BindView(R.id.txt_count)
        TextView mTxtCount;
        @BindView(R.id.chk_status)
        CheckBox mChkStatus;
        public RoleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mChkStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    listener.onItemClicked(getAdapterPosition(), roleList.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickedListner {
        void onItemClicked(int position, Role users);
    }
}
