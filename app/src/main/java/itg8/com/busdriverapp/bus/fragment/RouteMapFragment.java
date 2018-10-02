package itg8.com.busdriverapp.bus.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.bus.adapter.ChildListAdapter;
import itg8.com.busdriverapp.home.HomeActivity;
import itg8.com.busdriverapp.home.busModel.Checkpoint;
import itg8.com.busdriverapp.home.busModel.User_;

import static itg8.com.busdriverapp.home.RouteListFragment.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RouteMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RouteMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GoogleMap mMap;
    private BottomSheetBehavior<LinearLayout> sheetBehavior;
    private List<Checkpoint> checkList;
    private Context context;
    private Marker myMarker;
    OnMakerClickedListener listener;

    public RouteMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RouteMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RouteMapFragment newInstance(List<Checkpoint> checkpointList) {
        RouteMapFragment fragment = new RouteMapFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, (ArrayList<? extends Parcelable>) checkpointList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            checkList = getArguments().getParcelableArrayList(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_route_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        callBottomSheet();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        listener = (OnMakerClickedListener) context;

    }


    private List<LatLng> getLatituteAndLongitude() {
        List<LatLng> latituteList = new ArrayList<>();
        LatLng latLng = new LatLng(21.1222209, 79.0405717);
        latituteList.add(latLng);
        latLng = new LatLng(21.1222209, 79.0405288);
        latituteList.add(latLng);
        latLng = new LatLng(21.1333464, 79.0579316);
        latituteList.add(latLng);
        latLng = new LatLng(21.1532194, 79.0614292);
        latituteList.add(latLng);
        latLng = new LatLng(21.173013, 79.0602705);
        latituteList.add(latLng);
        return latituteList;


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));


        onMapLocationReady();
    }

    private void onMapLocationReady() {
        List<LatLng> location = getLatituteAndLongitude();
//        PolylineOptions options =  new PolylineOptions()
//                .width(10)
//                .color(Color.RED)
//                .geodesic(true)
//                .clickable(true)
//                .jointType(JointType.ROUND);
        Log.d(TAG, "onMapLocationReady: checkList Size" + checkList.size());
        if (checkList != null && checkList.size() > 0) {
            for (Checkpoint latLng : checkList) {

                myMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(latLng.getLatitude()), Double.parseDouble(latLng.getLongitude()))).title(latLng.getCheckpointAddress()).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_school)));
                myMarker.setTag(latLng);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom((new LatLng(Double.parseDouble(latLng.getLatitude()), Double.parseDouble(latLng.getLongitude()))), 12));
            }

            mMap.setOnMarkerClickListener(this);

        }


    }


    @Override
    public boolean onMarkerClick(Marker marker) {

        //Log.d(TAG, "onMarkerClick: maker  "+marker.getTag());
        Checkpoint selectedCheckpoint= (Checkpoint) marker.getTag();
        listener.onMakerClicked(selectedCheckpoint.getUsersChild(), selectedCheckpoint.getCheckpointAddress());
//            for (Checkpoint ch : checkList) {
//
//                if (marker.getTitle().equals(ch.getCheckpointAddress())) {
//                    return false;
//                }
//        }
        return false;

    }

    public interface OnMakerClickedListener {

        void onMakerClicked(List<User_> list, String address);
    }

}

