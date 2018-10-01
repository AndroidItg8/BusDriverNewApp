package itg8.com.busdriverapp.rout_status;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.home.model.Checkpoint;
import itg8.com.busdriverapp.home.model.CheckpointData;
import itg8.com.busdriverapp.home.model.User;
import itg8.com.busdriverapp.rout_status.mvp.RouteStatusMVP;
import itg8.com.busdriverapp.rout_status.mvp.RouteStatusPresenterImp;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RouteStatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RouteStatusFragment extends Fragment implements RouteStatusMVP.RouteStatusView, OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.txtRouteName)
    TextView txtRouteName;
    @BindView(R.id.lblCheckpointCount)
    TextView lblCheckpointCount;
    @BindView(R.id.txtCheckpointCount)
    TextView txtCheckpointCount;
    @BindView(R.id.ll_checkpoint_count)
    LinearLayout llCheckpointCount;
    @BindView(R.id.lblStudentCount)
    TextView lblStudentCount;
    @BindView(R.id.txtStudentCount)
    TextView txtStudentCount;
    @BindView(R.id.ll_student)
    LinearLayout llStudent;
    @BindView(R.id.lblLeave)
    TextView lblLeave;
    @BindView(R.id.txtLeaveCount)
    TextView txtLeaveCount;
    @BindView(R.id.ll_Leave)
    LinearLayout llLeave;
    @BindView(R.id.mvRouteMap)
    MapView mvRouteMap;
    @BindView(R.id.btnStart)
    Button btnStart;
    @BindView(R.id.rvCheckpoints)
    RecyclerView rvCheckpoints;
    @BindView(R.id.rl_top)
    ConstraintLayout rlTop;
    Unbinder unbinder;


    // TODO: Rename and change types of parameters
    private CheckpointData mCheckpointData;
    private String mParam2;


    private RouteStatusMVP.RouteStatusPresenter presenter;
    private GoogleMap gmap;
    private RouteStatusMVP.AllMapRelData navigation;

    public RouteStatusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment RouteStatusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RouteStatusFragment newInstance(CheckpointData param1) {
        RouteStatusFragment fragment = new RouteStatusFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCheckpointData = getArguments().getParcelable(ARG_PARAM1);
            presenter = new RouteStatusPresenterImp();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_route_status, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter.onViewCreated(this);
        init();
        return view;
    }

    private void init() {
        txtRouteName.setText(mCheckpointData.getRouteName());
        mvRouteMap.getMapAsync(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onViewDestroyed();
        unbinder.unbind();
    }

    @Override
    public void onCheckpointsWithUserAvail() {

    }

    @Override
    public void onListOfAddressForMapViewAvail(RouteStatusMVP.AllMapRelData navigation) {
        if(gmap==null){
            this.navigation=navigation;
            return;
        }
//        for (:
//             ) {
//
//        }
    }

    @Override
    public void ListOfLeaveAvail() {

    }

    @Override
    public void onCheckpointAvail(List<Checkpoint> checkpoints) {

    }

    @Override
    public void onUsersListAvail(List<User> allUsers) {
        txtStudentCount.setText(String.valueOf(allUsers.size()));
    }

    @Override
    public void onLeaveUserAvail(List<User> usersOnLeave) {
        txtLeaveCount.setText(String.valueOf(usersOnLeave.size()));
    }

    @Override
    public void onNoInternet() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onRetroError(Object obj) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.gmap=googleMap;
    }
}
