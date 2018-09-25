package itg8.com.busdriverapp.admin_map;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.home.HomeActivity;
import itg8.com.busdriverapp.rout_status.RouteStatusAdapter;

/**
 * Created by swapnilmeshram on 26/03/18.
 */

public class ChildCheckinDialogFragment extends DialogFragment {

    private static final String PARAM1 = "PARAM1";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private List<Parcelable> childList;

    public static ChildCheckinDialogFragment newInstance(List childList) {

        Bundle args = new Bundle();
        args.putParcelableArrayList(PARAM1, (ArrayList<? extends Parcelable>) childList);
        ChildCheckinDialogFragment fragment = new ChildCheckinDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        childList = b.getParcelableArrayList(PARAM1);
//        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        getDialog().getWindow().getAttributes().windowAnimations=R.style.PauseDialogAnimation;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_child_checkin_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        createRecyclerview();
        return view;
    }

    private void createRecyclerview() {


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



}
