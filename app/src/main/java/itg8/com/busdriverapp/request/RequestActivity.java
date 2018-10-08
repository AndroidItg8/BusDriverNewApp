package itg8.com.busdriverapp.request;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.common.CommonMethod;
import itg8.com.busdriverapp.request.model.Role;
import itg8.com.busdriverapp.request.model.User;

public class RequestActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,
        RoleClickListner {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.lbl_selectRole)
    TextView mLblSelectRole;
    @BindView(R.id.checkbox)
    CheckBox mCheckbox;
    @BindView(R.id.frame_container)
    FrameLayout mFrameContainer;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    private FragmentManager fm;
    private List<Role> roleList;
    RoleClickListner.OnCheckChangedClickedListener listener;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setClickListner();
        init();


    }

    private void setClickListner() {
        mCheckbox.setOnCheckedChangeListener(this);
    }

    private void init() {
        if (getIntent().hasExtra(CommonMethod.ROLE)) {

            roleList = getIntent().getParcelableArrayListExtra(CommonMethod.ROLE);
            callFragment(RoleFragment.newInstance(roleList));
        }
        if (getIntent().hasExtra(CommonMethod.ROLE_USER)) {
            userList = getIntent().getParcelableArrayListExtra(CommonMethod.ROLE_USER);
            RoleFragment fragment = new RoleFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(CommonMethod.ROLE_USER, (ArrayList<? extends Parcelable>) userList);
            fragment.setArguments(bundle);
            if (fm == null)
                fm = getSupportFragmentManager();
            FragmentTransaction ft = fm
                    .beginTransaction();
            ft.remove(fragment);
            ft.add(R.id.frame_container, fragment, fragment.getClass().getSimpleName());
            ft.addToBackStack(fragment.getClass().getSimpleName());
            ft.commit();

        }


    }

    private void callFragment(Fragment fragment) {
        if (fm == null)
            fm = getSupportFragmentManager();
        FragmentTransaction ft = fm
                .beginTransaction();
        ft.replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName());
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();

    }

    @Override
    public void onUserClicked(int position, User user) {
        mLblSelectRole.setText(user.getFullName() +" ");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        listener.onCheckedUpdateList(position, userList);

    }

    @Override
    public void onRoleClicked(int position, Role role) {
        mLblSelectRole.setText(role.getRoleName() + "   " + role.getRoleUser().size());
//        userList.addAll(role.getRoleUser());
        roleList.clear();
        roleList.add(role);
        RoleFragment fragment = new RoleFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(CommonMethod.ROLE_USER, (ArrayList<? extends Parcelable>) role.getRoleUser());
        fragment.setArguments(bundle);
        callFragment(fragment);
    }


    @Override
    public void onUserChecked(List<User> userList, List<Role> list) {
//        Intent returnIntent = new Intent();
//        returnIntent.putParcelableArrayListExtra(CommonMethod.ROLE_USER, (ArrayList<? extends Parcelable>) userList);
//        setResult(Activity.RESULT_OK, returnIntent);
//        this.finish();
        listener.onCheckedUpdateList(userList, roleList);

    }

    @Override
    public void onRoleChecked(List<Role> roleList) {
        Intent returnIntent = new Intent();
        returnIntent.putParcelableArrayListExtra(CommonMethod.ROLE, (ArrayList<? extends Parcelable>) roleList);

        setResult(Activity.RESULT_OK, returnIntent);
        this.finish();
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (listener != null) {
            if (roleList != null && roleList.size() > 0) {

                listener.onCheckBoxClickedRole(b, roleList);
            } else if (userList != null && userList.size() > 0) {
                listener.onCheckBoxClickedUser(b, userList);
            }
        }

    }

    public void setActivityListener(RoleClickListner.OnCheckChangedClickedListener listener) {

        this.listener = listener;
    }


}
