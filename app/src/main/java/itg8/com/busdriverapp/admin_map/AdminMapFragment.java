package itg8.com.busdriverapp.admin_map;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.common.BaseActivity;
import itg8.com.busdriverapp.common.BaseFragment;
import itg8.com.busdriverapp.common.CommonMethod;
import itg8.com.busdriverapp.common.GenericAdapter;
import itg8.com.busdriverapp.common.NetworkUtility;
import itg8.com.busdriverapp.common.OnRecyclerItemClickListener;
import itg8.com.busdriverapp.home.HomeActivity;
import itg8.com.busdriverapp.home.HomeFragmentInteractor;
import itg8.com.busdriverapp.home.model.Checkpoint;
import itg8.com.busdriverapp.home.model.CheckpointData;
import itg8.com.busdriverapp.home.model.StudentList;
import itg8.com.busdriverapp.map.MapDirectionModel;
import itg8.com.busdriverapp.map.MapLatLngAddressModel;
import itg8.com.busdriverapp.notification.model.MessageEvent;

import static itg8.com.busdriverapp.common.CommonMethod.getListOfLocations;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminMapFragment extends BaseFragment implements OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMarkerClickListener, OnRecyclerItemClickListener {
    public static final String TAG = "AdminMapFragment";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";


    Unbinder unbinder;


    @BindView(R.id.lbl_halts)
    TextView lblHalts;
    @BindView(R.id.lbl_halts_value)
    TextView lblHaltsValue;
    @BindView(R.id.ll_halts)
    LinearLayout llHalts;
    @BindView(R.id.lbl_skip)
    TextView lblSkip;
    @BindView(R.id.lbl_skip_value)
    TextView lblSkipValue;
    @BindView(R.id.ll_skip)
    LinearLayout llSkip;
    @BindView(R.id.lbl_children)
    TextView lblChildren;
    @BindView(R.id.lbl_children_value)
    TextView lblChildrenValue;
    @BindView(R.id.ll_children)
    LinearLayout llChildren;
    @BindView(R.id.rv_childrenList)
    RecyclerView rvChildrenList;

    // TODO: Rename and change types of parameters
    private Location mLocation;
    private CheckpointData checkpointData;
    private GoogleMap mMap;

    HomeFragmentInteractor listener;

    View[] childrenView;
    View[] haltView;
    View[] toSkipView;
    private String startAddress;
    private Marker mCurrentLocMarker;
    private String endAddress;
    private Marker schoolMarker;
    private String childrens;
    private String halt;
    private String skip;
    private MapDirectionModel model;
    private List<MapLatLngAddressModel> haltLocationList;
    //    HashMap<MapLatLngAddressModel, List<StudentList>> mapPointToStudents;
    private boolean isViewDestroyed;
    private GenericAdapter<StudentList, ChildrenViewHolder> adapter;
    HashMap<Integer, Boolean> hasChildPickedHashmap = new HashMap<>();
    private AlertDialog dialog;
    private LatLng oldLatLng;
    private List<LatLng> list = new ArrayList<LatLng>();
    private Polyline line;

    public AdminMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminMapFragment newInstance(Location param1, CheckpointData param2,
                                               String children, String halt, String skip) {
        AdminMapFragment fragment = new AdminMapFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        args.putParcelable(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, children);
        args.putString(ARG_PARAM4, halt);
        args.putString(ARG_PARAM5, skip);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mLocation = getArguments().getParcelable(ARG_PARAM1);
            checkpointData = getArguments().getParcelable(ARG_PARAM2);
            childrens = getArguments().getString(ARG_PARAM3);
            halt = getArguments().getString(ARG_PARAM4);
            skip = getArguments().getString(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_map, container, false);
        unbinder = ButterKnife.bind(this, view);
        listener.onAttach(TAG);
        isViewDestroyed = false;
        haltLocationList = new ArrayList<>();
        initLayouts();
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        init();
        return view;
    }


    private void initLayouts() {
        childrenView = new View[]{llChildren, lblChildren, lblChildrenValue};
        haltView = new View[]{llHalts, lblHalts, lblHaltsValue};
        toSkipView = new View[]{llSkip, lblSkip, lblSkipValue};
        lblChildrenValue.setText(childrens);
        lblHaltsValue.setText(halt);
        lblSkipValue.setText(skip);
        rvChildrenList.setVisibility(View.GONE);
    }

    private void init() {
        llChildren.setOnClickListener(this);
        llHalts.setOnClickListener(this);
        llSkip.setOnClickListener(this);
    }

    // Add a marker in Sydney and move the camera
    LatLng sydney;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        setLocationWithMarkerIcon(checkpointData.getPickLocation());

        if (mLocation == null)
            sydney = new LatLng(-34, 151);
        else
            sydney = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());


        showLocationOnReady();
    }

    private void showLocationOnReady() {
        startAddress = sydney.latitude + "," + sydney.longitude;
            List<Checkpoint> checkpoints= (List<Checkpoint>) checkpointData.getCheckpoints().getCheckpoint();
        if (!TextUtils.isEmpty(checkpoints.get(0).getCheckpointAddress())) {
            startAddress = checkpoints.get(0).getLatitude() + ","
                    + checkpoints.get(0).getLongitude();
        }
        if (!TextUtils.isEmpty(checkpoints.get(checkpoints.size() - 1).getCheckpointAddress())) {
            Checkpoint checkpoint = checkpoints.get(checkpoints.size() - 1);
            endAddress = checkpoint.getLatitude() + "," + checkpoint.getLongitude();
        }

        mMap.setOnMarkerClickListener(this);
        mCurrentLocMarker = mMap.addMarker(new MarkerOptions().position(sydney).title("Present location").icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_bus)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(sydney.latitude, sydney.longitude), 18.0f));
        downloadMapData();
        listener.startLocationUpdate(new BaseActivity.OnLocationUpdateListener() {
            @Override
            public void onLocationUpdate(Location location) {

            }
        });
    }


    //This methos is used to move the marker of each car smoothly when there are any updates of their position
    public Marker animateMarker(final LatLng startPosition, final LatLng toPosition,
                                final boolean hideMarker) {
        if (startPosition == null)
            return null;

//        final Marker marker = mMap.addMarker(new MarkerOptions()
//                .position(startPosition)
//                .title("Current Location")
////                .snippet(mCarParcelableListCurrentLation.get(position).mAddress)
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car)));
//
        PolylineOptions options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        for (int z = 0; z < list.size(); z++) {
            LatLng point = list.get(z);
            options.add(point);
        }
        line = mMap.addPolyline(options);

        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();

        final long duration = 1000;
        final android.view.animation.Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * toPosition.longitude + (1 - t)
                        * startPosition.longitude;
                double lat = t * toPosition.latitude + (1 - t)
                        * startPosition.latitude;

                mCurrentLocMarker.setPosition(new LatLng(lat, lng));

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                } else {
                    if (hideMarker) {
                        mCurrentLocMarker.setVisible(false);
                    } else {
                        mCurrentLocMarker.setVisible(true);
                    }
                }
            }
        });
        return mCurrentLocMarker;
    }

