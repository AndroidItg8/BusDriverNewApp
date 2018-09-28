package itg8.com.busdriverapp.admin_map;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.home.model.StudentList;

public class ChildrenAdapter extends RecyclerView.Adapter<ChildrenAdapter.ViewHolder> {


    private List<StudentList> value;

    public ChildrenAdapter(List<StudentList> value) {

        this.value = value;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_child, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(value.get(position).getFname());
    }


    @Override
    public int getItemCount() {
        return value.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgProfilePic)
        ImageView imgProfilePic;
        @BindView(R.id.img_status)
        ImageView imgStatus;
        @BindView(R.id.txtStudent)
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}