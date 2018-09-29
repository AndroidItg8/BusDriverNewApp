package itg8.com.busdriverapp.bus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.home.busModel.Buses;
import itg8.com.busdriverapp.home.busModel.User;


public class BusAdapter extends RecyclerView.Adapter<BusAdapter.BusViewHolder> {


    private Context context;
    private OnBusItemClickedListener listener;
    private List<Buses> busModel;

    public BusAdapter(Context context, OnBusItemClickedListener listener, List<Buses> busModel) {

        this.context = context;
        this.listener = listener;
        this.busModel = busModel;
    }


    @NonNull
    @Override
    public BusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bus, parent, false);

        return new BusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusViewHolder holder, int position) {
        holder.mTxtBusName.setText(busModel.get(position).getBusName());
        holder.mTxtBusNo.setText(busModel.get(position).getBusNumber());
        holder.mTxtDriverNumber.setText(busModel.get(position).getDriverNumber());
        holder.mDriverName.setText(busModel.get(position).getDriverName());


    }

    @Override
    public int getItemCount() {
        return busModel.size();
    }

    public class BusViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lbl_busName)
        TextView mLblBusName;
        @BindView(R.id.txt_busName)
        TextView mTxtBusName;
        @BindView(R.id.lbl_BusNo)
        TextView mLblBusNo;
        @BindView(R.id.txt_busNo)
        TextView mTxtBusNo;
        @BindView(R.id.ll_bus)
        LinearLayout mLlBus;
        @BindView(R.id.view)
        View mView;
        @BindView(R.id.lbl_driver_number)
        TextView mLblDriverNumber;
        @BindView(R.id.txt_driver_number)
        TextView mTxtDriverNumber;
        @BindView(R.id.lbl_driver_name)
        TextView mLblDriverName;
        @BindView(R.id.driverName)
        TextView mDriverName;
        @BindView(R.id.lldriver)
        LinearLayout mLldriver;



        public BusViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBusItemClicked(getAdapterPosition(),  busModel.get(getAdapterPosition()).getUser());
                }
            });
        }
    }

    public interface OnBusItemClickedListener {
        void onBusItemClicked(int position, Object busModel);


    }

}
