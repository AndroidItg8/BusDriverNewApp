package itg8.com.busdriverapp.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.home.model.CheckpointData;
import itg8.com.busdriverapp.home.model.RouteModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RouteListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RouteListFragment extends Fragment implements RouteAdapter.RouteClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static final String TAG = "RouteListFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rvRoutes)
    RecyclerView rvRoutes;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private RouteModel mParam1;
    private RouteAdapter routeAdapter;


    public RouteListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RouteListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RouteListFragment newInstance(RouteModel model) {
        RouteListFragment fragment = new RouteListFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, model);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_route_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRvRoute();
        return view;
    }

    private void initRvRoute() {
        rvRoutes.setLayoutManager(new LinearLayoutManager(getActivity()));
        routeAdapter=new RouteAdapter(mParam1.getCheckpointData(),this);
        rvRoutes.setAdapter(routeAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRouteClicked(CheckpointData data) {
        if(getActivity()!=null)
            ((HomeActivity)getActivity()).callFragment(HomeFragment.newInstance(data));
    }
}
