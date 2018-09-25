package itg8.com.busdriverapp.rout_status;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.admin_map.Type;
import itg8.com.busdriverapp.home.model.User;
import itg8.com.busdriverapp.widget.CircularImageView;

/**
 * Created by Android itg 8 on 3/16/2018.
 */

public class RouteStatusAdapter extends RecyclerView.Adapter<RouteStatusAdapter.StatusViewHolder> {


    private final Context context;
    private final Type type;
    private final List<User> list;
    private HashMap<String, Integer> userIdPosition;
    private HashMap<Integer, User> userByPosition;


    public RouteStatusAdapter(Context context, Type type, List<User> list) {

        this.context = context;
        this.type = type;
        this.list = list;
        userIdPosition = new HashMap<>();
        userByPosition = new HashMap<>();
    }

    @Override
    public StatusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_route_status, parent, false);
        return new StatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StatusViewHolder holder, int position) {
//        if (list.get(position) instanceof StudentList) {
//            StudentList o = (StudentList) list.get(position);
//            holder.lblName.setText(o.getFname());
//            holder.lblAddress.setText(o.getStudentAdd());
//        }else if(list.get(position) instanceof RouteEList){
//            RouteEList o=(RouteEList)list.get(position);
//            holder.lblName.setText(o.getRouteName());
//            holder.lblAddress.setText(o.getAddress());
//        }
        userIdPosition.put(list.get(position).getUserID(), position);
        userByPosition.put(position, list.get(position));
        holder.lblName.setText(checkNull(list.get(position).getFullName()));
        if (list.get(position).getInBus().equalsIgnoreCase("true")) {
            holder.imgStatus.setImageResource(R.drawable.img_status_green);
        } else {
            holder.imgStatus.setImageResource(R.drawable.img_status_orange);
        }
    }

    public HashMap<String, Integer> getUserIdPosition() {
        return userIdPosition;
    }

    private String checkNull(String fullName) {
        if (TextUtils.isEmpty(fullName)) {
            return "";
        }
        return fullName;
    }

    @Override
    public int getItemCount() {
        return list.size();
//        return list.size();
    }

    public void setUserIDs(List<String> userIDS) {
        for (User user :
                list) {
            if (userIDS.contains(user.getUserID())) {
                user.setInBus("true");
            } else {
                user.setInBus("false");
            }
             int i = userIdPosition.get(user.getUserID());
            notifyItemChanged(i);
        }
    }

    public void setAllUsers(List<User> users) {
        this.list.clear();
        this.list.addAll(users);
        notifyDataSetChanged();
    }

    public class StatusViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_profile)
        CircularImageView imgProfile;
        @BindView(R.id.lbl_name)
        TextView lblName;
        @BindView(R.id.lbl_address)
        TextView lblAddress;
        @BindView(R.id.img_status)
        ImageView imgStatus;

        public StatusViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
