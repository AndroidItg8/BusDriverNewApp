package itg8.com.busdriverapp.request;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
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

public class RequestActivity extends AppCompatActivity implements RoleFragment.RoleClickListner{

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();


    }

    private void init() {
        if (getIntent().hasExtra(CommonMethod.ROLE)) {

         roleList = getIntent().getParcelableArrayListExtra(CommonMethod.ROLE);
            callFragment(RoleFragment.newInstance(roleList));
        }
        if (getIntent().hasExtra(CommonMethod.ROLE_USER)) {
            List<User> userList = getIntent().getParcelableArrayListExtra(CommonMethod.ROLE_USER);
            callFragment(RoleFragment.newInstance(roleList));
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
    public void onRoleClicked(int position, Role role) {
        mLblSelectRole.setText(role.getRoleName() +"   "+ role.getRoleUser().size() );

    }
}