//    private void checkDistanceAndShowStudent(Location location) {
//        if (mapPointToStudents == null)
//            return;
//        for (Map.Entry<MapLatLngAddressModel, List<StudentList>> e :
//                mapPointToStudents.entrySet()) {
//
//            LatLng oldPosition = new LatLng(location.getLatitude(), location.getLongitude());
//            LatLng newPosition = e.getKey().getLatLng();
//
//            float[] results = getDistance(oldPosition, newPosition);
//            ;
//            if (!isViewDestroyed)
//                if (results[0] < 100) {
//                    if (rvChildrenList.getVisibility() == View.GONE) {
//                        initRecyclerview(e.getValue());
//                        rvChildrenList.setVisibility(View.VISIBLE);
//                        break;
//                    }
//                } else {
//                    rvChildrenList.setVisibility(View.GONE);
//                    Log.d(TAG, "checkDistanceAndShowStudent: "+results[0]);
//                }
//        }
////            Log.d(TAG, "compareDistance: :"+results[0]+" old: "+oldPosition.toString()+", new "+haltLocationList.get(1).getPlaceAddress());
//
//    }

    private void initRecyclerview(List<StudentList> value) {
        Log.d(TAG, "initRecyclerview: ");
        rvChildrenList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new GenericAdapter<StudentList, ChildrenViewHolder>(value) {
            @Override
            public ChildrenViewHolder setViewHolder(ViewGroup parent, final OnRecyclerItemClickListener listener) {
                final ChildrenViewHolder c = new ChildrenViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_child, parent, false));
                c.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onItemClicked(view, c.getAdapterPosition());
                    }
                });
                return c;
            }

            @Override
            public void onBindData(ChildrenViewHolder holder, StudentList val) {
                holder.textView.setText(val.getFname());
            }

            @Override
            public OnRecyclerItemClickListener getListener() {
                return AdminMapFragment.this;
            }
        };
        rvChildrenList.setAdapter(adapter);
    }

    private void compareDistance(MapLatLngAddressModel location) {
        LatLng oldPosition = location.getLatLng();
//        if (mapPointToStudents == null)
//            mapPointToStudents = new HashMap<>();

        for (Checkpoint list :
                ((List<Checkpoint>) checkpointData.getCheckpoints().getCheckpoint())) {
            LatLng newPosition = new LatLng(Double.parseDouble(list.getLatitude()), Double.parseDouble(list.getLongitude()));
            float[] results = getDistance(oldPosition, newPosition);
//            Log.d(TAG, "compareDistance: :"+results[0]+" old: "+oldPosition.toString()+", new "+haltLocationList.get(1).getPlaceAddress());
//            if (results[0] < 100) {
//                mapPointToStudents.put(location, list.get());
//            }
        }
    }

    private float[] getDistance(LatLng oldPosition, LatLng newPosition) {
        float[] results = new float[1];
        Location.distanceBetween(oldPosition.latitude, oldPosition.longitude,
                newPosition.latitude, newPosition.longitude, results);
        return results;
    }


    @Override
    public void onDestroyView() {
        listener.onDetach(TAG);
        listener.stopLocationUpdate();
        isViewDestroyed = true;
        unbinder.unbind();
        super.onDestroyView();

    }

    @Override
    public void onClick(View view) {
        Type type = null;
        switch (view.getId()) {
            case R.id.ll_children:
                type = Type.CHILDREN;
                changeChildrenTextViewColor();
                ((HomeActivity) getActivity()).setAndOpenChildrenList();
                break;
            case R.id.ll_halts:
                type = Type.HALTS;
                changeHaltsTextViewColor();
                ((HomeActivity) getActivity()).setAndHaltList();
                break;
            case R.id.ll_skip:
                type = Type.HALTS;
                changeSkipTextViewColor();
                ((HomeActivity) getActivity()).openDrawerLayout();
                break;
        }
//        openListFragment(type);

    }


    private void changeChildrenTextViewColor() {

//        changeRadioClick(llChildren, llSkip, llHalts);
        setSelected(childrenView);
        setUnselected(haltView);
        setUnselected(toSkipView);
    }

    private void changeHaltsTextViewColor() {
//        changeRadioClick(llHalts, llSkip, llChildren);
        setUnselected(childrenView);
        setSelected(haltView);
        setUnselected(toSkipView);
    }

    private void changeSkipTextViewColor() {
//        changeRadioClick(llSkip, llChildren, llHalts);
        setUnselected(childrenView);
        setUnselected(haltView);
        setSelected(toSkipView);
    }


    private void setSelected(View[] views) {
        for (View v :
                views) {
            v.setSelected(true);
        }
    }

    private void setUnselected(View[] views) {
        for (View v :
                views) {
            v.setSelected(false);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
        listener = (HomeFragmentInteractor) context;
    }

    private void downloadMapData() {
//        List<ArrayList<LatLng>> navigations=new ArrayList<>();
//        ArrayList<LatLng> latLngs=new ArrayList<>();
//        for(Checkpoint checkpoint:checkpointData.getCheckpointData().getCheckpoints().getCheckpoint()){
//            latLngs.add(new LatLng(Double.parseDouble(checkpoint.getLatitude()),Double.parseDouble(checkpoint.getLongitude())));
//            mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(checkpoint.getLatitude()),Double.parseDouble(checkpoint.getLongitude())))).setTitle(checkpoint.getCheckpointAddress());
//        }
//        navigations.add(latLngs);
        //  generateNavigation(navigations);

//        for (RouteEList list :
//               checkpointData.getRouteEList() ) {
//            mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(list.getLatitude()),Double.parseDouble(list.getLongitude())))
//                                .title(list.getAddress()));
//        }
        new NetworkUtility.NetworkBuilder().build().downloadAddresses(startAddress, endAddress, CommonMethod.getAddresses(checkpointData), getString(R.string.google_maps_key), new NetworkUtility.ResponseListener() {
            @Override
            public void onSuccess(Object message) {
                List<ArrayList<LatLng>> navigation = getListOfLocations(message.toString());
                model = new Gson().fromJson(message.toString(), MapDirectionModel.class);
                if (isViewDestroyed)
                    return;
                ((HomeActivity) getActivity()).collectPlaces(model, new HomeActivity.MarkerAvailableListener() {
                    @Override
                    public void onAllLatlangAvail(MapLatLngAddressModel values) {

                        mMap.addMarker(new MarkerOptions().position(values.getLatLng())
                                .title(values.getPlaceAddress()));
                        haltLocationList.add(values);
                        compareDistance(values);

                    }

                    @Override
                    public void onLatlangAvail(MapLatLngAddressModel values) {
//                        mMap.addMarker(new MarkerOptions().position(values.getLatLng())
//                                .title(values.getPlaceAddress()));
                        mMap.addMarker(new MarkerOptions().position(values.getLatLng()).title("School").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_school)));
                        haltLocationList.add(values);
                    }

                    @Override
                    public void onListComplete() {

                    }
                });
//                CommonMethod.getLatLngFromAddress(new HomeActivity.MarkerAvailableListener() {
//                    @Override
//                    public void onAllLatlangAvail(MapLatLngAddressModel values) {
//                        mMap.addMarker(new MarkerOptions().position(values.getLatLng())
//                                .title(values.getPlaceAddress()));
//                    }
//
//                    @Override
//                    public void onLatlangAvail(MapLatLngAddressModel latLng) {
//                        mMap.addMarker(new MarkerOptions().position(latLng.getLatLng())
//                                .title(latLng.getPlaceAddress()));
//                    }
//
//                    @Override
//                    public void onListComplete() {
//
//                    }
//                });
                generateNavigation(navigation);
            }

            @Override
            public void onFailure(Object err) {

            }

            @Override
            public void onSomethingWrong(Object e) {

            }
        });
    }

    private void generateNavigation(List<ArrayList<LatLng>> navigation) {
        PolylineOptions options = null;
        LatLng lastLatLng = null;
        for (ArrayList<LatLng> latlang :
                navigation) {

            options = new PolylineOptions();
            options.addAll(latlang);
            options.width(12);
            options.color(Color.RED);
            options.jointType(JointType.ROUND);
            options.geodesic(true);
            lastLatLng = latlang.get(latlang.size() - 1);

        }

        if (options != null)
            mMap.addPolyline(options);

    }


    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        try {
            JSONObject jsonObject = new JSONObject(event.message);
            String latitudeString = jsonObject.getString("Latitude");
            String longitudeString = jsonObject.getString("Longitude");
            String speedOverGround= jsonObject.getString("SpeedOverGround");
            lblSkipValue.setText(speedOverGround);
            Log.d(TAG, "onMessageEvent: "+jsonObject.toString());
            if(jsonObject.has("UserData")){
                getUserStatus(jsonObject.getJSONObject("UserData"));
            }else {
                sendListOfPresentUserToHomeActivty(new ArrayList<String>());
            }
            if(isViewDestroyed || Double.parseDouble(latitudeString)==0)
                return;
            LatLng currentLatLng = new LatLng(Double.parseDouble(latitudeString), Double.parseDouble(longitudeString));
            mCurrentLocMarker.setPosition(currentLatLng);
            if (oldLatLng != null) {
                oldLatLng = currentLatLng;
                list.add(currentLatLng);
//                mCurrentLocMarker = mMap.addMarker(new MarkerOptions().position(currentLatLng).title("Present location").icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_bus)));
//                mCurrentLocMarker.setPosition(currentLatLng);
//                animateMarker(oldLatLng, currentLatLng, false);
            }

            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng));

