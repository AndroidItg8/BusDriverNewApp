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
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;

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
    RoleClickListner.OnCheckChangedClickedListener listener;
    private FragmentManager fm;
    private List<Role> roleList;
    private List<User> userList;
    private List<User> selectedAllUser = new ArrayList<>();
    private boolean isRoledClicked = false;

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
            callFragment(RoleFragment.newInstance(userList, CommonMethod.FROM_CHECK_USER));
        }


    }

    private void callFragment(Fragment fragment) {
        if (fm == null)
            fm = getSupportFragmentManager();
        FragmentTransaction ft = fm
                .beginTransaction();
        ft.replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName());
//        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();

    }

    @Override
    public void onUserClicked(int position, User user) {
        mLblSelectRole.setText(user.getFullName() + " ");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        listener.onCheckedUpdateList(userList);

    }

    @Override
    public void onRoleClicked(int position, Role role) {
        mLblSelectRole.setText(role.getRoleName() + "   " + role.getRoleUser().size());
        selectedAllUser.clear();
        selectedAllUser.addAll(role.getRoleUser());
        isRoledClicked = true;

        callFragment(RoleFragment.newInstance(role, role.getRoleUser()));
//        roleList.clear();
//        roleList.add(role)
    }


    @Override
    public void onUserChecked(List<User> userList) {
        Intent returnIntent = new Intent();


        returnIntent.putParcelableArrayListExtra(CommonMethod.ROLE_USER, (ArrayList<? extends Parcelable>) userList);

        setResult(Activity.RESULT_OK, returnIntent);
        this.finish();
    }

    @Override
    public void onUserChecked(List<User> userList, String roleID) {
        Role tempRole = null;
        Log.d(this.getClass().getSimpleName(), "onUserChecked: " + new Gson().toJson(roleList));
        for (Role r :
                roleList) {
            if (r.getRoleID().equalsIgnoreCase(roleID)) {
                r.setRoleUser(userList);
                tempRole = r;
            }
        }
        Log.d(this.getClass().getSimpleName(), "onUserChecked: after " + new Gson().toJson(roleList));

        if (tempRole != null) {
            callFragment(RoleFragment.newInstance(roleList));
        }


    }

    @Override
    public void unCheckedOnClearButton() {
        mCheckbox.setChecked(false);
    }

    @Override
    public void onRoleChecked(List<Role> roleList) {
        List<Role> TempRole = new ArrayList<>();
        for (Role role : roleList) {
            if (role.isChecked()) {
                TempRole.add(role);
            }


        }
        Intent returnIntent = new Intent();


        returnIntent.putParcelableArrayListExtra(CommonMethod.ROLE, (ArrayList<? extends Parcelable>) TempRole);

        setResult(Activity.RESULT_OK, returnIntent);
        this.finish();
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            if (listener != null) {

                if (isRoledClicked) {
                    if (selectedAllUser != null && selectedAllUser.size() > 0) {
                        listener.onCheckBoxClickedUser(b, selectedAllUser);
                    }
                } else if (roleList != null && roleList.size() > 0) {
                    listener.onCheckBoxClickedRole(b, roleList);
                }
                isRoledClicked = !isRoledClicked;
            }


        } else {
            callFragment(RoleFragment.newInstance(roleList));
        }

    }

    public void setActivityListener(RoleClickListner.OnCheckChangedClickedListener listener) {

        this.listener = listener;
    }


}
