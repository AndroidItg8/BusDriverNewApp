package itg8.com.busdriverapp.request.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.request.model.User;

public class RoleUserAdapter extends   RecyclerView.Adapter<RoleUserAdapter.RoleUserViewHolder> {
    private final Context context;
    private final List<User> userList;

    public RoleUserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public RoleUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_user_role, parent, false);

        return new RoleUserAdapter.RoleUserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RoleUserViewHolder roleUserViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class RoleUserViewHolder extends RecyclerView.ViewHolder {
        public RoleUserViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