//
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void getUserStatus(JSONObject userData) {
        List<String> userIDS=new ArrayList<>();
        if(userData.has("users")){
            try {
                if(userData.get("users") instanceof JSONObject){
                    JSONObject user=userData.getJSONObject("users");
                    String userID=user.getString("userID");
                    if(userID!=null)
                        userIDS.add(userID);
                }else if(userData.get("users") instanceof JSONArray){
                    JSONArray users = userData.getJSONArray("users");
                    for (int i=0; i < users.length(); i++) {
                        JSONObject user= users.getJSONObject(i);

                        if(user.has("routeName")){
                            String routeName=user.getString("routeName");
                            if(routeName.equalsIgnoreCase(checkpointData.getRouteName())
                                    ){
                                if(user.has("checkpoints")) {
                                    JSONObject checkpoint = user.getJSONObject("checkpoints");
                                    if(checkpoint.has("checkpoint")){
                                        List<Checkpoint> checkpoints=new ArrayList<>();
                                        if(checkpoint.get("checkpoint") instanceof JSONObject){
                                            Checkpoint checkpointObj=new Gson().fromJson(checkpoint.getJSONObject("checkpoint").toString(),Checkpoint.class);
                                            checkpoints.add(checkpointObj);
                                        }else if(checkpoint.get("checkpoint") instanceof JSONArray){
                                            List<Checkpoint> checkpointList=new Gson().fromJson(checkpoint.getJSONArray("checkpoint").toString(),new TypeToken<List<Checkpoint>>(){}.getType());
                                            checkpoints.addAll(checkpointList);
                                        }
                                        if(getActivity()!=null){
                                            ((HomeActivity)getActivity()).setListOfChildrens(checkpoints);
                                        }
                                    }
                                }

                            }
                        }


//                        if(user.has("userID")) {
//                            String userID = user.getString("userID");
//                            if (userID != null)
//                                userIDS.add(userID);
//                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

//        sendListOfPresentUserToHomeActivty(userIDS);
    }

    private void sendListOfPresentUserToHomeActivty(List<String> userIDS) {
        if(getActivity()!=null){
            ((HomeActivity)getActivity()).changeUsersStatus(userIDS);
        }
    }

    @Override
    public void onItemClicked(View view, int position) {
        if (hasChildPickedHashmap.containsKey(adapter.getItem(position).getPkid())) {
            Toast.makeText(getActivity(), "Children already inside the bus", Toast.LENGTH_SHORT).show();
        } else {
            askToSendDataToAdmin(adapter.getItem(position));
        }
    }

    private void askToSendDataToAdmin(final StudentList item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setMessage(R.string.msg_send_to_admin)
                .setTitle("Not Present")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sendToAdmin(item);
                        dialog.dismiss();
                    }
                });

        dialog = builder.create();
        dialog.show();

    }

    private void sendToAdmin(StudentList item) {
        //TODO send to admin
    }


}
