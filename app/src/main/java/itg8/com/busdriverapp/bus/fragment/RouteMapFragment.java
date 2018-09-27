package itg8.com.busdriverapp.bus.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.bus.adapter.ChildListAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RouteMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RouteMapFragment extends Fragment implements OnMapReadyCallback, ChildListAdapter.OnChildItemClickedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GoogleMap mMap;
    private BottomSheetBehavior<LinearLayout> sheetBehavior;


    public RouteMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RouteMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RouteMapFragment newInstance(String param1, String param2) {
        RouteMapFragment fragment = new RouteMapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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

    private   List<LatLng> getLatituteAndLongitude() {
        List<LatLng> latituteList = new ArrayList<>();
        LatLng latLng = new LatLng(21.1222209,79.0405717);
        latituteList.add(latLng);
        latLng = new LatLng(21.1222209,79.0405288);
        latituteList.add(latLng);
       latLng = new LatLng(21.1333464,79.0579316);
        latituteList.add(latLng);
        latLng = new LatLng(21.1532194,79.0614292);
        latituteList.add(latLng);
        latLng = new LatLng(21.173013,79.0602705);
        latituteList.add(latLng);
         return latituteList;


    }


    private void callBottomSheet() {

//        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);


        /**
         * bottom sheet state change listener
         * we are changing button text when sheet changed state
         * */
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {

                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {

                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }


//    private void callRecyclerView() {
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setAdapter(new ChildListAdapter(getActivity(), this));
//    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));


        onMapLocationReady();
    }

    private void onMapLocationReady() {
List<LatLng> location=        getLatituteAndLongitude();
//        PolylineOptions options =  new PolylineOptions()
//                .width(10)
//                .color(Color.RED)
//                .geodesic(true)
//                .clickable(true)
//                .jointType(JointType.ROUND);

for(LatLng latLng :location) {

    mMap.addMarker(new MarkerOptions().position(latLng).title("Bus").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_school)));
//    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));

//    options.add(latLng);



}
//        mMap.addPolyline(options);

//        callRecyclerView();



    }

    @Override
    public void onChildItemClicked(int position) {

    }
}
