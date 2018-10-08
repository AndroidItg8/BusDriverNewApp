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
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.common.CommonMethod;
import itg8.com.busdriverapp.request.adpter.RoleAdapter;
import itg8.com.busdriverapp.request.adpter.RoleUserAdapter;
import itg8.com.busdriverapp.request.model.Role;
import itg8.com.busdriverapp.request.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RoleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoleFragment extends Fragment implements RoleAdapter.OnItemClickedListner, View.OnClickListener,
        RoleUserAdapter.OnItemCheckedUserListner, RequestActivity.OnCheckChangedClickedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM1_1 = "ARG_PARAM1_1";
    private static final String ARG_PARAM1_2 = "ARG_PARAM1_2";
    private static final String ARG_PARAM2_1 = "ARG_PARAM2_1";
    private static final String FROM_CHECK_USER = "FROM_CHECK_USER";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.btnDone)
    Button btnDone;
    @BindView(R.id.btnClear)
    Button btnClear;
    Unbinder unbinder;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Role> roleList;
    private List<User> userList = new ArrayList<>();
    private RoleAdapter adapter;
    RoleClickListner mHomeListner;
    private Context context;
    private RoleUserAdapter userAdapter;
    private String roleID;
    private String fromCheckUser;

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


    public static RoleFragment newInstance(Role role, List<User> roleUser) {
        RoleFragment fragment = new RoleFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1_1, (ArrayList<? extends Parcelable>) roleUser);
        args.putString(ARG_PARAM1_2, role.getRoleID());
        fragment.setArguments(args);
        return fragment;
    }

    public static RoleFragment newInstance(List<User> userList, String value) {
        RoleFragment fragment = new RoleFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1_1, (ArrayList<? extends Parcelable>) userList);
        args.putString(ARG_PARAM2_1, value);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
                roleList = getArguments().getParcelableArrayList(ARG_PARAM1);
                userList = getArguments().getParcelableArrayList(ARG_PARAM1_1);
                roleID=getArguments().getString(ARG_PARAM1_2);
            fromCheckUser =getArguments().getString(ARG_PARAM2_1);
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_role, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        ((RequestActivity) getActivity()).setActivityListener(this);

        return view;
    }

    private void init() {
        btnClear.setOnClickListener(this);
        btnDone.setOnClickListener(this);
        if (userList != null) {
            setRecyclerViewa(userList);
        } else {
            setRecyclerView(roleList);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        mHomeListner = (RoleClickListner) context;
    }

    private void setRecyclerView(List<Role> roleList) {
        if (roleList.size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(manager);
            adapter = new RoleAdapter(getActivity(), roleList, this);
            mRecyclerView.setAdapter(adapter);
        }


    }


    private void setRecyclerViewa(List<User> userList) {
        if (userList.size() > 0) {
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(manager);
            userAdapter = new RoleUserAdapter(getActivity(), userList, this);
            mRecyclerView.setAdapter(userAdapter);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClicked(int position, Role users, Boolean b) {
        users.setChecked(b);
        for (User user :
                users.getRoleUser()) {
            user.setChecked(b);
        }

        adapter.notifyItemChanged(position);
        mHomeListner.onRoleClicked(position, users);
        //        adapter.notifyItemChanged(position);


    }

    @Override
    public void onItemClicked(int adapterPosition, Role role) {
        mHomeListner.onRoleClicked(adapterPosition,role);
    }

    @Override
    public void onCheckedUpdateList(List<User> userList) {
//        for (User user : userList
//                ) {
//            if (user.getChecked() != null) {
//                if (user.getChecked()) {
//                    users.add(user);
//                }
//            }


//        }

//        setRecyclerView(roles);


    }

    @Override
    public void onCheckedUpdateList(List<User> b, List<Role> userList) {


    }

    @Override
    public void onClick(View view) {
        if (view == btnDone) {
            if (userList != null ) {
                if(fromCheckUser!=null)
                         mHomeListner.onUserChecked(userList);
                else
                    mHomeListner.onUserChecked(userList,roleID);


            } else {

                mHomeListner.onRoleChecked(roleList);
            }
        } else if (view == btnClear) {
            if (userList != null) {
                for (User user : userList
                        ) {
                    user.setChecked(false);


                }
                userAdapter.notifyDataSetChanged();
            }
            if (roleList != null) {
                for (Role user : roleList
                        ) {
                    user.setChecked(false);
                    }

                adapter.notifyDataSetChanged();
            }
            mHomeListner.unCheckedOnClearButton();

        }
    }


    @Override
    public void onItemCheckedUser(int position, User users, Boolean b) {
//            userList.remove(position);
            users.setChecked(b);
            userAdapter.notifyItemChanged(position);
//        userList.add(position,users);


//        mHomeListner.onUserClicked(position, users);


    }

    @Override
    public void onCheckBoxClickedUser(boolean b, List<User> list) {

        List<User> userList = new ArrayList<>();
        for (User user : list
                ) {
            user.setChecked(b);
//            user.setFullName(user.getFullName());
//            user.setUserID(user.getUserID());
            userList.add(user);

        }
        setRecyclerViewa(userList);

    }

    @Override
    public void onCheckBoxClickedRole(boolean b, List<Role> list) {
        List<User> userList1 = new ArrayList<>();
        for (Role user : list
                ) {
            user.setChecked(b) ;


//            user.setRoleID(user.getRoleID());
//            user.setRoleName(user.getRoleName());
//            user.setRoleUser(user.getRoleUser());

                for (User user1 : user.getRoleUser()) {


                    user1.setChecked(b);
//                    user1.setFullName(user1.getFullName());
//                    user1.setUserID(user1.getUserID());
                    userList1.add(user1);

                }

//            userList1.addAll(userList);
//            roleList.add(user);
        }

//        this.userList.addAll(userList);
//        this.userList= new ArrayList<>();
//        this.userList.addAll(userList1);
        if(userList1.size()>0)
             setRecyclerViewa(userList1);

    }


}

