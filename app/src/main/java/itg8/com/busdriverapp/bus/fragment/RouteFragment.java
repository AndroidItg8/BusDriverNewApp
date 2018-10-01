package itg8.com.busdriverapp.bus.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.bus.adapter.RouteAdapter;
import itg8.com.busdriverapp.home.HomeActivity;
import itg8.com.busdriverapp.home.busModel.Checkpoint;
import itg8.com.busdriverapp.home.busModel.Checkpoints;
import itg8.com.busdriverapp.home.busModel.User;
import itg8.com.busdriverapp.home.busModel.User_;

import static itg8.com.busdriverapp.home.RouteListFragment.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RouteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RouteFragment extends Fragment implements RouteAdapter.OnRouteItemClickedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<User> listUser;


    public RouteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment RouteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RouteFragment newInstance(List<User> busModel) {
        RouteFragment fragment = new RouteFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, (ArrayList<? extends Parcelable>) busModel);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            listUser = getArguments().getParcelableArrayList(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_route, container, false);
        unbinder = ButterKnife.bind(this, view);
        callRecyclerView();
        return view;
    }

    private void callRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(new RouteAdapter(getActivity(), this, listUser));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRouteItemClicked(int position, List<Checkpoint> checkpoints) {

                                ((HomeActivity)getActivity()).callFragment(RouteMapFragment.newInstance(checkpoints));

//        io.reactivex.Observable
//                .just(checkpoints)
//                .flatMap(new Function<Checkpoints, ObservableSource<List<Checkpoint>>>() {
//
//                    @Override
//                    public ObservableSource<List<Checkpoint>> apply(Checkpoints checkpoints) throws Exception {
//
//                        List<Checkpoint> list= new ArrayList<>();
//                        String jsonString = new Gson().toJson(checkpoints.getCheckpoint());
//                        Object json = new JSONTokener(jsonString).nextValue();
////                        if(checkpoints.getCheckpoint() instanceof  ArrayList<?>){
// List<Checkpoint> checkpoints1 = new Gson().fromJson(json.toString(), new TypeToken<List<Checkpoint>>() {
//                            }.getType());
//
//
//                            list.addAll(checkpoints1);
//                        Log.d(TAG, "apply: "+checkpoints1.size());
////                        }
//
//
//                        return io.reactivex.Observable.just(list);
//                    }
//                }).subscribeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<List<Checkpoint>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<Checkpoint> checkpointList) {
//                        ((HomeActivity)getActivity()).callFragment(RouteMapFragment.newInstance(checkpointList));
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//
    }

}
