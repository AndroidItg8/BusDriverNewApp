package itg8.com.busdriverapp.home;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.common.BaseFragment;
import itg8.com.busdriverapp.home.model.Checkpoint;
import itg8.com.busdriverapp.home.model.CheckpointData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String TAG = "HomeFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.lbl_admin_name)
    TextView lblAdminName;
    @BindView(R.id.lbl_admin_detail)
    TextView lblAdminDetail;
    @BindView(R.id.lbl_pick_up)
    TextView lblPickUp;
    @BindView(R.id.img_direction)
    ImageView imgDirection;
    @BindView(R.id.lbl_source_location)
    TextView lblSourceLocation;
    @BindView(R.id.lbl_designation_location)
    TextView lblDesignationLocation;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.lbl_time_remaining)
    TextView lblTimeRemaining;
    @BindView(R.id.rl_location)
    RelativeLayout rlLocation;
    @BindView(R.id.img_bus)
    ImageView imgBus;
    @BindView(R.id.lbl_bus_number)
    TextView lblBusNumber;
    @BindView(R.id.lbl_halts)
    TextView lblHalts;
    @BindView(R.id.lbl_halts_value)
    TextView lblHaltsValue;
    @BindView(R.id.lbl_skip)
    TextView lblSkip;
    @BindView(R.id.lbl_skip_value)
    TextView lblSkipValue;
    @BindView(R.id.lbl_children)
    TextView lblChildren;
    @BindView(R.id.lbl_children_value)
    TextView lblChildrenValue;
    @BindView(R.id.ll_children)
    LinearLayout llChildren;
    @BindView(R.id.lbl_let_start)
    TextView lblLetStart;
    @BindView(R.id.rl_card)
    RelativeLayout rlCard;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private CheckpointData mCheckpointData;
    private HomeFragmentInteractor listener;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(CheckpointData param1) {
        HomeFragment fragment = new HomeFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        listener.onAttach(TAG);
        init();
        return view;
    }

    private void init() {
        lblLetStart.setOnClickListener(this);
        setDetailsFromModel();
    }

    private void setDetailsFromModel() {
        if(mCheckpointData !=null){
            lblAdminName.setText("Welcome "+getNString("ss"));
            setChildrenRelatedDetails();
            setRouteRelatedDetails();
            return;
        }
        lblAdminName.setText("Welcome Bus Driver");
//        setChildrenRelatedDetails();
        setRouteRelatedDetails();
    }

    private void setRouteRelatedDetails() {
//        lblHaltsValue.setText(getNString(String.valueOf(mCheckpointData.getRouteEList().size())));
//        lblSourceLocation.setText(getNString(mCheckpointData.getPickLocation()));
//        lblDesignationLocation.setText(getNString(mCheckpointData.getDropLocation()));
//        lblSkipValue.setText("0");
        if(mCheckpointData !=null) {
            String numberOfHalt = String.valueOf(((List<Checkpoint>)mCheckpointData.getCheckpoints().getCheckpoint()).size());
            lblBusNumber.setText(mCheckpointData.getBusNumber());
            lblHaltsValue.setText(numberOfHalt);
        }
        listener.startDownloadingAddressForMap(mCheckpointData);
    }

    private void setChildrenRelatedDetails() {
        Observable.create(new ObservableOnSubscribe<List<Checkpoint>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Checkpoint>> e) throws Exception {
                //                    for (StudentList list :
                //                            elist.getStudentList()) {
                //                    }
                List<Checkpoint> lists = new ArrayList<>((List<Checkpoint>)mCheckpointData.getCheckpoints().getCheckpoint());

                e.onNext(lists);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Checkpoint>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Checkpoint> studentLists) {
                        String getCountOfChildrens=String.valueOf(studentLists.size());
                        String lblDetail="You have to pick "+getCountOfChildrens+" children from the location.Following are the complete details.";
                        lblAdminDetail.setText(lblDetail);
                        lblChildrenValue.setText(getCountOfChildrens);
                        if(!isDetached() && getActivity()!=null){
                            ((HomeActivity)getActivity()).setListOfChildrens(studentLists);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        listener.onDetach(TAG);
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lbl_let_start:
                try {
                    listener.onStartMapFragment(lblChildrenValue.getText().toString(),lblHaltsValue.getText().toString(),lblSkipValue.getText().toString(),mCheckpointData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (HomeFragmentInteractor) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
