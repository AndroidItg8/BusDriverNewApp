package itg8.com.busdriverapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.busdriverapp.R;
import itg8.com.busdriverapp.common.CommonMethod;
import itg8.com.busdriverapp.common.Prefs;
import itg8.com.busdriverapp.common.UtilSnackbar;
import itg8.com.busdriverapp.home.HomeActivity;
import itg8.com.busdriverapp.login.mvp.LoginMVP;
import itg8.com.busdriverapp.login.mvp.LoginPresenterImp;
import itg8.com.busdriverapp.notification.SignalRService;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginMVP.LoginView {

    private static final String TAG = "loginActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rl_custom)
    RelativeLayout rlCustom;
    @BindView(R.id.lbl_login)
    TextView lblLogin;
    @BindView(R.id.input_number)
    EditText inputNumber;
    @BindView(R.id.input_layout_number)
    TextInputLayout inputLayoutNumber;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.input_layout_password)
    TextInputLayout inputLayoutPassword;
    @BindView(R.id.lbl_forget)
    TextView lblForget;
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindString(R.string.bad_credentials)
    String badCredentials;

    private LoginMVP.LoginPresenter presenter;
    private boolean tokenAvail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        presenter = new LoginPresenterImp(this);
        startService(new Intent(this, SignalRService.class));
        if(Prefs.getBoolean(CommonMethod.IS_LOGIN,false)){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            this.finish();
            return;
        }
        Log.d(TAG, "onCreate: "+ Prefs.getString(CommonMethod.TOKEN));
        if(!Prefs.contains(CommonMethod.TOKEN)){
            presenter.startGettingToken();
        }
        btnLogin.setOnClickListener(this);
    }

    public void onClick(View view) {
//        startActivity(new Intent(this, HomeActivity.class));
//        finish();
        presenter.onLoginClicked(view);
    }

    @Override
    public String getUsername() {
        inputLayoutNumber.setError(null);
        return inputNumber.getText().toString();
    }

    @Override
    public String getPassword() {
        inputLayoutPassword.setError(null);
        return inputPassword.getText().toString();
    }

    @Override
    public void onSuccess() {
        Prefs.putBoolean(CommonMethod.IS_LOGIN,true);
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(CommonMethod.IS_LOGIN,true);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFail(String message) {
        UtilSnackbar.showSnakbarTypeTwo(inputLayoutNumber,message);
    }

    @Override
    public void onError(Object t) {
        onFail(badCredentials);
    }

    @Override
    public void onUsernameInvalid(String err) {
        inputLayoutNumber.setError(err);
    }

    @Override
    public void onPasswordInvalid(String err) {
        inputLayoutPassword.setError(err);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setTokenAvail(boolean isAvail) {
        tokenAvail=isAvail;
    }

}
