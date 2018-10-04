package itg8.com.busdriverapp.bus.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.bus.adapter.BusAdapter;
import itg8.com.busdriverapp.home.HomeActivity;
import itg8.com.busdriverapp.home.busModel.BusModel;
import itg8.com.busdriverapp.home.busModel.Buses;
import itg8.com.busdriverapp.home.busModel.Checkpoint;
import itg8.com.busdriverapp.home.busModel.Checkpoints;
import itg8.com.busdriverapp.home.busModel.User;
import itg8.com.busdriverapp.home.busModel.User_;
import itg8.com.busdriverapp.home.model.RouteModel;

import static itg8.com.busdriverapp.R2.id.message;
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
    HideBottomSheetListener  listener;
    private Context context;


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
@Override
public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
listener = (HideBottomSheetListener) context;
listener.onHideBottomSheet();
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
    public void onBusItemClicked(int position, final List<User> userList) {
        ((HomeActivity)getActivity()).callFragment(RouteFragment.newInstance(userList));


//        Observable.just(object).flatMap(new Function<Object, Observable<List<User>>>() {
//
//            @Override
//            public Observable<List<User>> apply(Object o) throws Exception {
//                List<User> userLists = new ArrayList<>();
//
//                if(object instanceof LinkedTreeMap<?,?>){
//                    String jsonString = new Gson().toJson(object);
//                    Object json = new JSONTokener(jsonString).nextValue();
//                    itg8.com.busdriverapp.home.busModel.User busUser = new Gson().fromJson(json.toString(), itg8.com.busdriverapp.home.busModel.User.class);
//                    userLists.add(busUser);
//                }
//                if(object instanceof User){
//                    String jsonString = new Gson().toJson(object);
//                    Object json = new JSONTokener(jsonString).nextValue();
//                    List<User> userList = new Gson().fromJson(json.toString(), new TypeToken<List<User>>() {
//                    }.getType());
//                    userLists.addAll(userList);
//                    }
//
//
//                User user1 = new User();
//
//                for (User check:userLists) {
//                    Checkpoints checkpoints = new Checkpoints();
//                    String jsonStrings = new Gson().toJson(check.getCheckpoints().getCheckpoint());
//                    Object jsons = new JSONTokener(jsonStrings).nextValue();
//
//                    if(checkpoints.getCheckpoint() instanceof  LinkedTreeMap<?,?>) {
//                            List<Checkpoint> checkpoints1 = new Gson().fromJson(jsons.toString(), new TypeToken<List<Checkpoint>>() {
//                            }.getType());
//
//                            checkpoints.setCheckpoint(checkpoints1);
//                            checkpoints.setCheckpointList(checkpoints1);
//                        }
//                        user1.setCheckpoints(checkpoints);
////                    userLists.add(user1);
//                    }
//                    return Observable.just(userLists);
//            }
//
//
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<List<User>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<User> users) {
//
//                    }
//
//
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


    }

    @Override
    public void onBusItemCalledClicked(int position, Buses busModel) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + busModel.getDriverName()));
        startActivity(intent);


    }

    @Override
    public void onBusItemSMSClicked(int position, Buses busModel) {
        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + busModel.getDriverNumber()));
        intent.putExtra( "sms_body", message );
        startActivity(intent);

    }

    @Override
    public void onDetach() {
        listener.onHideBottomSheet();
        super.onDetach();

    }

    public interface HideBottomSheetListener{

        void onHideBottomSheet();
    }
}



