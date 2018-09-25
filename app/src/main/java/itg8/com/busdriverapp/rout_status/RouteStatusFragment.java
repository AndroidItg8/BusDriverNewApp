package itg8.com.busdriverapp.rout_status;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.admin_map.Type;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RouteStatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RouteStatusFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;

    // TODO: Rename and change types of parameters
    private Type type;
    private String mParam2;


    public RouteStatusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RouteStatusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RouteStatusFragment newInstance(Type param1, String param2) {
        RouteStatusFragment fragment = new RouteStatusFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getParcelable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_route_status, container, false);
        unbinder = ButterKnife.bind(this, view);
        getRootLayoutWidth();
        return view;
    }

    private void getRootLayoutWidth() {
        ViewTreeObserver vto = rlTop.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            int width;

            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    rlTop.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    width = rlTop.getMeasuredWidth();
                } else {

                }

                ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
                params.width = width / 2;
               // rlTop.setLayoutParams(params);
                recyclerView.setLayoutParams(params);
            }
        });

        getList();

    }

    private void getList() {
        List<Object> objects = new ArrayList<>();



        setRecyclerView(objects);
    }

    private void setRecyclerView(List<Object> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
      //  recyclerView.setAdapter(new RouteStatusAdapter(getActivity(), type, list));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
