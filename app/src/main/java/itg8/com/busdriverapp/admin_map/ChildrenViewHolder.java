package itg8.com.busdriverapp.admin_map;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.busdriverapp.R;

class ChildrenViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imgProfilePic)
    ImageView imgProfilePic;
    @BindView(R.id.img_status)
    ImageView imgStatus;
    @BindView(R.id.txtStudent)
    TextView textView;
    public ChildrenViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
