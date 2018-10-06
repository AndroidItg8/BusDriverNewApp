package itg8.com.busdriverapp.request;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
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
import itg8.com.busdriverapp.request.adpter.RoleAdapter;
import itg8.com.busdriverapp.request.adpter.RoleUserAdapter;
import itg8.com.busdriverapp.request.model.Role;
import itg8.com.busdriverapp.request.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RoleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoleFragment extends Fragment implements RoleAdapter.OnItemClickedListner{
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
    private List<Role> roleList;
    private List<User> userList = new ArrayList<>();
    private RoleAdapter adapter;
    RoleClickListner listner;
    private Context context;


    public RoleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment RoleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoleFragment newInstance(List<Role> param1) {
        RoleFragment fragment = new RoleFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, (ArrayList<? extends Parcelable>) param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            roleList = getArguments().getParcelableArrayList(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_role, container, false);
        unbinder = ButterKnife.bind(this, view);

        setRecyclerView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
         this.context = context;
        listner = (RoleClickListner) context;
    }

    private void setRecyclerView() {
        if (roleList.size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(manager);
            adapter = new RoleAdapter(getActivity(), roleList, this);
            mRecyclerView.setAdapter(adapter);
        }


    }


    private void setRecyclerViewa() {
        if (userList.size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.setAdapter(new RoleUserAdapter(getActivity(), userList));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClicked(int position, Role users) {
        users.setChecked(true);
//        adapter.notifyItemChanged(position);
        roleList.set(position, users);
        listner.onRoleClicked(position, users);

    }



    public interface  RoleClickListner{
        void  onRoleClicked(int position, Role role);
    }
}

