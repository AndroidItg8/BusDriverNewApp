package itg8.com.busdriverapp.bus.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.bus.adapter.BusAdapter;
import itg8.com.busdriverapp.home.HomeActivity;
import itg8.com.busdriverapp.home.busModel.BusModel;
import itg8.com.busdriverapp.home.busModel.Buses;
import itg8.com.busdriverapp.home.busModel.User;

import static itg8.com.busdriverapp.home.RouteListFragment.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusFragment extends Fragment implements BusAdapter.OnBusItemClickedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private BusModel busModel;



    public BusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment BusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BusFragment newInstance(BusModel busModel) {
        BusFragment fragment = new BusFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, busModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            busModel = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bus, container, false);
        unbinder = ButterKnife.bind(this, view);
     getBusData(busModel);
        return view;
    }

    private void getBusData(BusModel busModel) {

        if(busModel.getWSResponse().getBuses()!=null) {
             List<Buses> list = (List<Buses>) busModel.getWSResponse().getBuses();
            callRecyclerView(list);

            }


    }

    private void callRecyclerView(List<Buses> users) {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(manager);


    mRecyclerView.setAdapter(new BusAdapter(getActivity(), this,users));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onBusItemClicked(int position, Object object) {
        List<User> list = new ArrayList<>();
      //  Log.d(TAG, "onBusItemClicked: "+userModel.size());

         if(object instanceof JSONObject){
             User user = (User) object;
             list.add(user);
         }
         else if(object instanceof JSONArray){
             List<User> users = (List<User>) object;
             list.addAll(users);
         }

//        ((HomeActivity)getActivity()).callFragment(RouteFragment.newInstance(list));
    }
}



